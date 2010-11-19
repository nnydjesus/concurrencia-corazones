package model.carta;

/* Esta clase representa a las 52 cartas del mazo. Su estructura interna 
 * es un vector de 52 cartas.
 */

//public final class Mazo {
public class Mazo {
	/* Atributos */
	public final static int NUM_CARTAS= 52;
	public final static int NUM_PALOS= Palo.values().length;
	public final static int NUM_CARTA_POR_MAZO = NUM_CARTAS / NUM_PALOS;
	private Carta[] cartas;


	/* Construye un vector de 52 lugares */
	public Mazo(Boolean conComodines) {
		cartas = new Carta[NUM_CARTAS];
		for (int i = 0; i < NUM_CARTAS; i++) {
			cartas[i] = new Carta();
		}
		cargar_mazo();
	}

	public void cargar_mazo() {
		int i, palo, cont;
		cont = 0;
		for (palo = 1; palo < NUM_PALOS; palo++) {
			for (i = 0; i < NUM_CARTA_POR_MAZO; i++) {
				cartas[cont].setPalo(Palo.values()[palo]);
				cartas[cont].setNumero(i + 1);
				cont++;
			}
		}

	}

	public Carta devolverCarta(int n) {
		return (cartas[n]);
	}

	public static void main(String[] args) {
		Mazo mazo = new Mazo(false);
		for (Carta carta : mazo.cartas) {
			System.out.println(carta);
		}
	}
}
