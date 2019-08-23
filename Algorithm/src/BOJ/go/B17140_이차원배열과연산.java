package BOJ.go;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class B17140_이차원배열과연산 {
	static class Node implements Comparable<Node> {
		int number, count;

		public Node(int number, int count) {
			super();
			this.number = number;
			this.count = count;
		}

		@Override
		public int compareTo(Node o) {
			if(this.count == o.count) return this.number - o.number;
			else return this.count - o.count;
		}
	}
	
	static PriorityQueue<Node> q;
	static int[][] map;
	static int R, C, K;
	static int time, sizeR, sizeC;
	static final int SIZE = 101;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		K = sc.nextInt();
		
		q = new PriorityQueue<>();
		map = new int[SIZE][SIZE];
		time = 0;
		sizeR = 3;
		sizeC = 3;
		
		for(int r = 1 ; r <= 3 ; ++r){
			for(int c = 1 ; c <= 3 ; ++c){
				map[r][c] = sc.nextInt();
			}
		}
		
		while(true) {
			if(map[R][C] == K) break;
			time++;
			if(time > 100){
				time = -1;
				break;
			}
			
			if(sizeR >= sizeC) {
				func_R();
			}
			else func_C();
		}
		
		System.out.println(time);
	}

	private static void func_R() {
		for(int r = 1 ; r <= sizeR ; ++r){
			int[] temp = new int[sizeC + 1];
			
			for(int c = 1 ; c <= sizeC ; ++c){
				temp[c] = map[r][c];
			}
			
			Arrays.sort(temp);
			
			Node node = null;
			for(int c = 1 ; c <= sizeC ; ++c) {
				if(temp[c] == 0) continue;
				if(node == null){
					node = new Node(temp[c], 1);
				} else if(node.number != temp[c]) {
					q.offer(node);
					node = new Node(temp[c], 1);
				} else if (node.number == temp[c]){
					node.count++;
				}
			}
			q.offer(node);
			
			if(q.size() * 2 > sizeC) sizeC = q.size() * 2;
			
			int indexC = 1;
			while(!q.isEmpty()){
				Node pick = q.poll();
				
				map[r][indexC] = pick.number;
				map[r][indexC + 1] = pick.count;
				indexC += 2;
				
				if(indexC > 100) q.clear();
			}
			if(indexC < 100){
				for(int i = indexC ; i <= 100 ; ++i){
					map[r][i] = 0;
				}
			}
		}
	}

	private static void func_C() {
		for(int c = 1 ; c <= sizeC ; ++c){
			int[] temp = new int[sizeR + 1];
			
			for(int r = 1 ; r <= sizeR ; ++r){
				temp[r] = map[r][c];
			}
			
			Arrays.sort(temp);
			
			Node node = null;
			for(int r = 1 ; r <= sizeR ; ++r) {
				if(temp[r] == 0) continue;
				if(node == null){
					node = new Node(temp[r], 1);
				} else if(node.number != temp[r]) {
					q.offer(node);
					node = new Node(temp[r], 1);
				} else if (node.number == temp[r]){
					node.count++;
				}
			}
			q.offer(node);
			
			if(q.size() * 2 > sizeR) sizeR = q.size() * 2;
			
			int indexR = 1;
			while(!q.isEmpty()){
				Node pick = q.poll();
				
				map[indexR][c] = pick.number;
				map[indexR + 1][c] = pick.count;
				indexR += 2;
				
				if(indexR > 100) q.clear();
			}
			if(indexR < 100){
				for(int i = indexR ; i <= 100 ; ++i){
					map[i][c] = 0;
				}
			}
		}
	}
}
