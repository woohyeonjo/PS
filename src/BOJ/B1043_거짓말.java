package BOJ;


import java.util.ArrayList;
import java.util.Scanner;

public class B1043_거짓말 {
	
	static int[] parent;
	static int known;
	static ArrayList<Integer>[] party;
	static ArrayList<Integer> list;
	static int N, M, ans, temp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		parent = new int[N + 1];
		list = new ArrayList<>();
		party = new ArrayList[M];
		ans = 0;
		
		for(int i = 1 ; i <= N ; ++i) parent[i] = i;
		
		temp = sc.nextInt();
		int person = 0;
		for(int i = 0 ; i < temp ; ++i) {
			person = sc.nextInt();
			known = person;
			list.add(person);
		}
		union(list);
		
		for(int i = 0 ; i < M ; ++i) {
			temp = sc.nextInt();
			party[i] = new ArrayList<>();
			for(int j = 0 ; j < temp ; ++j) {
				person = sc.nextInt();
				list.add(person);
				party[i].add(person);
			}
			union(list);
		}
		
//		for(int i = 1 ; i <= N ; ++i) System.out.print(parent[i] + " ");
//		System.out.println();
		
OUTER : for(int i = 0 ; i < M ; ++i) {
			for(Integer p : party[i]) if(find(p) == find(known)) continue OUTER;
			ans++;
		}
		
		System.out.println(ans);
		
	}
	
	private static void union(ArrayList<Integer> list) {
		for(int i = 1 ; i < list.size() ; ++i) {
			union(list.get(0), list.get(i));
		}
		list.clear();
	}
	
	private static void union(int x, int y) {
		int root_x = find(x);
		int root_y = find(y);
		
		if(root_x != root_y) {
			parent[root_y] = root_x;
		}
	}

	private static int find(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = find(parent[x]);
	}
}
