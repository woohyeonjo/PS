package Chapter_13;

public class E04Util {
	public static <K, V> boolean compare(E04Pair<K, V> p1, E04Pair<K, V> p2){
		boolean keyCompare = p1.getKey().equals(p2.getKey());
		boolean valueCompare = p1.getValue().equals(p2.getValue());
		return keyCompare && valueCompare;
	}
}
