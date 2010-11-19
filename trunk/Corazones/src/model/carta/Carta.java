package model.carta;

import java.io.Serializable;


public class Carta implements Serializable{
	/* Atributos */

	private Palo palo;
	private int numero;
	private boolean cambiada = false; // utilizado en el momento de cambiar 3
										// cartas

	public Carta() {
		palo = Palo.NADA;
		numero = 0;
	}

	
	public void setCambiada(boolean estado) {
		cambiada = estado;
	}

	/*
	 * Borra una carta, es decir, pone el palo como "blanco" y el numero como
	 * "O" y 0 respectivamente.
	 */
	public void borrar_carta() {
		palo = Palo.NADA;
		numero = 0;
		cambiada = false;
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
		cambiada = c.cambiada;
	}

	public boolean estaCambiada() {
		return cambiada;
	}
	
	@Override
	public String toString() {
		return this.numero + " de "+ this.palo;
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
