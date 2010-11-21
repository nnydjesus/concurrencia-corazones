package model.juego;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.carta.Mazo;
import model.carta.Mesa;

public class Juego {
	public static Mesa mesa1 = new Mesa();
	public static Corazones juegoCorazones = new Corazones(mesa1, 4, new Mazo());

	static int cantJugadores = juegoCorazones.cantJugadores();

	// Espero a #cantJugadores Jugadores.
	public void recibirJugador(int key, Contenedor par) {
		juegoCorazones.addPlayers(key, par);
	}

	public static void main(String[] args) {
		Juego juego = new Juego();
		ServerSocket serverSocket;
		try {
			// Creo una coneccion por el puerto
			serverSocket = new ServerSocket(1234);
			
			// recibo 4 jugadores en forma de string, serializar !			
			for (int i = 0; i < cantJugadores; i++) {
				// Espero que se cree el canal
				Socket serverSoc = serverSocket.accept();
				BufferedReader serverIn = new BufferedReader(new InputStreamReader(serverSoc.getInputStream()));
				PrintStream clienteOut = new PrintStream(serverSoc.getOutputStream());
				juego.recibirJugador(i, new Contenedor(serverIn, clienteOut));
				// juego.recibirJugador(x);
			}
			juegoCorazones.init();	
		} catch (IOException e) {
			new RuntimeException(e);
		}
	}

}
