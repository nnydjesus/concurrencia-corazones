package model.carta;

import utils.common.Serializable;

/* Esta clase representa a las 52 cartas del mazo. Su estructura interna 
 * es un vector de 52 cartas.
 */

//public final class Mazo {
public class Mazo {
	/* Atributos */
	public final static int NUM_CARTAS= 36;
	public final static int NUM_PALOS= Palo.values().length-1;
	public final static int NUM_CARTA_POR_MAZO = NUM_CARTAS / NUM_PALOS;
	private Carta[] cartas;


	/* Construye un vector de 52 lugares */
	public Mazo() {
		cartas = new Carta[NUM_CARTAS];
		for (int i = 0; i < NUM_CARTAS; i++) {
			cartas[i] = new Carta();
		}
		cargar_mazo();
	}

	public void cargar_mazo() {
		int i, palo, cont;
		cont = 0;
		for (palo = 1; palo < NUM_PALOS+1; palo++) {
			for (i = 0; i < NUM_CARTA_POR_MAZO; i++) {//si se crea una carta con 10 o mas pincha al desserializar!!
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
		Mazo mazo = new Mazo();
		for (Carta carta : mazo.cartas) {
			System.out.println(carta);
			String deObjetoAString = Serializable.deObjetoAString(carta);
			System.out.println(deObjetoAString);
			System.out.println(Serializable.deStringAObjeto(deObjetoAString));
			
		}
	}
}
