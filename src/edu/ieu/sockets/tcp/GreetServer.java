package edu.ieu.sockets.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetServer {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public void start(int port) {
		try {
			//bind listen
			serverSocket = new ServerSocket(port);
			//accept
			System.out.println("Servidor escuchando.....");
			clientSocket = serverSocket.accept();
			System.out.println("conectando un cliente");
			out = new PrintWriter( clientSocket.getOutputStream(), true);
			in = new BufferedReader( 
						new InputStreamReader(
							clientSocket.getInputStream()
						)
					);
			System.out.println("mensaje cliente");
			String greeting = in.readLine();
			System.out.println("mensaje cliente " + greeting);
			if("hello server".equals(greeting)) {
				out.println("Hello cliente");
			}else {
				out.println("saludo no reconocido");
			}
			System.out.println("respuesta enviada al cliente");
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void stop() {
		try {
		in.close();
		out.close();
		clientSocket.close();
		serverSocket.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		GreetServer servidor = new GreetServer();
		System.out.println("Servidor iniciado en puerto 6000.....");
		servidor.start(6000);
		System.out.println("Servidor finalizado");
	}
}













