package Algorithm.SWEA.go;
//2
//1 0
//3 2
//1 2
//3 2

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

class Node{
	public int number;
	public ArrayList<Node> connected;
	
	Node(int i){
		this.number = i;
		connected = new ArrayList<Node>();
	}
	
	public void connect(Node node) {
		connected.add(node);
	}
}

public class S2814 {
	
	static Stack stack;
	static ArrayList<Node> nodeList;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int N, M, startPoint, endPoint;
		int distance;
		
		for(int t = 1 ; t <= T; ++t) {
			stack = new Stack();
			nodeList = new ArrayList<Node>();
			
			N = sc.nextInt();
			M = sc.nextInt();
			
			for(int n = 1 ; n <= N ; ++n) {
				nodeList.add(new Node(n));
			}
			
			for(int m = 0 ; m < M ; ++m) {
				startPoint = sc.nextInt();
				endPoint = sc.nextInt();
				nodeList.get(startPoint + 1).connect(nodeList.get(endPoint + 1));
			}
			
		}
	}
}
