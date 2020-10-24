package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class B1935_후위표기식2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int op_cnt = stoi(br.readLine());
		
		Stack<Double> s = new Stack<>();
		double[] ops = new double[op_cnt];
		double ans = 0;
		
		char[] input = br.readLine().toCharArray();
		for(int i = 0 ; i < op_cnt ; ++i) {
			ops[i] = stoi(br.readLine());
		}
		
		double a = 0;
		double b = 0;
		for(int i = 0 ; i < input.length ; ++i) {
			switch(input[i]) {
			case '+':
				a = s.pop();
				b = s.pop();
				s.push(b + a);
				break;
			case '-':
				a = s.pop();
				b = s.pop();
				s.push(b - a);
				break;
			case '*':
				a = s.pop();
				b = s.pop();
				s.push(b * a);
				break;
			case '/':
				a = s.pop();
				b = s.pop();
				s.push(b / a);
				break;
			default:
				s.push(ops[input[i] - 'A']);
			}
		}
		
		System.out.format("%.2f", s.pop());
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
