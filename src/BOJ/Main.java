package BOJ;

import java.util.*;

public class Main {
   static int[][] map;// 지도배열
   static int[] command;// 명령어 배열
   static int x, y, n, m, k;

   public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);

      // 각각 지도 세로크기, 가로크기, 주사위 x좌표, y좌표, 명령개수를 입력 받는다.
      n = scan.nextInt();
      m = scan.nextInt();
      x = scan.nextInt();
      y = scan.nextInt();
      k = scan.nextInt();
      map = new int[n][m];
      command = new int[k];

      // n x m 만큼 지도를 입력받음
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < m; j++) {
            map[i][j] = scan.nextInt();
         }
      }

      // 명령어 개수만큼 명령어 배열에 입력
      for (int i = 0; i < k; i++)
         command[i] = scan.nextInt();

      Dice d = new Dice(x, y, n, m, k, map);// 다이스 객체 생성
      for(int i = 0; i < k; i++) {
         d.move(command[i]);
      }
   }

}

class Dice {
   int[][] infor = new int[4][3]; // 주사위칸에 숫자정보 입력
   int[][] map;
   int x, y, n, m, k;

   Dice(int x, int y, int n, int m, int k, int map[][]) { // 예외처리 쉽게하려고
      this.x = x;
      this.y = y;
      this.n = n;
      this.m = m;
      this.k = k;
      this.map = map;
   }

   void print() {
      System.out.println(infor[1][1]);
//      for(int i=0;i<4;i++) {
//         for(int j=0;j<3;j++)
//            System.out.print(infor[i][j]);
//         System.out.println();
//      }
//      System.out.println();
   }
   void move(int com) {
      switch(com) {
      case 1:
         if(y + 1 > m - 1) {//주사위 예외처리
            break;
         } else {//주사위 움직이기
            ++y;
            reverse(com);//주사위 굴린후 주사위 정보 바꿈
         }
         if(map[x][y] == 0) {//움직였는데 0이면 주사위 -> 바닥면 복사
            map[x][y] = infor[3][1];
         } else {//움직였는데 바닥이 0이 아니면 바닥면 -> 주사위 복사
            infor[3][1] = map[x][y];
            map[x][y] = 0;
         }
         print();
         break;
      case 2:
         if(y - 1 < 0) {//주사위 예외처리
            break;
         } else {//주사위 움직이기
            --y;
            reverse(com);//주사위 굴린후 주사위 정보 바꿈
         }
         if(map[x][y] == 0) {//움직였는데 0이면 주사위 -> 바닥면 복사
            map[x][y] = infor[3][1];
         } else {//움직였는데 바닥이 0이 아니면 바닥면 -> 주사위 복사
            infor[3][1] = map[x][y];
            map[x][y] = 0;
         }
         print();
         break;
      case 3:
         if(x - 1 < 0) {//주사위 예외처리
            break;
         } else {//주사위 움직이기
            --x;
            reverse(com);//주사위 굴린후 주사위 정보 바꿈
         }
         if(map[x][y] == 0) {//움직였는데 0이면 주사위 -> 바닥면 복사
            map[x][y] = infor[3][1];
         } else {//움직였는데 바닥이 0이 아니면 바닥면 -> 주사위 복사
            infor[3][1] = map[x][y];
            map[x][y] = 0;
         }
         print();
         break;
      case 4:
         if(x + 1 > n - 1) {//주사위 예외처리
            break;
         } else {//주사위 움직이기
            ++x;
            reverse(com);//주사위 굴린후 주사위 정보 바꿈
         }
         if(map[x][y] == 0) {//움직였는데 0이면 주사위 -> 바닥면 복사
            map[x][y] = infor[3][1];
         } else {//움직였는데 바닥이 0이 아니면 바닥면 -> 주사위 복사
            infor[3][1] = map[x][y];
            map[x][y] = 0;
         }
         print();
         break;
      }
   }
   
   void reverse(int com) {//주사위 이동하면 주사위칸 숫자정보 바뀜
      switch(com) {
      
      case 1://동
         int temp = infor[1][1];
         infor[1][1] = infor[1][0];
         infor[1][0] = infor[3][1];
         infor[3][1] = infor[1][2];
         infor[1][2] = temp;
         break;
      case 2://서
         temp = infor[1][1];
         infor[1][1] = infor[1][2];
         infor[1][2] = infor[3][1];
         infor[3][1] = infor[1][0];
         infor[1][0] = temp;
         break;
      case 3://북
         temp = infor[1][1];
         infor[1][1] = infor[2][1];
         infor[2][1] = infor[3][1];
         infor[3][1] = infor[0][1];
         infor[0][1] = temp;
         break;
      case 4://남
         temp = infor[1][1];
         infor[1][1] = infor[0][1];
         infor[0][1] = infor[3][1];
         infor[3][1] = infor[2][1];
         infor[2][1] = temp;
         break;
      }
   }
   
}