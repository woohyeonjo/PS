package Chapter_13;

public class E03Util {
	public static <T> E01Box<T> boxing(T t){
		E01Box<T> box = new E01Box<>();
		box.set(t);
		return box;
	}
}
