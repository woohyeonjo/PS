package DS;

import java.util.LinkedList;

public class 동시삭제 {
	
	private static class Node {
	      Object data;
	      Node left, right;

	      Node() {
	         left = right = null;
	      }

	      Node(Object data) {
	         this.data = data;
	         left = right = null;
	      }
	   }



	   private static class KeroList {
	      Node header;
	      int size;
	      
	      KeroList() {
	         this.header = new Node();
	         size = 0;
	      }

	      Node get(int num) {
	         if (num >= size) {
	            return new Node(-1);
	         }
	         if (num > size >> 1) {
	            Node n = header.right;
	            for (int i = 0; i < size - num - 1; i++) {
	               n = n.left;
	            }
	            return n;
	         } else {
	            Node n = header.left;
	            for (int i = 0; i < num; i++) {
	               n = n.right;
	            }
	            return n;
	         }
	      }

	      boolean isEmpty() {
	         return size == 0;
	      }

	      void addFirst(Node n) {
	         header.left = n;
	         header.right = n;
	         size++;
	      }

	      void add(Node n) {
	         if (size == 0) {
	            addFirst(n);
	            return;
	         }
	         
	         n.left = header.right;
	         header.right.right = n;
	         header.right = n;
	         size++;
	      }
	      
	      void add(Node n, int num) {
	         if (size == 0) {
	            addFirst(n);
	            return;
	         }
	         
	         n.left = header.right;
	         header.right.right = n;
	         header.right = n;
	         size++;
	      }
	      
	      

	      void remove(int index) {
	         if (index >= size) {
	            return;
	         }
	         if (index == 0) {
	            removeFirst();
	            return;
	         }

	         if (index == size - 1) {
	            removeLast();
	            return;
	         }

	         if (size == 1) {
	            header = new Node();
	            this.size--;
	            return;
	         }

	         Node temp = get(index);

	         temp.left.right = temp.right;
	         temp.right.left = temp.left;

	         this.size--;
	      }

	      void removeFirst() {
	         Node head = header.left;
	         head = head.right;
	         if (head != null) {
	            head.left = null;
	         }
	         header.left = head;
	         size--;
	      }

	      void removeLast() {
	         Node tail = header.right;
	         tail = tail.left;
	         if (tail != null) {
	            tail.right = null;
	         }
	         header.right = tail;
	         size--;
	      }

	      Node poll() {
	         Node n = header.left;
	         removeFirst();
	         return n;
	      }

	      Node pop() {
	         Node n = header.right;
	         removeLast();
	         return n;
	      }
	   }
	   static KeroList keroList1 = new KeroList();
	   static KeroList keroList2 = new KeroList();
	   
	   
	   
	   public static void main(String[] args) {
	      for(int i = 1; i <= 10; i++) {
	         Node n = new Node(i);
	         keroList1.add(n);
	         keroList2.add(n);
	      }
	      print();
//	      keroList1.remove(3);
//	      print();
	      keroList1.remove(1);
	      print();
//	      keroList1.remove(5);
//	      print();
	      
	   }

	   private static void print() {
	      System.out.println("keroList1 : ");
	      System.out.println("size : " + keroList1.size);
	      for (int i = 0; i < keroList1.size; i++) {
	         System.out.print(keroList1.get(i)+ " ");
	      }
	      System.out.println();
	      System.out.println("keroList2 : ");
	      System.out.println("size : " + keroList2.size);
	      for (int i = 0; i < keroList2.size; i++) {
	         System.out.print(keroList2.get(i)+ " ");
	      }
	      System.out.println();
	      System.out.println("---------------------");
	   }

}
