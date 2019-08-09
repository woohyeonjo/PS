package SWEA.go;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class S1238 {
	
	static HashSet<Integer>[] adj;
	static boolean[] isContact;
	static Queue<Integer> queue;
	static ArrayList<Integer> temp;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		for(int t = 1 ; t <= 10 ; ++t) {
			int size = sc.nextInt();
			int startNode = sc.nextInt();
			int result = 0;	
			
			adj = (HashSet<Integer>[]) new HashSet[size + 1];
			isContact = new boolean[size + 1];
			queue = new LinkedList<Integer>();
			
			for(int i = 0 ; i < size / 2 ; ++i) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				
				if(adj[from] == null) {
					adj[from] = new HashSet<Integer>();
				}
				adj[from].add(to);
			}
			
			queue.offer(startNode);

			while(!queue.isEmpty()) {
				temp = new ArrayList<Integer>();
				result = Collections.max(queue);
				while(!queue.isEmpty()) {
					int parent = queue.poll();
					if(adj[parent] != null) {
						for(int child : adj[parent]) {
							if(!isContact[child]) {
								isContact[child] = true;
								temp.add(child);
							}
						}
					}
				}
				
				for(int q = 0 ; q < temp.size() ; ++q) {
					queue.add(temp.get(q));
				}
			}
			System.out.println("#" + t + " " + result);
			
		}
	}
}
