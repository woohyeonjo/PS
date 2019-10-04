package SWEA;


import java.util.Scanner;

public class S1873 {
	
	static String[][] map;
	static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int nowH, nowW;
	static int T, H, W, N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = Integer.parseInt(sc.nextLine());
		for(int t = 1 ; t <= T; ++t) {
			String[] HW = sc.nextLine().split(" ");
			H = Integer.parseInt(HW[0]);
			W = Integer.parseInt(HW[1]);
			
			map = new String[H][W];
			
			for(int h = 0 ; h < H ; ++h) {
				String[] objects = sc.nextLine().split("");
				for(int w = 0 ; w < W ; ++w) {
					map[h][w] = objects[w];
					if("^v<>".contains(objects[w])) {
						nowH = h;
						nowW = w;
					}
				}
			}
			
			N = sc.nextInt();
			sc.nextLine();
			String[] commandLine = sc.nextLine().split("");
			String command;
			for(int n = 0 ; n < N ; ++n) {
				command = commandLine[n];
				action(command);
			}
			
			System.out.print("#" + t + " ");
			for(int i = 0 ; i < H ; ++i) {
				for(int j = 0 ; j < W ; ++j) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}

	private static void action(String command) {
		
		switch (command){
			case "S":
				String look = map[nowH][nowW];
				if(look.equals("^")) {
					fire(0);
					break;
				} else if (look.equals("v")) {
					fire(1);
					break;
				} else if (look.equals("<")) {
					fire(2);
					break;
				} else if (look.equals(">")) {
					fire(3);
					break;
				}
			case "U":
				move(0, "^");
				break;
			case "D":
				move(1, "v");
				break;
			case "L":
				move(2, "<");
				break;
			case "R":
				move(3, ">");
				break;
		}
	}
	
	private static void move(int directionNum, String look) {
		map[nowH][nowW] = look;
		int nextH = nowH + direction[directionNum][0];
		int nextW = nowW + direction[directionNum][1];
		
		if(nextH >= 0 && nextH < H && nextW >= 0 && nextW < W) {
			if(map[nextH][nextW].equals(".")) {
				map[nextH][nextW] = look;
				map[nowH][nowW] = ".";
				nowH = nextH;
				nowW = nextW;
			}
		}
	}
	
	private static void fire(int look) {
		int nextShellH = nowH;
		int nextShellW = nowW;
		String object;
		while(true) {
			nextShellH += direction[look][0];
			nextShellW += direction[look][1];
			if(nextShellH < 0 || nextShellH >= H || nextShellW < 0 || nextShellW >= W) return;
			object = map[nextShellH][nextShellW];
			switch (object) {
				case ".":
					continue;
				case "-":
					continue;
				case "*":
					map[nextShellH][nextShellW] = ".";
					return;
				case "#":
					return;
			}
		}
	}
	
	
	
	
}
