package BOJ;



import java.util.Scanner;

public class B10828_스택_2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int[] stack = new int[10000];
		int top = -1;
		
		for(int i = 0 ; i < N ; i++) {
			String[] line = sc.nextLine().split(" ");
			String command = line[0];
			int target = 0;
			if(line.length >= 2) {
				target = Integer.parseInt(line[1]);
			}
			
			if(command.equals("push")) {
				stack[++top] = target;
			} else if(command.equals("pop")) {
				if(top == -1) System.out.println(-1);
				else {
					System.out.println(stack[top]);
					top--;
				}
			} else if(command.equals("size")) {
				System.out.println(top + 1);
			} else if(command.equals("empty")) {
				if(top == -1) System.out.println(1);
				else System.out.println(0);
			} else if(command.equals("top")) {
				if(top == -1) System.out.println(-1);
				else {
					System.out.println(stack[top]);
				}
			}
		}
	}
}
