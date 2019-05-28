package Chapter_13;

public class E07StorageImpl<T> implements E07Storage<T>{
	private T[] array;
	
	public E07StorageImpl(int capacity){
		this.array = (T[]) (new Object[capacity]);
	}
	
	@Override
	public void add(T item, int index){
		array[index] = item;
	}
	
	@Override
	public T get(int index){
		return array[index];
	}
}
