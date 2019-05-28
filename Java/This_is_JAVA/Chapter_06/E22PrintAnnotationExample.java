package Chapter_06;

import java.lang.reflect.Method;
import java.security.Provider.Service;

public class E22PrintAnnotationExample {
	public static void main(String args[]){
		Method[] declaredMethods = Service.class.getDeclaredMethods();
		
		for(Method method : declaredMethods){
			if(method.isAnnotationPresent(E22PrintAnnotation.class)){
				E22PrintAnnotation printAnnotation = method.getAnnotation(E22PrintAnnotation.class);
				
				System.out.println("[" + method.getName() + "]");
				
				for(int i = 0 ; i < printAnnotation.number() ; i++){
					System.out.print(printAnnotation.value());
				}
				System.out.println();
				
				try{
					method.invoke(new E22Service());
				} catch (Exception e) {
					System.out.println();
				}
			}
		}
	}
}
