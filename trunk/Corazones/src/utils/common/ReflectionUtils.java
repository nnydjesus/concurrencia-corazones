package utils.common;


 
/**
 * @author Ronny
 *
 */
public class ReflectionUtils {

	protected static Object privateInvokeMethod(Object model, String actionName) {
		try {
			return model.getClass().getMethod(actionName, new Class[]{}).invoke(model, new Object[]{});
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected static void privateInvokeVoidMethod(Object model, String actionName, Object... args) {
		try {
			model.getClass().getMethod(actionName, Object.class).invoke(model, args);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public static void invokeMethod(Object jugador, String lectura) {
		String[] split;
		if (lectura.contains("&")) {
			split = lectura.split("&");			
			privateInvokeVoidMethod(jugador, split[0], split[1]);
		}else if (lectura.contains("#")) {			
			split = lectura.split("#");			
			privateInvokeVoidMethod(jugador, split[0], utils.common.Serializable.deStringAObjeto(split[1]));
		}else 
			privateInvokeMethod(jugador, lectura);
	}	

}
