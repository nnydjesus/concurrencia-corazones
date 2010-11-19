package model.jugador;

import model.carta.Carta;
import model.carta.Palo;

public final class Pozojugador {
	private int puntaje = 0;

	public Pozojugador() {
	}

	public void llevar_al_pozo(Carta[] cartas) {
		Carta c;
		for (Carta carta : cartas) {			
			if (carta.getPalo() == Palo.CORAZON) {
				puntaje++;
			}
		}
	}

	public int devolver_puntaje() {
		return this.puntaje;
	}
}