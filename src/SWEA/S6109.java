package SWEA;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class S6109 {
	static ArrayList<Integer>[] map;
	static int T, N;
	static String command;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] line;
		T = Integer.parseInt(in.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			line = in.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			command = line[1];
			
			map = new ArrayList[N];
			
			for(int i = 0 ; i < N ; ++i) {
				map[i] = new ArrayList<Integer>();
			}
			
			for(int r = 0 ; r < N ; ++r) {
				line = in.readLine().split(" ");
				for(int c = 0 ; c < N ; ++c) {
					map[r].add(Integer.parseInt(line[c]));
				}
			}
			
			System.out.println("#" + t);
			switch(command) {
				case "up":
					up();
					break;
				case "down":
					down();
					break;
				case "left":
					left();
					break;
				case "right":
					right();
					break;
			}
		}
	}
	private static void right() {
		ArrayList<Integer>[] result = map.clone();
		int first = 0, second = 0;
		
		for(int r = 0 ; r < result.length ; ++r) {
			for(int c = result[r].size() - 1 ; c >= 1  ; --c) {
				first = result[r].get(c);
				if(first == 0) {
					for(int cnt = 0 ; cnt < N ; ++cnt) {
						result[r].remove(c);
						result[r].add(0, 0);
						first = result[r].get(c);
						if(first != 0) break;
					}
				}
				for(int sc = c - 1 ; sc >= 0 ; --sc) {
					second = result[r].get(sc);
					if(second == 0) {
						for(int scnt = 0 ; scnt < N ; ++scnt) {
							result[r].remove(sc);
							result[r].add(0, 0);
							second = result[r].get(sc);
							if(second != 0) break;
						}
					}
					if(first == second) {
						result[r].remove(c);
						result[r].add(c, first * 2);
						result[r].remove(sc);
						result[r].add(0, 0);
						break;
					} else break;
				}
			}
		}
		print(result);
	}
	
	private static void left() {
		ArrayList<Integer>[] result = map.clone();
		int first = 0, second = 0;
		
		for(int r = 0 ; r < result.length ; ++r) {
			for(int c = 0 ; c < result[r].size() ; ++c) {
				first = result[r].get(c);
				if(first == 0) {
					for(int cnt = 0 ; cnt < N ; ++cnt) {
						result[r].remove(c);
						result[r].add(0);
						first = result[r].get(c);
						if(first != 0) break;
					}
				}
				for(int sc = c + 1 ; sc < result[r].size() ; ++sc) {
					second = result[r].get(sc);
					if(second == 0) {
						for(int scnt = 0 ; scnt < N ; ++scnt) {
							result[r].remove(sc);
							result[r].add(0);
							second = result[r].get(sc);
							if(second != 0)break;
						}
					}
					if(first == second) {
						result[r].remove(c);
						result[r].add(c, first * 2);
						result[r].remove(sc);
						result[r].add(0);
						break;
					} else break;
				}
			}
		}
		print(result);
	}
	
	private static void down() {
		ArrayList<Integer>[] result = turn(map);
		int first = 0, second = 0;
		
		for(int r = 0 ; r < result.length ; ++r) {
			for(int c = result[r].size() - 1 ; c >= 1  ; --c) {
				first = result[r].get(c);
				if(first == 0) {
					for(int cnt = 0 ; cnt < N ; ++cnt) {
						result[r].remove(c);
						result[r].add(0, 0);
						first = result[r].get(c);
						if(first != 0) break;
					}
				}
				for(int sc = c - 1 ; sc >= 0 ; --sc) {
					second = result[r].get(sc);
					if(second == 0) {
						for(int scnt = 0 ; scnt < N ; ++scnt) {
							result[r].remove(sc);
							result[r].add(0, 0);
							second = result[r].get(sc);
							if(second != 0) break;
						}
					}
					if(first == second) {
						result[r].remove(c);
						result[r].add(c, first * 2);
						result[r].remove(sc);
						result[r].add(0, 0);
						break;
					} else break;
				}
			}
		}
		result = turn(result);
		print(result);
	}
	
	private static void up() {
		ArrayList<Integer>[] result = turn(map);
		int first = 0, second = 0;
		
		for(int r = 0 ; r < result.length ; ++r) {
			for(int c = 0 ; c < result[r].size() ; ++c) {
				first = result[r].get(c);
				if(first == 0) {
					for(int cnt = 0 ; cnt < N ; ++cnt) {
						result[r].remove(c);
						result[r].add(0);
						first = result[r].get(c);
						if(first != 0) break;
					}
				}
				for(int sc = c + 1 ; sc < result[r].size() ; ++sc) {
					second = result[r].get(sc);
					if(second == 0) {
						for(int scnt = 0 ; scnt < N ; ++scnt) {
							result[r].remove(sc);
							result[r].add(0);
							second = result[r].get(sc);
							if(second != 0)break;
						}
					}
					if(first == second) {
						result[r].remove(c);
						result[r].add(c, first * 2);
						result[r].remove(sc);
						result[r].add(0);
						break;
					} else break;
				}
			}
		}
		result = turn(result);
		print(result);
	}
	
	private static ArrayList<Integer>[] turn(ArrayList<Integer>[] map) {
		ArrayList<Integer>[] result = new ArrayList[map.length];
		for(int i = 0 ; i < map.length ; ++i) {
			result[i] = new ArrayList<Integer>();
		}
		for(int i = 0 ; i < map.length ; ++i) {
			for(int j = 0 ; j < map[i].size() ; ++j) {
				result[j].add(map[i].get(j));
			}
		}
		
		return result;
	}
	
	private static void print(ArrayList<Integer>[] map) {
		for(int r = 0; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				System.out.print(map[r].get(c) + " ");
			}
			System.out.println();
		}
	}
}
