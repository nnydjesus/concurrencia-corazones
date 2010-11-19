package model.juego;

import model.carta.Mesa;
import model.jugador.Jugador;

/*
 * Clase que representaria a una pantalla.... muy en futuro....
 */
public final class Corazones extends Juego{
	//private Juego juego;
	private Jugador[] players;
	private Mesa mesa;
	private static final int CANT_JUGADORES_MAX = 4; // Depende de la cantidad de Jugadores
	public int jugRec = 0;
	
	
	public Jugador[] getPlayers() {
		return players;
	}

	public void setPlayers(Jugador players) {
		//if (jugRec< this.cantJugadores){
			this.players[jugRec] = players;
			jugRec++;
		
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa ;
	}

	public Corazones(Mesa mesa, int cantJugadores) {
		super();
		setMesa(mesa);
		this.getMesa().setCantJugadores(Math.max(cantJugadores, CANT_JUGADORES_MAX));
	}
	
	public int cantJugadores(){
		return this.getMesa().getCantJugadores();
	}
	
/*

	public void mostrarPuntaje() {
		String tex = "";
		tex += juego.getJugador1().getNombre() + "\n";
		tex += juego.getJugador1().getPuntaje() + "\n";
		tex += juego.getJugador2().getNombre() + "\n";
		tex += juego.getJugador2().getPuntaje() + "\n";
		tex += juego.getJugador3().getNombre() + "\n";
		tex += juego.getJugador3().getPuntaje() + "\n";
		tex += juego.getHumano().getNombre() + "\n";
		tex += juego.getHumano().getPuntaje() + "\n";

	}

	public static void main(String[] args) {
		Corazones tablero = new Corazones();
		new Juego(tablero);
	}
*/
}
