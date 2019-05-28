package Chapter_13;

public class E04Pair<K, V> {
	private K key;
	private V value;
	
	public E04Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public void setKey(K key) { this.key = key; }
	public void setValue(V value) { this.value = value; }
	public K getKey() { return key; }
	public V getValue() { return value; }
}
