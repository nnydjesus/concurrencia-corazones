package model.jugador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import model.carta.Carta;
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

public class Jugador {
	/* Atributos */
	private String nombre;
	private int puntaje;
	private Pozojugador pozo;
	private  Carta[] mano =  new Carta[13];
	int x = 12;
	private static BufferedReader serverIn;
	private static PrintStream clienteOut;

	/* Constructor */
	public Jugador(String name) {
		nombre = name;
		puntaje = 0;
		pozo = new Pozojugador();
		cargar();
	}
	
	public void cargar(){
		mano[0] = new Carta();
		mano[1] = new Carta();
		mano[2] = new Carta();
	}

	public void inicializarPuntaje() {
		puntaje = 0;
	}

	/*
	 * Cambia el nombre del jugador actual por el ingresado como par�metro
	 */
	public void cambiar_nombre(String s) {
		nombre = s;
	}

	/*
	 * Incrementa el puntaje del jugador en "n" unidades.
	 */
	public void incrementar_puntaje(int n) {
		puntaje += n;
	}

	/*
	 * Incrementa el puntaje del jugador con los puntos que acarre� en el pozo.
	 */
	public void calcular_puntaje() {
		puntaje += pozo.devolver_puntaje();
	}

	


	/* Envia a la baza la carta mas conveniente */
	public void tirar_carta_AMesa() {
		clienteOut.println(Serializable.deObjetoAByte(this.mano[0]));
	}

	public Carta[] getMano() {
		return mano;
	}


	public void elegir3Cartas() {
		clienteOut.println(Serializable.deObjetoAByte(mano[0]));
		clienteOut.println(Serializable.deObjetoAByte(mano[1]));
		clienteOut.println(Serializable.deObjetoAByte(mano[2]));
	}

	 /* Inserta una carta "c" dada en la posicion "i" dada */
    public void insertar_carta(Carta c, int i){
			mano[i]= c;
    }

    
	/*
	 * Devuelve true si en la baraja se encuentran cartas de un "palo" dado.
	 */
	public boolean hay_cartas_delPalo(Palo palo) {
		for (Carta carta : this.mano) {
			if (carta.getPalo().equals(palo)) {
				return true;
			}
		}
		return false;
	}
	
	
	public String getPuntaje() {
		return Integer.toString(puntaje);
	}

	public int getPuntos() {
		clienteOut.println(puntaje);
		return puntaje;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Pozojugador getPozo() {
		return pozo;
	}

	public void setPozo(Pozojugador pozo) {
		this.pozo = pozo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	 

	public static void main(java.lang.String argv[]) {
		try {
			Jugador jugador = new Jugador("");
			//System.out.println(ReflectionUtils.invokeMethod(jugador, "getPuntos")); //convencion solo se manda 1 parametro);
			Socket socket = new Socket("localhost",9999);
			clienteOut = new PrintStream(socket.getOutputStream());
			serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while(true){
				String lectura = serverIn.readLine();
				String[] split = lectura.split("!");
				if(split.length >1){
					ReflectionUtils.invokeMethod(jugador, split[0],split[1]); //convencion solo se manda 1 parametro
				}else
					ReflectionUtils.invokeMethod(jugador, lectura); //convencion solo se manda 1 parametro
				
			}
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}	
	
}
