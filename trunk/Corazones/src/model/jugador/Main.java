package model.jugador;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.carta.Carta;
import utils.common.Serializable;

public class Main {

	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(1234);
			Socket serverSoc = serverSocket.accept();
			PrintStream clienteOut = new PrintStream(serverSoc.getOutputStream());
			BufferedReader clienteIn = new BufferedReader(new InputStreamReader(serverSoc.getInputStream()));
			
			//System.out.println(Serializable.deStringAObjeto(clienteIn.readLine()));
			Carta[] cartas = new Carta[]{new Carta(), new Carta(), new Carta()};
			
			while(true){
				//clienteOut.println("getThis");
				//System.out.println(Serializable.deStringAObjeto(clienteIn.readLine()));
				clienteOut.println("incrementarPuntaje&44");
				clienteOut.println("getPuntos");
				System.out.println(clienteIn.readLine());
				clienteOut.println("elegir3Cartas");
				Object[] cartas3 = (Object[])Serializable.deStringAObjeto(clienteIn.readLine());
				for (Object object : cartas3) {
					System.out.println((Carta)object);
				}
				clienteOut.println("recibir3Cartas#"+Serializable.deObjetoAString(cartas));
				clienteOut.println("getSNombre");
				System.out.println(clienteIn.readLine());
				clienteOut.println("recibirCarta#"+Serializable.deObjetoAString(new Carta()));
				clienteOut.println("tengo2DeTrebol");
				System.out.println(clienteIn.readLine());				
			}	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
