package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B12970_AB {
	
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for(int a = 0 ; a <= N ; ++a) {
			int b = N - a;
			
			// AB 쌍의 최대 개수는 a * b 이므로 최대개수가 K 미만일 경우는 넘어간다.
			if(a * b < K) continue;
			
			// B를 먼저 놔두고 A를 배치한다. 따라서 A가 위치할 수 있는 곳은 b + 1 곳 이다.
			// A[i] 는 i번째 들어갈 A의 개수 
			// 좌우반전을 하여 수행한다. 
			int[] A = new int[b + 1];
			for(int i = 0 ; i < a ; ++i) {
				// A의 오른쪽에 B의 개수만큼 순서쌍이 생긴다. 
				// 한 번에 최대 만들 수 있는 순서쌍은 b개
				// 따라서 K와 b중 작은 값의 위치에 A를 위치시킨다면 필요한 만큼 순서쌍을 만들 수 있다. 
				int idx = K > b ? b : K;
				
				// 지정한 위치에 A를 배치한다. 
				A[idx]++;
				// 생성된 순서쌍을 K에서 뺀다. 
				K -= idx;
			}
			
			for(int i = b ; i >= 0 ; --i) {
				for(int j = 0 ; j < A[i] ; ++j) {
					System.out.print("A");
				}
				// 마지막 B는 순서쌍을 더 만들게 되므로 추가하지 않는다.
				if(i > 0) System.out.print("B");
			}
			return;
		}
		
		// 만들지 못하는 경우 
		System.out.println(-1);
	}
}
