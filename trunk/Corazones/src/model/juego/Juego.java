package model.juego;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import model.carta.Mesa;
import model.jugador.Jugador;

public class Juego {
	public static Mesa mesa1 = new Mesa();
	public static Corazones juegoCorazones = new Corazones(mesa1,4);
	

	static int cantJugadores = juegoCorazones.cantJugadores();	
	Jugador[] players = new Jugador[cantJugadores];

	
	
	
	//Espero a #cantJugadores Jugadores.
	public void recibirJugador (Jugador j){
		juegoCorazones.setPlayers(j);
	}
	
	
	public void main (){
		ServerSocket serverSocket;
		try{
		// Creo una coneccion por el puerto
		serverSocket = new ServerSocket(1234);
		  // Espero que se cree el canal
		  Socket serverSoc = serverSocket.accept();
			
			// Creo un buffer para guardar lo que recibo
			BufferedReader serverIn = new BufferedReader(new InputStreamReader(serverSoc.getInputStream()));
			  for (int i = 0; i<cantJugadores ; i++){	
				  //recibo 4 jugadores en forma de string, serializar !
				  Jugador x = serverIn.readLine();
				  System.out.println("Jugador "+serverIn.readLine());
				  this.recibirJugador(x);
			  }
			  //juego corazones, start!
		serverIn.close();
		serverSoc.close();
		serverSocket.close();
		}
		catch(IOException e) {
		System.out.println("Could not listen on port: 1234");
		System.exit(-1);
		}
	}
	
}
