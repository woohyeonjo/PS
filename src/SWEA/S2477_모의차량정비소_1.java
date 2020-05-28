package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class S2477_모의차량정비소_1 {
	
	static class Customer {
		int customerNo;
		int receptionNo;
		int repaireNo;
		int arriveTime;
		int receptionStartTime;
		int receptionEndTime;
		int repairStartTime;
		
		Customer(int customerNo, int arriveTime) {
			this.customerNo = customerNo;
			this.arriveTime = arriveTime;
		}
	}
	
	static PriorityQueue<Customer> receptionQueue, repairQueue;
	static Customer[] receptionCounter, repairCounter;
	static int[] reception, repair;
	// 접수 창구의 개수 N, 정비 창구의 개수 M, 차량 정비소를 방문한 고객의 수 K, 지갑을 두고 간 고객이 이용한 접수 창구번호 A와 정비 창구번호 B가 주어진다.
	static int N, M, K, A, B, T, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T ; ++t) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			ans = 0;
			
			reception = new int[N + 1];
			repair = new int[M + 1];
			receptionCounter = new Customer[N + 1];
			repairCounter = new Customer[M + 1];
			receptionQueue = new PriorityQueue<>(new Comparator<Customer>() {
				@Override
				public int compare(Customer o1, Customer o2) {
					return o1.customerNo - o2.customerNo;
				}
			});
			repairQueue = new PriorityQueue<>(new Comparator<Customer>() {
				@Override
				public int compare(Customer o1, Customer o2) {
					if(o1.receptionEndTime == o2.receptionEndTime) {
						return o1.receptionNo - o2.receptionNo;
					} else {
						return o1.receptionEndTime - o2.receptionEndTime;
					}
				}
			});

			st = new StringTokenizer(br.readLine());
			for(int n = 1 ; n <= N ; ++n) {
				reception[n] = Integer.parseInt(st.nextToken()); 
			}
			
			st = new StringTokenizer(br.readLine());
			for(int m = 1 ; m <= M ; ++m) {
				repair[m] = Integer.parseInt(st.nextToken()); 
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= K ; ++i) {
				receptionQueue.offer(new Customer(i, Integer.parseInt(st.nextToken())));
			}
			
			
			int time = 0;
			int cnt = 0;
			while(true) {
				// 접수 끝난 사람 정비창구로 보내기
				for(int i = 1 ; i <= N ; ++i) {
					if(receptionCounter[i] == null) continue;
					
					Customer c = receptionCounter[i];
					
					if(c.receptionStartTime + reception[i] <= time) {
//						System.out.println("[" + time + "]" + i + "번 접수창구에 " + receptionCounter[i].customerNo + "고객이 접수를 마쳤습니다." );
						c.receptionEndTime = time;
						repairQueue.offer(c);
						receptionCounter[i] = null;
					}
				}
				
				// 접수창구 채우기 
				for(int i = 1 ; i <= N ; ++i) {
					if(receptionCounter[i] == null) {
						if(!receptionQueue.isEmpty()) {
							if(receptionQueue.peek().arriveTime <= time) {
								receptionCounter[i] = receptionQueue.poll();
								receptionCounter[i].receptionNo = i;
								receptionCounter[i].receptionStartTime = time;
//								System.out.println("[" + time + "]" + i + "번 접수창구에 " + receptionCounter[i].customerNo + "고객이 접수를 시작했습니다." );
							}
						}
					}
				}
				
				// 정비 끝난 사람 내보내기
				for(int i = 1 ; i <= M ; ++i) {
					if(repairCounter[i] == null) continue;
					
					Customer c = repairCounter[i];
					
					if(c.repairStartTime + repair[i] <= time) {
//						System.out.println("[" + time + "]" + i + "번 정비창구 " + repairCounter[i].customerNo + "고객이 정비를 마쳤습니다." );
//						System.out.println(c.customerNo + "고객은 " + c.receptionNo + "접수창구와 " + c.repaireNo + "정비창구를 이용했습니다.");
						if(c.receptionNo == A && c.repaireNo == B) ans += c.customerNo;
						repairCounter[i] = null;
						cnt++;
					}
				}
				
				if(cnt == K) break;
				
				// 정비창구 채우기
				for(int i = 1 ; i <= M ; ++i) {
					if(repairCounter[i] == null) {
						if(!repairQueue.isEmpty()) {
							repairCounter[i] = repairQueue.poll();
							repairCounter[i].repaireNo = i;
							repairCounter[i].repairStartTime = time;
//							System.out.println("[" + time + "]" + i + "번 정비창구 " + repairCounter[i].customerNo + "고객이 정비를 시작했습니다." );
						}
					}
				}
				
				time++;
			}
			
			if(ans == 0) ans = -1;
			System.out.println("#" + t + " " + ans);
		}
	}
}
