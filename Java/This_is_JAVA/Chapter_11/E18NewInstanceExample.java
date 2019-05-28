package Chapter_11;

public class E18NewInstanceExample {
	public static void main(String args[]){
		try{
			//Class clazz = Class.forName("Chapter_11.E18SendAction");
			Class clazz = Class.forName("Chapter_11.E18ReceiveAction");
			E18Action action = (E18Action) clazz.newInstance();
			action.execute();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (InstantiationException e){
			e.printStackTrace();
		} catch (IllegalAccessException e){
			e.printStackTrace();
		}
	}
}
