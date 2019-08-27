package BOJ.go;

public class Main {
	static int[][] map;
	static int[] temp = new int[2];
    public static void main(String[] args) {
    	
    	map = new int[2][2];
    	int num = 1;
    	
    	for(int i = 0 ; i < 2 ; ++i) {
    		for(int j = 0 ; j < 2 ; ++j) {
    			map[i][j] = num++;
    			System.out.print(map[i][j] + " ");
    		}
    		System.out.println();
    	}
    	System.out.println();
    	
    	go(0, 0, 0);
    }

	private static void go(int depth, int I, int J) {
		
		if(depth == 2 || J == 2) {
			for(int i = 0 ; i < 2 ; ++i) {
				System.out.print(temp[i] + " ");
			}
			System.out.println();
			return;
		}
		
		boolean flag = true;
		for(int i = 0 ; i < 2 ; ++i) {
			for(int j = 0 ; j < 2 ; ++j) {
				if(flag) {
					i = I;
					j = J;
					flag=false;
				}
				temp[depth] = map[i][j];
				go(depth + 1, i, j + 1);
			}
		}
	}
}