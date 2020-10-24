package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1107_리모컨 {

	static boolean[] broken;
	static int N, M, limit, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		M = stoi(br.readLine());
		
		broken = new boolean[10];

		if(M > 0) { // 고장난 버튼이 있을 때만 입력받기
			st = new StringTokenizer(br.readLine()); 
		}
		
		for(int i = 0 ; i < M ; ++i) {
			broken[stoi(st.nextToken())] = true;
		}
		
		limit = ("" + N).length() + 1; // 채널의 최대 자릿수 
		ans = Math.abs(N - 100); // +, - 버튼만으로 이동하는 경우 
		
		for(int i = 0 ; i < 10 ; ++i) { // 입력채널 첫번째 자릿수 결정 
			if(!broken[i]) {
				click(1, i);
			}
		}
		
		System.out.println(ans);
	}

	private static void click(int length, int channel) {
		if(limit < length) {
			return;
		}
		
		int cur = Math.abs(N - channel) + length; // 현재 입력된 채널에서 목표 채널로 가기위한 횟수
		ans = ans > cur ? cur : ans;  // 최솟값 갱신 
		
		for(int i = 0 ; i < 10 ; ++i) {
			if(!broken[i]) {
				click(length + 1, (channel * 10) + i); // 스트링을 사용하지않고 10을 곱하고 더함으로써 해결 
			}
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
