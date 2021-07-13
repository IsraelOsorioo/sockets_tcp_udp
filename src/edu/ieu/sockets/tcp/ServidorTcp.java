package edu.ieu.sockets.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTcp {
		
	public void iniciar() {
		ServerSocket servidor;
		boolean fin = false;
		try {
			servidor = new ServerSocket(6000); //Se creo el socket, bind, listen
			System.out.println("Servidor escuchando en el puerto 6000");
			Socket socketManejoCliente = servidor.accept(); // Aceptamos la conexion del ciente.
			//Objetos de entrada y de salida.
			DataInputStream   in = new DataInputStream( socketManejoCliente.getInputStream() );
			DataOutputStream out = new DataOutputStream( socketManejoCliente.getOutputStream() );
			do {
				String mensaje = "";
				mensaje = in.readUTF(); //<-- Leemos le mensaje del cliente
				System.out.println("El servidor recibió del cliente: "+ mensaje);
				out.writeUTF("Gracias por tu mensaje"); // <-- Enviamos una respuesta al cliente
				if(mensaje.equalsIgnoreCase("fin")) {
					fin = true;
					System.out.println("El servidor se apagara ya no recibe mas mensajes:");					
				}				
			}while(!fin);
			in.close();
			out.close();
			socketManejoCliente.close();
			servidor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	public static void main(String args[]) {
		ServidorTcp servidor = new ServidorTcp();
		servidor.iniciar();
	}
}