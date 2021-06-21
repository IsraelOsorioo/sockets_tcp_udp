package edu.ieu.sockets.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	private Socket clienteSocket;
	private ServerSocket serverSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public void start(int port) {
		try {
		//Creamos el servidor en puerto port
		serverSocket = new ServerSocket(port);
		System.out.println("Servidor escuchando en el puerto " + port);
		
		//Aceptamos la conexión del cliente
		clienteSocket = serverSocket.accept();
		System.out.println("Se conecto un cliente " + clienteSocket.getInetAddress().toString());
		
		//Ajustamos las entradas y salidas del cliente para comunicarnos con el cliente
		out = new PrintWriter( clienteSocket.getOutputStream(), true);
		in = new BufferedReader( new InputStreamReader( clienteSocket.getInputStream() ) );
		
		//Leer y luego escribimos en el socket
		String inputLine = "";
		while( (inputLine = in.readLine() ) != null) {
			System.out.println("Recibimos del cliente: " + inputLine);
			if(".".contentEquals(inputLine)) {
				out.println("good bye");
				System.out.println("Servidor recibio orden de apagado");
				break;
			}
			out.println(inputLine);
		}
		out.close();
		in.close();
		clienteSocket.close();
		serverSocket.close();
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EchoServer echoServer = new EchoServer();
		echoServer.start(6000);
		System.out.println("Servicio finalizado.....");
	}
}
