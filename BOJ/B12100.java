package BOJ;


import java.util.ArrayList;
import java.util.Scanner;

public class B12100 {
	
	static ArrayList<Integer>[] map;
	static int N, ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new ArrayList[N];
		
		for(int i = 0 ; i < N ; ++i) {
			map[i] = new ArrayList<Integer>();
		}
		
		for(int r = 0 ; r < N ; ++r) {
			for(int c = 0 ; c < N ; ++c) {
				map[r].add(sc.nextInt());
			}
		}
		
		dfs(map, 0, 0);
		System.out.println(ans);
	}
	private static void dfs(ArrayList<Integer>[] map, int max, int cnt) {
		
		if(cnt == 5) {
			ans = max > ans ? max : ans;
			return;
		}
		
		for(int i = 0 ; i < 4 ; ++i) {
			ArrayList<Integer>[] nMap = copy(map);
			dfs(nMap, go(nMap, i), cnt + 1);
		}
	}
	
	private static ArrayList<Integer>[] copy(ArrayList<Integer>[] map) {
		ArrayList<Integer>[] result = new ArrayList[N];
		
//		for(int i = 0 ; i < N ; ++i) {
//			result[i] = new ArrayList<Integer>();
//		}
		
		for(int i = 0 ; i < N ; ++i) {
			result[i] = (ArrayList<Integer>) map[i].clone();
		}
		
		
		return result;
	}
	private static int go(ArrayList<Integer>[] nMap, int dir) {
		ArrayList<Integer>[] tMap;
		int first = 0, second = 0;
		int max = 0;
		switch(dir) {
			// 상
			case 0:
				tMap = turn(map);
		        for(int r = 0 ; r < tMap.length ; ++r) {
		            for(int c = 0 ; c < tMap[r].size() ; ++c) {
		                first = tMap[r].get(c);
		                if(first == 0) {
		                    for(int cnt = 0 ; cnt < N ; ++cnt) {
		                        tMap[r].remove(c);
		                        tMap[r].add(0);
		                        first = tMap[r].get(c);
		                        max = first > max ? first : max;
		                        if(first != 0) break;
		                    }
		                }
		                for(int sc = c + 1 ; sc < tMap[r].size() ; ++sc) {
		                    second = tMap[r].get(sc);
		                    if(second == 0) {
		                        for(int scnt = 0 ; scnt < N ; ++scnt) {
		                            tMap[r].remove(sc);
		                            tMap[r].add(0);
		                            second = tMap[r].get(sc);
		                            max = second > max ? second : max;
		                            if(second != 0)break;
		                        }
		                    }
		                    if(first == second) {
		                        tMap[r].remove(c);
		                        tMap[r].add(c, first * 2);
		                        max = first * 2 > max ? first * 2 : max;
		                        tMap[r].remove(sc);
		                        tMap[r].add(0);
		                        break;
		                    } else break;
		                }
		            }
		        }
		        nMap = turn(tMap);
				break;
				
			// 하	
			case 1:
				tMap = turn(map);
		        for(int r = 0 ; r < tMap.length ; ++r) {
		            for(int c = tMap[r].size() - 1 ; c >= 1  ; --c) {
		                first = tMap[r].get(c);
		                if(first == 0) {
		                    for(int cnt = 0 ; cnt < N ; ++cnt) {
		                        tMap[r].remove(c);
		                        tMap[r].add(0, 0);
		                        first = tMap[r].get(c);
		                        max = first > max ? first : max;
		                        if(first != 0) break;
		                    }
		                }
		                for(int sc = c - 1 ; sc >= 0 ; --sc) {
		                    second = tMap[r].get(sc);
		                    if(second == 0) {
		                        for(int scnt = 0 ; scnt < N ; ++scnt) {
		                            tMap[r].remove(sc);
		                            tMap[r].add(0, 0);
		                            second = tMap[r].get(sc);
		                            max = second > max ? second : max;
		                            if(second != 0) break;
		                        }
		                    }
		                    if(first == second) {
		                        tMap[r].remove(c);
		                        tMap[r].add(c, first * 2);
		                        max = first * 2 > max ? first * 2 : max;
		                        tMap[r].remove(sc);
		                        tMap[r].add(0, 0);
		                        break;
		                    } else break;
		                }
		            }
		        }
		        nMap = turn(tMap);
				break;
				
			// 좌	
			case 2:
				for(int r = 0 ; r < nMap.length ; ++r) {
		            for(int c = 0 ; c < nMap[r].size() ; ++c) {
		                first = nMap[r].get(c);
		                if(first == 0) {
		                    for(int cnt = 0 ; cnt < N ; ++cnt) {
		                        nMap[r].remove(c);
		                        nMap[r].add(0);
		                        first = nMap[r].get(c);
		                        max = first > max ? first : max;
		                        if(first != 0) break;
		                    }
		                }
		                for(int sc = c + 1 ; sc < nMap[r].size() ; ++sc) {
		                    second = nMap[r].get(sc);
		                    if(second == 0) {
		                        for(int scnt = 0 ; scnt < N ; ++scnt) {
		                            nMap[r].remove(sc);
		                            nMap[r].add(0);
		                            second = nMap[r].get(sc);
		                            max = second > max ? second : max;
		                            if(second != 0)break;
		                        }
		                    }
		                    if(first == second) {
		                        nMap[r].remove(c);
		                        nMap[r].add(c, first * 2);
		                        max = first * 2 > max ? first * 2 : max;
		                        nMap[r].remove(sc);
		                        nMap[r].add(0);
		                        break;
		                    } else break;
		                }
		            }
		        }
				break;
				
			// 우	
			case 3:
				for(int r = 0 ; r < nMap.length ; ++r) {
		            for(int c = nMap[r].size() - 1 ; c >= 1  ; --c) {
		                first = nMap[r].get(c);
		                if(first == 0) {
		                    for(int cnt = 0 ; cnt < N ; ++cnt) {
		                        nMap[r].remove(c);
		                        nMap[r].add(0, 0);
		                        first = nMap[r].get(c);
		                        max = first > max ? first : max;
		                        if(first != 0) break;
		                    }
		                }
		                for(int sc = c - 1 ; sc >= 0 ; --sc) {
		                    second = nMap[r].get(sc);
		                    if(second == 0) {
		                        for(int scnt = 0 ; scnt < N ; ++scnt) {
		                            nMap[r].remove(sc);
		                            nMap[r].add(0, 0);
		                            second = nMap[r].get(sc);
		                            max = second > max ? second : max;
		                            if(second != 0) break;
		                        }
		                    }
		                    if(first == second) {
		                        nMap[r].remove(c);
		                        nMap[r].add(c, first * 2);
		                        max = first * 2 > max ? first * 2 : max;
		                        nMap[r].remove(sc);
		                        nMap[r].add(0, 0);
		                        break;
		                    } else break;
		                }
		            }
		        }
				break;
		}
		return max;
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
	
	
	
	
}
