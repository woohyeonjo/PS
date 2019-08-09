package SWEA.go;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class S5644 {

	static Queue<Cell> q;
	static int[][] dir = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static Cell[][] map;
	static int[] roadA;
	static int[] roadB;
	static int T, M, A, ans;

	static class BC implements Comparable<BC> {
		int c, p;

		public BC(int c, int p) {
			this.c = c;
			this.p = p;
		}

		@Override
		public String toString() {
			return this.p + "";
		}

		@Override
		public int compareTo(BC o) {
			return -(this.p - o.p);
		}

	}

	static class Cell {
		int r, c;
		ArrayList<BC> bcList;

		public Cell(int r, int c) {
			this.r = r;
			this.c = c;
			bcList = new ArrayList<BC>();
		}

		@Override
		public String toString() {
			return this.bcList.size() + "";
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		for (int t = 1; t <= T; ++t) {
			init(sc);
			go();

			System.out.println("#" + t + " " + ans);
		}
	}

	private static void go() {
		int ap = 0, bp = 0;
		if (map[1][1].bcList.size() > 0) {
			ap = map[1][1].bcList.get(0).p;
		}
		if (map[10][10].bcList.size() > 0) {
			bp = map[10][10].bcList.get(0).p;
		}
		int ar = 1, ac = 1;
		int br = 10, bc = 10;

		for (int i = 0; i < M; ++i) {
			ar += dir[roadA[i]][0];
			ac += dir[roadA[i]][1];
			br += dir[roadB[i]][0];
			bc += dir[roadB[i]][1];

			// A, B 둘다 충전 가능 지역일 때
			if (map[ar][ac].bcList.size() > 0 && map[br][bc].bcList.size() > 0) {
				// A, B 가 충전범위를 공유하지 않을 때
				if (!isSameCoverage(map[ar][ac], map[br][bc])) {
					ap += map[ar][ac].bcList.get(0).p;
					bp += map[br][bc].bcList.get(0).p;
				} else {
					// A, B가 충전범위를 공유할 때
					if (map[ar][ac].bcList.size() == 1 && map[br][bc].bcList.size() == 1) {
						ap += (map[ar][ac].bcList.get(0).p / 2);
						bp += (map[br][bc].bcList.get(0).p / 2);
					} else if (map[ar][ac].bcList.size() > 1 && map[br][bc].bcList.size() == 1) {
						if (map[ar][ac].bcList.get(0).p == map[br][bc].bcList.get(0).p) {
							ap += map[ar][ac].bcList.get(1).p;
							bp += map[br][bc].bcList.get(0).p;
						} else {
							ap += map[ar][ac].bcList.get(0).p;
							bp += map[br][bc].bcList.get(0).p;
						}
					} else if (map[ar][ac].bcList.size() == 1 && map[br][bc].bcList.size() > 1) {
						if (map[ar][ac].bcList.get(0).p == map[br][bc].bcList.get(0).p) {
							ap += map[ar][ac].bcList.get(0).p;
							bp += map[br][bc].bcList.get(1).p;
						} else {
							ap += map[ar][ac].bcList.get(0).p;
							bp += map[br][bc].bcList.get(0).p;
						}
					} else {
						if (map[ar][ac].bcList.get(0).p == map[br][bc].bcList.get(0).p) {
							if (map[ar][ac].bcList.get(0).p + map[br][bc].bcList.get(1).p > map[ar][ac].bcList.get(1).p
									+ map[br][bc].bcList.get(0).p) {
								ap += map[ar][ac].bcList.get(0).p;
								bp += map[br][bc].bcList.get(1).p;
							} else {
								ap += map[ar][ac].bcList.get(1).p;
								bp += map[br][bc].bcList.get(0).p;
							}
						} else {
							ap += map[ar][ac].bcList.get(0).p;
							bp += map[br][bc].bcList.get(0).p;
						}
					}
				}
				// A만 충전 가능 지역일 때
			} else if (map[ar][ac].bcList.size() > 0) {
				ap += map[ar][ac].bcList.get(0).p;

				// B만 충전 가능 지역일 때
			} else if (map[br][bc].bcList.size() > 0) {
				bp += map[br][bc].bcList.get(0).p;
			}
		}
		ans = ap + bp;
	}
	
	private static boolean isSameCoverage(Cell a, Cell b) {
		for(int i = 0 ; i < map[a.r][a.c].bcList.size() ; ++i) {
			if(map[b.r][b.c].bcList.contains(map[a.r][a.c].bcList.get(i))) return true;
		}
		for(int i = 0 ; i < map[b.r][b.c].bcList.size() ; ++i) {
			if(map[a.r][a.c].bcList.contains(map[b.r][b.c].bcList.get(i))) return true;
		}
		return false;
	}

	private static void init(Scanner sc) {
		M = sc.nextInt(); // 총 이동 시간
		A = sc.nextInt(); // BC의 개수

		roadA = new int[M];
		roadB = new int[M];
		map = new Cell[10 + 1][10 + 1];
		q = new LinkedList<Cell>();

		// BC 맵 생성
		for (int r = 1; r <= 10; ++r) {
			for (int c = 1; c <= 10; ++c) {
				map[r][c] = new Cell(r, c);
			}
		}

		// 사용자 A, B 이동 정보 저장
		for (int m = 0; m < M; ++m) {
			roadA[m] = sc.nextInt();
		}
		for (int m = 0; m < M; ++m) {
			roadB[m] = sc.nextInt();
		}

		// BC 정보 저장
		for (int a = 0; a < A; ++a) {
			int c = sc.nextInt();
			int r = sc.nextInt();
			int C = sc.nextInt();
			int P = sc.nextInt();
			map[r][c].bcList.add(new BC(C, P));
			q.offer(map[r][c]);
			findCoverage(r, c, map[r][c].bcList.get(map[r][c].bcList.size() - 1));
		}

		// 각 셀의 BC 리스트 P 내림차순으로 정렬
		for (int r = 1; r <= 10; ++r) {
			for (int c = 1; c <= 10; ++c) {
				Collections.sort(map[r][c].bcList);
			}
		}
	}

	// BC 커버리지 범위 저장
	private static void findCoverage(int r, int c, BC bc) {
		int nr, nc;

		while (!q.isEmpty()) {
			Cell cell = q.poll();
			for (int i = 1; i < 5; ++i) {
				nr = cell.r + dir[i][0];
				nc = cell.c + dir[i][1];
				if (nr > 10 || nr < 1 || nc > 10 || nc < 1)
					continue;
				if (Math.abs(r - nr) + Math.abs(c - nc) > bc.c)
					continue;
				if (map[nr][nc].bcList.contains(bc))
					continue;
				map[nr][nc].bcList.add(bc);
				q.offer(map[nr][nc]);
			}
		}

	}

}
