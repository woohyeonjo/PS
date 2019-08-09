package BOJ.go;


import java.util.Scanner;

public class B10845 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int[] queue = new int[10001];
		int front = -1;
		int rear = -1;
		int maxSize = queue.length;
		
		// front == rear �����? ��
		// enqueue rear++�� ���� 
		// dequeue front++�� ���� 
		
		for(int i = 0 ; i < N ; i++) {
			String[] line = sc.nextLine().split(" ");
			String command = line[0];
			int target = 0;
			if(line.length >= 2) target = Integer.parseInt(line[1]);
			
			if(command.equals("push")) {
				if(rear >= maxSize - 1) {
					for(int j = 0 ; j < front - rear ; j++) {
						queue[j] = queue[++front];
					}
					front = -1;
					rear = rear - front - 1;
				}
				queue[++rear] = target;
				
			} else if(command.equals("pop")) {
				if(front >= rear) System.out.println(-1);
				else System.out.println(queue[++front]);
				
			} else if(command.equals("size")) {
				if(front >= rear) System.out.println(0);
				else System.out.println(rear - front);
				
			} else if(command.equals("empty")) {
				if(front >= rear) System.out.println(1);
				else System.out.println(0);
				
			} else if(command.equals("front")) {
				if(front >= rear) System.out.println(-1);
				else System.out.println(queue[front + 1]);
				
			} else if(command.equals("back")) {
				if(front >= rear) System.out.println(-1);
				else System.out.println(queue[rear]);
			}
		}
	}
}
