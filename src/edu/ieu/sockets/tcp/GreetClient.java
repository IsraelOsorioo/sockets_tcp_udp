package edu.ieu.sockets.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class GreetClient {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public void startConnection(String ip, int port) {
		try {
			//connect
			clientSocket = new Socket(ip,port);
			out = new PrintWriter( clientSocket.getOutputStream(),true);
			in = new BufferedReader(
					new InputStreamReader(
						clientSocket.getInputStream()
						)					
					);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String sendMessage(String msg) {
		String response = "";
		try {
			out.println(msg);
			response = in.readLine();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public void stopConnection() {
		try {
			in.close();
			out.close();
			clientSocket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		GreetClient cliente = new GreetClient();
		System.out.println("Conectando al servidor localhost");
		cliente.startConnection("localhost", 6000);
		System.out.println("Enviando mensaje al servidor localhost");
		String response = cliente.sendMessage("hello server");
		System.out.println("Obtuvimos la respuesta: " + response);
	}
	
}



















