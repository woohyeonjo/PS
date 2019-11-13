package BOJ;


import java.util.*;

public class Main {
	
	static LinkedList<Integer> ll = new LinkedList<>();
	public static void main(String[] args){
		ll.add(1);
		print();
		ll.addFirst(2);
		print();
		ll.addLast(3);
		print();
		System.out.println(ll.peek());
		
		
    }
	
	static public void print() {
		for(Integer i : ll) System.out.print(i + " ");
		System.out.println();
	}
}