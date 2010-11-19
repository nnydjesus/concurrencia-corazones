package model.carta;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import utils.common.Serializable;

public class Main {

	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9999);
			Socket serverSoc = serverSocket.accept();
			PrintStream clienteOut = new PrintStream(serverSoc.getOutputStream());
			BufferedReader clienteIn = new BufferedReader(new InputStreamReader(serverSoc.getInputStream()));
			while(true){
				clienteOut.println("getPuntos");
				System.out.println(clienteIn.readLine());
				clienteOut.println("elegir3Cartas");
				System.out.println(Serializable.deStringAObjeto(clienteIn.readLine()));
				System.out.println(Serializable.deStringAObjeto(clienteIn.readLine()));
				System.out.println(Serializable.deStringAObjeto(clienteIn.readLine()));			
			}	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
