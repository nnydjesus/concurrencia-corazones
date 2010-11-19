package model.carta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public final class Mesa extends Vector {
	// Atributo, "tope" es el total de cartas del vector
	private static  int cantJugadores; // Depende de la cantidad de Jugadores

	// Constructor de la clase.
	public Mesa() {		
		super(cantJugadores);
	}
	
	public void setCantJugadores(int cantJugadores) {
		this.cantJugadores = cantJugadores;
	}

	public int getCantJugadores() {
		return this.cantJugadores;
	}

	// Devuelve el numero de cartas que hay en la Mesa.
	public int cant_cartas_enMesa() {
		return this.size();
	}

	/*
	 * Devuelve true si la meza est� vacia, es decir, si no hay ninguna carta en
	 * la meza. Devuelve false en caso contrario.
	 */
	public boolean mezaVacia() {
		return  this.isEmpty();
	}

	/*
	 * Devuelve el palo de la carta que se encuentra en la posici�n i de la
	 * meza.
	 */
	public String devolver_Palo(String carta) {
		return carta.substring(carta.length()-1);
	}
	
	public int devolverNumero(String carta) {
		return new Integer(carta.substring(0));
	}

	/*
	 * Devuelve la carta con mayor numero y de palo de la primer carta de la
	 * mesa.
	 */
	public int devolver_carta_masAlta() {
		final String paloCorrespondiente = this.devolver_Palo(this.devolverCarta(0));
		String cartaActual;
		int iCartaMax = 0;
		for (int i = 1; i < TOPE; i++) {
			cartaActual = this.devolverCarta(i);
			if (compararCarta(paloCorrespondiente, this
					.devolverCarta(iCartaMax), cartaActual)) {
				iCartaMax = i;
			}
		}
		return iCartaMax;
	}

	private boolean compararCarta(final String paloCorrespondiente,
			String cartaMax, String cartaActual) {
		return (paloCorrespondiente.equals(this.devolver_Palo(cartaActual))
				&& ( this.devolverNumero(cartaMax) < this.devolverNumero(cartaActual)));
	}

	public int numJugadorCartaMasAlta(int jugadorQueTiroPrimero) {
		return (jugadorQueTiroPrimero + this.devolver_carta_masAlta()) % TOPE;
	}
	
	

	public static void main(String argv[]) {
		try {
			
			ServerSocket serverSocket = new ServerSocket(8888);
			Socket serverSoc = serverSocket.accept();
			PrintStream clienteOut = new PrintStream(serverSoc.getOutputStream());
			
			Socket socketj1 = new Socket("localhost",1222);
			BufferedReader j1 = new BufferedReader(new InputStreamReader(socketj1.getInputStream()));
			Socket socketj2 = new Socket("localhost",1333);
			BufferedReader j2 = new BufferedReader(new InputStreamReader(socketj2.getInputStream()));
			Socket socketj3 = new Socket("localhost",1444);
			BufferedReader j3 = new BufferedReader(new InputStreamReader(socketj3.getInputStream()));
			Socket socketj4 = new Socket("localhost",1555);
			BufferedReader j4 = new BufferedReader(new InputStreamReader(socketj4.getInputStream()));
			Socket socketjuego = new Socket("localhost",9999);
			BufferedReader juego = new BufferedReader(new InputStreamReader(socketjuego.getInputStream()));
			
			while(true){
								
			}
		}catch (IOException e) {
			System.out.println("Could not listen on port: 1234");
			}
	}	
	
	
}
