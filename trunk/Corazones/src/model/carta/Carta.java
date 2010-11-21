package model.carta;

import java.io.Serializable;

public class Carta implements Serializable {
	private static final long serialVersionUID = 1L;

	/* Atributos */
	private Palo palo;
	private int numero;

	// cartas

	public Carta() {
		palo = Palo.NADA;
		numero = 0;
	}

	public Carta(int num, Palo palo) {
		this.numero = num;
		this.palo = palo;
	}


	/*
	 * Borra una carta, es decir, pone el palo como "blanco" y el numero como
	 * "O" y 0 respectivamente.
	 */
	public void borrar_carta() {
		palo = Palo.NADA;
		numero = 0;
	}

	/*
	 * Funcion que determina si una carta es igual a otra c dada. Esta
	 * comparacion se realiza atributo por atributo
	 */
	public boolean igual(Carta c) {
		return ((palo == c.palo) && (numero == c.numero));
	}

	public boolean es2deTrebol() {
		return ((palo == Palo.TREBOL) && (numero == 2));
	}

	public boolean esReinaPicka() {
		return ((palo == Palo.PICKA) && (numero == 12));
	}

	/*
	 * Servicio que copia los atributos de una carta a otra
	 */
	public void copiar(Carta c) {
		numero = c.numero;
		palo = c.palo;
	}


	@Override
	public String toString() {
		return this.numero + " de " + this.palo;
	}

	public Palo getPalo() {
		return palo;
	}

	public void setPalo(Palo palo) {
		this.palo = palo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
