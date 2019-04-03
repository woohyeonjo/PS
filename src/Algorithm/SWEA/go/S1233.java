package Algorithm.SWEA.go;

import java.util.Scanner;

public class S1233 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        for(int t = 1; t <= 10; t++) {    
            boolean check = true;            
            int count = Integer.parseInt(sc.nextLine());
 
            for(int i = 0; i < count; i++) {
                String[] line = sc.nextLine().split(" ");
                if(line[1].charAt(0) <= '9' && line[1].charAt(0) >= '0') {
                	//숫자
                    if(line.length != 2) // 단말 노드가 아니면 연산 불가
                    	check = false;
                }
                else { // 연산자
                    if(line.length != 4) // 단말 노드면 연산 불가
                    	check = false;
                }
            }
            System.out.println("#"+t+" " + (check?1:0));
        }
    }
}
