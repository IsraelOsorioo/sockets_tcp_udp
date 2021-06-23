package edu.ieu.sockets.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;

import edu.ieu.sockets.tcp.EchoClient;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class ClienteGrafico {

	private JFrame frmIsraelOsorio;
	private JTextField textMensaje;
	private JTextPane textRespuesta;
	private JButton btnEnviar;
	
	private EchoClient echoClient = new EchoClient();

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteGrafico window = new ClienteGrafico();
					window.frmIsraelOsorio.setVisible(true);
					window.conectar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void conectar() {
		echoClient.startConnection("localhost", 6000);
		textRespuesta.setText("Conectado al servidor localhost:6000");
	}
	
	public String agregarTextoRespuesta(String nuevoTexto) {
		StringBuilder builder = new StringBuilder();
		builder.append( textRespuesta.getText() );
		builder.append(nuevoTexto + "\n");
		return builder.toString();
	}

	/**
	 * Create the application.
	 */
	public ClienteGrafico() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIsraelOsorio = new JFrame();
		frmIsraelOsorio.setFont(new Font("Verdana", Font.ITALIC, 15));
		frmIsraelOsorio.setTitle("Israel Osorio");
		frmIsraelOsorio.setBounds(100, 100, 450, 300);
		frmIsraelOsorio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textMensaje = new JTextField();
		textMensaje.setForeground(Color.BLACK);
		textMensaje.setBackground(Color.WHITE);
		textMensaje.setFont(new Font("Verdana", Font.PLAIN, 13));
		textMensaje.setToolTipText("Escriba el mensaje al sevidor\r\n");
		frmIsraelOsorio.getContentPane().add(textMensaje, BorderLayout.NORTH);
		textMensaje.setColumns(10);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBackground(Color.WHITE);
		btnEnviar.setForeground(Color.BLACK);
		btnEnviar.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensaje = textMensaje.getText();
				System.out.println("Mensaje para el servidor " + mensaje);
				
				String respuesta = echoClient.sendMessage(mensaje);
				System.out.println("Respuesta : " + respuesta);
				String historialRespuesta = agregarTextoRespuesta(respuesta);
				textRespuesta.setText(historialRespuesta);
				
				if(respuesta.equals("good bye")) {
					System.out.println("Conecion finalizada");
					textRespuesta.setText("Coneción finalizada..... \n " 
							+ "Reinicie el programa");
					echoClient.stopConnection();
					btnEnviar.setEnabled(false);
				}
			}
		});
		frmIsraelOsorio.getContentPane().add(btnEnviar, BorderLayout.EAST);
		
		JTextPane textPane = new JTextPane();
		textPane.setForeground(Color.BLACK);
		textPane.setFont(new Font("Verdana", Font.PLAIN, 13));
		textPane.setBackground(Color.WHITE);
		frmIsraelOsorio.getContentPane().add(textPane, BorderLayout.CENTER);
	}

}
