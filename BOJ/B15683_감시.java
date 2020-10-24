package BOJ;


import java.util.Scanner;

public class B15683_감시 {
	
	static class Cam {
		int r, c, type;

		public Cam(int r, int c, int type) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}
	
	static int[][] office;
	static int[][] office_copied;
	static Cam[] camList;
	static int[] camDir;
	static int N, M, ans, camCnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		ans = Integer.MAX_VALUE;
		camCnt = 0;
		office = new int[N][M];
		
		int type;
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c  < M ; ++c) {
				type = sc.nextInt();
				if(type > 0 && type < 6) camCnt++;
				office[r][c] = type;
			}
		}
		
		camDir = new int[camCnt];
		camList = new Cam[camCnt];
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c  < M ; ++c) {
				if(office[r][c] > 0 && office[r][c] < 6) {
					camList[--camCnt] = new Cam(r, c, office[r][c]);
				}
			}
		}
		
		go(0);
		System.out.println(ans);
	}

	private static void go(int index) {
		
		if(index == camDir.length) {
			copy();
			watch();
			
			int current = check();
			ans = ans > current ? current : ans;
			
			return;
		}
		
		for(int i = 0 ; i < 4 ; ++i) {
			camDir[index] = i;
			go(index + 1);
		}
	}

	private static void watch() {
		int[][] dir = null;
		
		for(int i = 0 ; i < camList.length ; ++i) {
			Cam cam = camList[i];
			switch(cam.type) {
				case 1:
					dir = new int[1][2];
					if(camDir[i] == 0) {
						for(int c = cam.c + 1 ; c < M ; ++c ) {
							if(office_copied[cam.r][c] == 6) break;
							office_copied[cam.r][c] = 9;
						}
					} else if (camDir[i] == 1) {
						for(int r = cam.r - 1 ; r >= 0 ; --r ) {
							if(office_copied[r][cam.c] == 6) break;
							office_copied[r][cam.c] = 9;
						}
					} else if (camDir[i] == 2) {
						for(int c = cam.c - 1 ; c >= 0 ; --c ) {
							if(office_copied[cam.r][c] == 6) break;
							office_copied[cam.r][c] = 9;
						}
					} else if (camDir[i] == 3) {
						for(int r = cam.r + 1 ; r < N ; ++r ) {
							if(office_copied[r][cam.c] == 6) break;
							office_copied[r][cam.c] = 9;
						}
					}
					break;
				case 2:
					dir = new int[2][2];
					if(camDir[i] % 2 == 0) {
						for(int c = cam.c + 1 ; c < M ; ++c ) {
							if(office_copied[cam.r][c] == 6) break;
							office_copied[cam.r][c] = 9;
						}
						for(int c = cam.c - 1 ; c >= 0 ; --c ) {
							if(office_copied[cam.r][c] == 6) break;
							office_copied[cam.r][c] = 9;
						}
					} else {
						for(int r = cam.r - 1 ; r >= 0 ; --r ) {
							if(office_copied[r][cam.c] == 6) break;
							office_copied[r][cam.c] = 9;
						}
						for(int r = cam.r + 1 ; r < N ; ++r ) {
							if(office_copied[r][cam.c] == 6) break;
							office_copied[r][cam.c] = 9;
						}
					}
					break;
				case 3:
					dir = new int[2][2];
					if(camDir[i] == 0) {
						for(int r = cam.r - 1 ; r >= 0 ; --r ) {
							if(office_copied[r][cam.c] == 6) break;
							office_copied[r][cam.c] = 9;
						}
						for(int c = cam.c + 1 ; c < M ; ++c ) {
							if(office_copied[cam.r][c] == 6) break;
							office_copied[cam.r][c] = 9;
						}
					} else if (camDir[i] == 1) {
						for(int c = cam.c + 1 ; c < M ; ++c ) {
							if(office_copied[cam.r][c] == 6) break;
							office_copied[cam.r][c] = 9;
						}
						for(int r = cam.r + 1 ; r < N ; ++r ) {
							if(office_copied[r][cam.c] == 6) break;
							office_copied[r][cam.c] = 9;
						}
					} else if (camDir[i] == 2) {
						for(int r = cam.r + 1 ; r < N ; ++r ) {
							if(office_copied[r][cam.c] == 6) break;
							office_copied[r][cam.c] = 9;
						}
						for(int c = cam.c - 1 ; c >= 0 ; --c ) {
							if(office_copied[cam.r][c] == 6) break;
							office_copied[cam.r][c] = 9;
						}
					} else if (camDir[i] == 3) {
						for(int c = cam.c - 1 ; c >= 0 ; --c ) {
							if(office_copied[cam.r][c] == 6) break;
							office_copied[cam.r][c] = 9;
						}
						for(int r = cam.r - 1 ; r >= 0 ; --r ) {
							if(office_copied[r][cam.c] == 6) break;
							office_copied[r][cam.c] = 9;
						}
					}
					break;
				case 4:
					dir = new int[3][2];
					if(camDir[i] == 0) {
						for(int c = cam.c - 1 ; c >= 0 ; --c ) {
							if(office_copied[cam.r][c] == 6) break;
							office_copied[cam.r][c] = 9;
						}
						for(int r = cam.r - 1 ; r >= 0 ; --r ) {
							if(office_copied[r][cam.c] == 6) break;
							office_copied[r][cam.c] = 9;
						}
						for(int c = cam.c + 1 ; c < M ; ++c ) {
							if(office_copied[cam.r][c] == 6) break;
							office_copied[cam.r][c] = 9;
						}
					} else if (camDir[i] == 1) {
						for(int r = cam.r - 1 ; r >= 0 ; --r ) {
							if(office_copied[r][cam.c] == 6) break;
							office_copied[r][cam.c] = 9;
						}
						for(int c = cam.c + 1 ; c < M ; ++c ) {
							if(office_copied[cam.r][c] == 6) break;
							office_copied[cam.r][c] = 9;
						}
						for(int r = cam.r + 1 ; r < N ; ++r ) {
							if(office_copied[r][cam.c] == 6) break;
							office_copied[r][cam.c] = 9;
						}
					} else if (camDir[i] == 2) {
						for(int c = cam.c + 1 ; c < M ; ++c ) {
							if(office_copied[cam.r][c] == 6) break;
							office_copied[cam.r][c] = 9;
						}
						for(int r = cam.r + 1 ; r < N ; ++r ) {
							if(office_copied[r][cam.c] == 6) break;
							office_copied[r][cam.c] = 9;
						}
						for(int c = cam.c - 1 ; c >= 0 ; --c ) {
							if(office_copied[cam.r][c] == 6) break;
							office_copied[cam.r][c] = 9;
						}
					} else if (camDir[i] == 3) {
						for(int r = cam.r + 1 ; r < N ; ++r ) {
							if(office_copied[r][cam.c] == 6) break;
							office_copied[r][cam.c] = 9;
						}
						for(int c = cam.c - 1 ; c >= 0 ; --c ) {
							if(office_copied[cam.r][c] == 6) break;
							office_copied[cam.r][c] = 9;
						}
						for(int r = cam.r - 1 ; r >= 0 ; --r ) {
							if(office_copied[r][cam.c] == 6) break;
							office_copied[r][cam.c] = 9;
						}
					}
					break;
				case 5:
					for(int r = cam.r + 1; r < N ; ++r ) {
						if(office_copied[r][cam.c] == 6) break;
						office_copied[r][cam.c] = 9;
					}
					for(int c = cam.c - 1 ; c >= 0 ; --c ) {
						if(office_copied[cam.r][c] == 6) break;
						office_copied[cam.r][c] = 9;
					}
					for(int r = cam.r - 1 ; r >= 0 ; --r ) {
						if(office_copied[r][cam.c] == 6) break;
						office_copied[r][cam.c] = 9;
					}
					for(int c = cam.c + 1 ; c < M ; ++c ) {
						if(office_copied[cam.r][c] == 6) break;
						office_copied[cam.r][c] = 9;
					}
					break;
			}
		}
	}

	private static int check() {
		int result = 0;
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				if(office_copied[r][c] == 0) result++;
			}
		}
		
		return result;
	}

	private static void copy() {
		int[][] temp = new int[N][M];
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < M ; ++c) {
				temp[r][c] = office[r][c];
			}
		}
		
		office_copied = temp;
	}
}
