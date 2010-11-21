package model.juego;

import java.io.BufferedReader;
import java.io.PrintStream;

public class Contenedor {
	private  BufferedReader serverIn;
	private  PrintStream clienteOut;
	
	public Contenedor(BufferedReader server, PrintStream client) {
		this.setServerIn(server);
		this.setClienteOut(client);
	}

	public void setServerIn(BufferedReader serverIn) {
		this.serverIn = serverIn;
	}

	public BufferedReader getServerIn() {
		return serverIn;
	}

	public void setClienteOut(PrintStream clienteOut) {
		this.clienteOut = clienteOut;
	}

	public PrintStream getClienteOut() {
		return clienteOut;
	}

}
