package Algorithm.SWEA.go;

import java.util.Scanner;
 
public class S1210 {
 
    static int N = 100;
    static int[][] map = new int[N][N];
    static int X, Y;
    static int[][] direction = { { -1, 0 }, { 0, -1 }, { 0, 1 } }; // ?�� �? ?��
 
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
 
        for (int t = 1; t <= 10; ++t) {
            int T = sc.nextInt();
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    map[i][j] = sc.nextInt();
                    if (map[i][j] == 2) {
                        X = i;
                        Y = j;
                    }
                }
            }
             
            while(true) {
                 
                if(X == 0) {
                    System.out.println("#" + T + " " + Y);
                    break;
                }
                 
                for(int i = 2; i >= 0 ; --i) {
                    int newX = X + direction[i][0];
                    int newY = Y + direction[i][1];
                     
                    if(newX >= 0 && newX < N && newY >= 0 && newY < N) {
                        if(map[newX][newY] == 1) {
                            X = newX;
                            Y = newY;
                            map[X][Y] = 5;
                            break;
                        }
                    }
                }
            }
        }
    }
}