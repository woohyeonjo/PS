package BOJ.go;

import java.util.Scanner;

public class B15686_치킨배달 {
	
	static class Node {
		int r, c, type;
		int chickenDistance;
		
		public Node(int r, int c, int type) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
		}
		
		@Override
		public String toString() {
			return "[" + this.r + ", " + this.c + "]";
		}
	}
	
	static Node[][] map;
	static Node[] storeList;
	static int storeCnt;
	static Node[] selected;
	static int N, M, ans, current;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		ans = Integer.MAX_VALUE;
		current = 0;
		storeCnt = 0;
		
		map = new Node[N + 1][N + 1];
		storeList = new Node[13];
		
		int type = 0;
		for(int r = 1 ; r <= N ; ++r){
			for(int c = 1 ; c <= N ; ++c){
				type = sc.nextInt();
				map[r][c] = new Node(r, c, type);
				if(type == 2) storeList[storeCnt++] = map[r][c];
			}
		}
		
		for(int m = 1 ; m <= M ; ++m){
			selected = new Node[m];
			select(m, 0, 0);
		}
		
		System.out.println(ans);
	}

	private static void select(int amount, int depth, int index) {
		
		if(depth == amount) {
			current = findChickenDistance(amount);
			ans = ans > current ? current : ans;
			return;
		}
		
		for(int i = index ; i < storeCnt ; ++i){
			selected[depth] = storeList[i];
			select(amount, depth + 1, i + 1);
		}
	}

	private static int findChickenDistance(int amount) {
		int result = 0;
		
		int temp = 0;
		int distance;
		for(int r = 1 ; r <= N ; ++r){
			for(int c = 1 ; c <= N ; ++c){
				distance = Integer.MAX_VALUE;
				if(map[r][c].type == 1){
					for(int i = 0 ; i < amount ; ++i){
						temp = findDistance(map[r][c], selected[i]);
						distance = distance > temp ? temp : distance;
					}
					result += distance;
				}
			}
		}
		
		return result;
	}

	private static int findDistance(Node node, Node node2) {
		return Math.abs(node.r - node2.r) + Math.abs(node.c - node2.c);
	}
}
