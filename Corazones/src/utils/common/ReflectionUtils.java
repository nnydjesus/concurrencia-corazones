package utils.common;

 
/**
 * @author Ronny
 *
 */
public class ReflectionUtils {

	public static Object invokeMethod(Object model, String actionName) {
		try {
			return model.getClass().getMethod(actionName, new Class[]{}).invoke(model, new Object[]{});
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void invokeMethod(Object model, String actionName, Object... args) {
		try {
			model.getClass().getMethod(actionName, model.getClass()).invoke(model, args);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
