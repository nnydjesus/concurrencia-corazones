package model.jugador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import model.carta.Carta;
import model.carta.Mazo;
import model.carta.Palo;
import utils.common.ReflectionUtils;
import utils.common.Serializable;

/* La clase Jugador representa a los jugadores de la partida. Cada jugador
 * consta de un nombre, un puntaje y un pozo de cartas donde alojar� a las
 * cartas que se lleve de la baza.
 */

/*
 * Agregarle las cartas
 */

public class Jugador implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/* Atributos */
	private String nombre;
	private int puntaje;
	// private Pozojugador pozo;
	private Carta[] mano = new Carta[Mazo.NUM_CARTA_POR_MAZO];
	int x = 12;
	private static BufferedReader serverIn;
	private static PrintStream clienteOut;

	/* Constructors */

	public Jugador() {
		puntaje = 0;
		cargar();
	}

	public Jugador(String name) {
		this();
		nombre = name;
	}

	public void cargar() {
		for (int i = 0; i < Mazo.NUM_CARTA_POR_MAZO; i++) {
			mano[i] = new Carta();
		}
	}

	public void inicializarPuntaje() {
		puntaje = 0;
	}

	/*
	 * Incrementa el puntaje del jugador en "n" unidades.
	 */
	public void incrementarPuntaje(Object n) {
		puntaje += Integer.parseInt((String) n);
	}

	/* Envia a la mesa la carta mas conveniente */
	public void tirarCartaAMesa() {
		clienteOut.println(Serializable.deObjetoAString(this.nextCarta()));
	}
	
	private Carta nextCarta(){
		Carta c = null;
		int i=0;
		boolean sigo = true;
		while(i<Mazo.NUM_CARTA_POR_MAZO && sigo ){
			if(!this.mano[i].getPalo().equals(Palo.NADA)){
				c=mano[i];
				mano[i]=new Carta();
				sigo = false;
			}
			i++;
		}
		return c;
	}

	public Carta[] getMano() {
		return mano;
	}

	public void elegir3Cartas() {
		Carta[] cartas = new Carta[] { this.nextCarta(),
				this.nextCarta(), this.nextCarta() };
		
		clienteOut.println(Serializable.deObjetoAString(cartas));
	}

	public void recibir3Cartas(Object cartas) {
		for (Carta carta : (Carta[])cartas) {
			this.recibirCarta(carta);
		}
	}

	public void recibirCarta(Object carta) {
		System.out.println(carta);
		int i = 0;
		boolean sigo=true;
		while (sigo && i < Mazo.NUM_CARTA_POR_MAZO) {
			if (this.mano[i].getPalo().equals(Palo.NADA)) {
				this.mano[i] = (Carta) carta;
				sigo=false;
			}
			i++;
		}
	}

	/*
	 * Devuelve true si en la baraja se encuentran cartas de un "palo" dado.
	 */
	public boolean hayCartasDelPalo(Palo palo) {
		for (Carta carta : this.mano) {
			if (carta.getPalo().equals(palo)) {
				return true;
			}
		}
		return false;
	}
	
	public void tengo2DeTrebol(){
		boolean tengo = false;
		for (Carta carta : this.mano) {
			if(carta.es2deTrebol()){
				tengo = true;
				break;
			}
		}
		clienteOut.println(tengo);
	}

	public int getPuntos() {
		clienteOut.println(puntaje);
		return puntaje;
	}

	public void getSNombre() {
		clienteOut.println(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void getThis() {
		clienteOut.println(Serializable.deObjetoAString(this));
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

	public static void main(java.lang.String argv[]) {
		try {
			Jugador jugador = new Jugador();
			Socket socket = new Socket("localhost", 1234);
			clienteOut = new PrintStream(socket.getOutputStream());
			serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true) {
				String lectura = serverIn.readLine();
				ReflectionUtils.invokeMethod(jugador, lectura);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
