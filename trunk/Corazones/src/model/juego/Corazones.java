package model.juego;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import model.carta.Carta;
import model.carta.Mazo;
import model.carta.Mesa;
import model.carta.Palo;
import utils.common.Serializable;

/*
 * Clase que representaria a una pantalla.... muy en futuro....
 */
public final class Corazones {
	// private Juego juego;
	private Map<Integer, Contenedor> players = new HashMap<Integer, Contenedor>();
	private Mesa mesa;
	private static final int CANT_JUGADORES_MAX = 4; // Depende de la cantidad
	// de Jugadores
	public int jugRec = 0;
	private Mazo mazo;
	private int jugadorQueEmpieza;

	public Corazones(Mesa mesa, int cantJugadores, Mazo mazo) {
		super();
		setMesa(mesa);
		this.mazo = mazo;
		this.getMesa().setCantJugadores(
				Math.max(cantJugadores, CANT_JUGADORES_MAX));
	}

	protected void init() {
		this.repartir_cartas();
		//this.seleccionar3Cartas();
		this.initRonda();
	}

	public void initRonda() {
		System.out.println("asfsadfsdsafds");
		try {
			jugadorQueEmpieza = this.buscar2DeTrebol();
			System.out.println("Jugador que empieza " +jugadorQueEmpieza);
			for (int i = 0; i < Mazo.NUM_CARTA_POR_MAZO; i++) {
				this.ronda();
				jugadorQueEmpieza = this.jugadorCartaMasAlta();
				this.llevarPozo();
				this.limpiarMeza();
				mostrarRonda();
			}
			if (!this.terminoElJuego()) {
				this.init();
			} else {
				mostrarGanador();
			}

		} catch (IOException e) {
			new RuntimeException(e);
		}

	}

	public void ronda() throws IOException {
		for (int i = 0; i < Juego.cantJugadores; i++) {
			Contenedor contenedor = players.get((jugadorQueEmpieza + i)
					% this.cantJugadores());
			contenedor.getClienteOut().println("tirarCartaAMesa");
			Carta carta = (Carta) Serializable.deStringAObjeto(contenedor
					.getServerIn().readLine());
			System.out.println((jugadorQueEmpieza + i) % this.cantJugadores()
					+ "tiro " + carta);
			mesa.insertElementAt(carta, i);
		}
	}

	/*
	 * Reparte todas las cartas del maso en las 4 barajas.
	 */
	public void repartir_cartas() {
		boolean[] conjunto = new boolean[Mazo.NUM_CARTAS];
		int numMazo;
		int pos = 0;
		Carta carta;
		for (int i = 0; i < Juego.cantJugadores ; i++) {
			while (pos < Mazo.NUM_CARTA_POR_MAZO) {
				numMazo = elegir_numero();
				//if (!conjunto[numMazo]) {
					carta = mazo.devolverCarta(numMazo);
					String cartaDeArgumento = Serializable.deObjetoAString(carta);
					players.get(i).getClienteOut().println("recibirCarta#" + cartaDeArgumento);
					pos++;
				//}
				conjunto[numMazo] = true;
			}
			pos=0;
			System.out.println("for");
		}	

	}

	public void seleccionar3Cartas() {
		int i = 0;
		for (Contenedor servers : this.players.values()) {
			servers.getClienteOut().println("elegir3Cartas");
			try {
				mesa.ingresar3Cartas((Carta[]) Serializable
						.deStringAObjeto(servers.getServerIn().readLine()), i);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			i++;
		}
	}

	/*
	 * devuelve el jugador que tiene el 2 de treblo
	 */
	public Integer buscar2DeTrebol() throws IOException {
		Integer jugador = 0;
		for (Integer player : players.keySet()) {
			players.get(player).getClienteOut().println("tengo2DeTrebol");
			if (Boolean.getBoolean(players.get(player).getServerIn().readLine())) {
				jugador = player;
				break;
			}
		}
		return jugador;
	}

	/* devuelve un numero al azar de 0 a 51 */
	private int elegir_numero() {
		return (int) ((double) Math.random() * Mazo.NUM_CARTAS-1);
	}

	public boolean terminoElJuego() throws NumberFormatException, IOException {
		for (Contenedor contenedor : players.values()) {
			contenedor.getClienteOut().println("getPuntos");
			if (Integer.parseInt(contenedor.getServerIn().readLine()) >= 30) {
				return true;
			}
		}
		return false;
	}

	public void mostrarRonda() throws IOException {
		for (Integer key : players.keySet()) {
			Contenedor contenedor= players.get(key);
			contenedor.getClienteOut().println("getPuntos");
			System.out.println(key+": " +contenedor.getServerIn().readLine());
		}
	}
	
	public void mostrarGanador() throws IOException {
		int ganador =1000; ///Esto se tiene que cambiar!!!
		Integer nombreJugador = null;
		for (Integer key : players.keySet()) {
			Contenedor contenedor= players.get(key);
			contenedor.getClienteOut().println("getPuntos");
			int puntageJugador = Integer.parseInt(contenedor.getServerIn().readLine());
			System.out.println("El Jugador:" +key+" tubo: "+puntageJugador);
			if(ganador >puntageJugador){
				ganador=puntageJugador;
				nombreJugador = key;
			}
		}
		System.out.println("El Ganador es: "+nombreJugador+ "con: " + ganador+" puntos");
	}

	public void limpiarMeza() {
		mesa.clear();
	}

	public void llevarPozo() {
		int i = 0;
		for (Carta carta : mesa) {
			if (carta.getPalo().equals(Palo.CORAZON)) {
				i++;
			}
		}
		this.players.get(this.jugadorQueEmpieza).getClienteOut().println(
				"incrementarPuntaje&" + i);
		System.out.println("el jugador: "+jugadorQueEmpieza+ "se llevo: " + i);
	}

	public int jugadorCartaMasAlta() {
		return mesa.numJugadorCartaMasAlta(jugadorQueEmpieza);
	}

	public int cantJugadores() {
		return this.getMesa().getCantJugadores();
	}

	public Set<Integer> getPlayers() {
		return players.keySet();
	}

	public void addPlayers(int key, Contenedor contenedor) {
		this.players.put(key, contenedor);
		jugRec++;

	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

}
