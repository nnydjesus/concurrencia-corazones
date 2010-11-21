package utils.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.jugador.Jugador;

/**
 * @author Ronny
 *
 */
public class Serializable {
	public final static String ISO = "ISO8859-1";
	
	public static String deObjetoAString(java.io.Serializable unObjetoSerializable){
		ByteArrayOutputStream bs= new ByteArrayOutputStream();
		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream (bs);
			os.writeObject(unObjetoSerializable);  // this es de tipo DatoUdp
			os.close();
			return new String (bs.toByteArray(), ISO);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static Object deStringAObjeto(String string){
		ObjectInputStream is;
		Object unObjetoSerializable = null;
		try {
			ByteArrayInputStream bs= new ByteArrayInputStream(string.getBytes(ISO));
			is = new ObjectInputStream(bs);
			unObjetoSerializable = is.readObject();
			is.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return unObjetoSerializable;

	}
	
	public static void main(String[] args) {
		Jugador c =new Jugador();
		String s = deObjetoAString(c);
		System.out.println(s);
		System.out.println(deStringAObjeto(s));
	}

}
