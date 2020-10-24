package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B3954_Brainfuck인터프리터 {
	
	static final int INFINITE = 50000000;
	static final int MAX = 256;
	
	static int[] memory;
	static int[] loop;
	static int[] touch;
	static char[] cmd;
	static char[] input;
	
	static int loopStart, loopEnd, cmdCnt;
	
	static int p;
	static int cp;
	static int ip;
	
	static int T, Sc, Sm, Si;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = stoi(br.readLine());
		
		for(int t = 0 ; t < T ; ++t) {
			init(br);
			
			while(cmdCnt < INFINITE && cp < Sc) {
				cmdCnt++; // 명령어 실행 횟수 증가 
				switch(cmd[cp]) {
				case '-':
					memory[p] = (memory[p] + 255) % MAX;
					break;
				case '+':
					memory[p] = (memory[p] + 1) % MAX;
					break;
				case '<':
					p = (p + (Sm - 1)) % Sm;
					break;
				case '>':
					p = (p + (Sm + 1)) % Sm;
					break;
				case '[':
					if(memory[p] == 0) {
						cp = loop[cp];
						continue;
					}
					break;
				case ']':
					if(memory[p] != 0) {
						touch[cp]++;
						cp = loop[cp];
						continue;
					}
					break;
				case '.':
					// memory[p] 출력 
					break;
				case ',':
					// input을 memory에 입력하기
					// input의 마지막을 넘은 경우 255 입력 
					if(ip == Si) {
						memory[p] = 255;
					} else {
						memory[p] = input[ip++];
					}
					break;
				}
				cp++; // 명령어 포인터 증가
			}
			
			if (cmdCnt == INFINITE) {
				System.out.print("Loops ");
				findLoops();
			} else {
				System.out.println("Terminates");
			}
		}
		
	}
	
	private static void findLoops() {
		for (int i = Sc - 1; i >= 0; i--) {
			if (touch[i] > 0) {
				System.out.println(loop[i] + " " + i);
				break;
			}
		}
	}

	private static void init(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		Sm = stoi(st.nextToken());
		Sc = stoi(st.nextToken());
		Si = stoi(st.nextToken());
		
		memory = new int[Sm];
		loop = new int[Sc];
		touch = new int[Sc]; // 반복문 명령어가 수행된 횟수
		cmd = br.readLine().toCharArray();
		input = br.readLine().toCharArray();
		
		Stack<Integer> stack = new Stack<>();
		
		// 반복문 짝 만들기 
		for(int i = 0 ; i < Sc ; ++i) {
			if(cmd[i] == '[') stack.push(i);
			else if(cmd[i] == ']') {
				int start = stack.pop();
				loop[start] = i;
				loop[i] = start;
			}
		}
		
		loopStart = 0;
		loopEnd = 0;
		cmdCnt = 0; // 명령어 수행횟수 
		
		p = 0; // 메모리 포인터 
		ip = 0; // 인풋 포인터 
		cp = 0; // 커맨드 포인터 
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
