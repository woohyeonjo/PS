package BOJ;


import java.util.Scanner;

public class B5052_전화번호목록 {
	
	static class Node {
		int data;
		Node[] children;
		
		Node(){ children = new Node[10]; }
		Node(int data){
			this.data = data;
			children = new Node[10];
		}
		
		public Node getChild(int index){
			return children[index];
		}
		
		public void setChild(int index, Node node){
			children[index] = node;
		}
	}
	
	static class Trie {
		Node root;
		
		Trie(){ root = new Node(); }
		
		public void insert(String key){
			Node now = root;
			
			for(int i = 0 ; i < key.length() ; ++i){
				int index = key.charAt(i) - '0';
				
				if(now.getChild(index) == null){
					Node temp = new Node(index);
					now.setChild(index, temp);
					now = temp;
				} else {
					now = now.getChild(index);
				}
			}
		}
		
		public boolean isEnd(String key){
			Node now = root;
			
			for(int i = 0 ; i < key.length() ; ++i){
				int index = key.charAt(i) - '0';
				now = now.getChild(index);
			}
			
			for(int i = 0 ; i < 10 ; ++i){
				if(now.getChild(i) != null) return false;
			}
			
			return true;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 0 ; t < T ; ++t){
			int N = sc.nextInt();
			
			Trie trie = new Trie();
			String[] numbers = new String[N];
			String ans = "YES";
			
			for(int i = 0 ; i < N ; ++i) {
				numbers[i] = sc.next();
				trie.insert(numbers[i]);
			}
			
			for(int i = 0 ; i < N ; ++i){
				if(!trie.isEnd(numbers[i])){
					ans = "NO";
					break;
				}
			}
			System.out.println(ans);
		}
	}
}
