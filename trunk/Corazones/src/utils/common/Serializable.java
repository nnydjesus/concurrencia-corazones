package utils.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.carta.Carta;

public class Serializable {
	public final static String ISO = "ISO8859-1";
	
	public static String deObjetoAByte(java.io.Serializable unObjetoSerializable){
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
		Carta unObjetoSerializable = null;
		try {
			ByteArrayInputStream bs= new ByteArrayInputStream(string.getBytes(ISO)); // bytes es el byte[]
			is = new ObjectInputStream(bs);
			unObjetoSerializable = (Carta)is.readObject();
			is.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return unObjetoSerializable;

	}
	
	public static void main(String[] args) {
		Carta c =new Carta();
		String s = deObjetoAByte(c);
		System.out.println(s);
		System.out.println(deStringAObjeto(s));
	}

}
