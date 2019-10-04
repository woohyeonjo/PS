package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class B16235 {
	static ArrayList<Tree>[][] ground;
	static int[][] soil;
	static int[][] nutrition;
	static ArrayList<Tree>treeList;
	static int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1},
						  {0, -1},  		 {0, 1},
						  {1, -1},  {1, 0},  {1, 1}};
	static class Tree implements Comparable<Tree> {
		int age;
		boolean isDead;
		
		public Tree(int age, boolean isDead) {
			this.age = age;
			this.isDead = isDead;
		}
		
		@Override
		public String toString() {
			return this.age + "";
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}
	static int N, M, K, ans;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line = in.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		K = Integer.parseInt(line[2]);
		
		ground = new ArrayList[N + 1][];
		for(int i = 1 ; i <= N ; ++i) {
			ground[i] = new ArrayList[N + 1];
		}
		
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= N ; ++c) {
				ground[r][c] = new ArrayList<Tree>();
			}
		}
		
		soil = new int[N + 1][N + 1];
		nutrition = new int[N + 1][N + 1];
		ans = M;
		
		for(int r = 1 ; r <= N ; ++r) {
			line = in.readLine().split(" ");
			for(int c = 1 ; c <= N ; ++c) {
				soil[r][c] = 5;
				nutrition[r][c] = Integer.parseInt(line[c - 1]);
			}
		}
		
		for(int i = 0 ; i < M; ++i) {
			line = in.readLine().split(" ");
			ground[Integer.parseInt(line[0])][Integer.parseInt(line[1])].
					add(new Tree(Integer.parseInt(line[2]), false));
		}
		
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= N ; ++c) {
				Collections.sort(ground[r][c]);
			}
		}
		
		
		for(int k = 0 ; k < K ; ++k) {
			spring_summer();
			autumn();
			winter();
		}
		System.out.println(ans);
		
	}
	
	private static void spring_summer() {
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= N ; ++c) {
				int size = ground[r][c].size();
				for(int i = 0 ; i < size ; ++i) {
					Tree t = ground[r][c].get(i);
					if(soil[r][c] >= t.age) {
						soil[r][c] -= t.age;
						t.age++;
					} else {
						for(int j = i ; j < size ; ++j ) {
							t = ground[r][c].get(j);
							soil[r][c] += (t.age / 2);
							ground[r][c].remove(j);
							size--;
							j--;
							ans--;
						}
						break;
					}
				}
			}
		}
	}

	private static void autumn() {
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= N ; ++c) {
				int size = ground[r][c].size();
				for(int i = 0 ; i < size ; ++i) {
					Tree t = ground[r][c].get(i);
					int nr, nc;
					if(t.age % 5 == 0) {
						for(int j = 0 ; j < 8 ; ++j) {
							nr = r + dir[j][0];
							nc = c + dir[j][1];
							if(nr > N || nr < 1 || nc > N || nc < 1) continue;
							ground[nr][nc].add(0, new Tree(1, false));
							ans++;
						}
					}
				}
			}
		}
	}

	private static void winter() {
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= N ; ++c) {
				soil[r][c] = soil[r][c] + nutrition[r][c];
			}
		}
	}
}
