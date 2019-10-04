package SWEA;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class S2477_모의차량정비소 {
	
	static class ReceptionRule implements Comparator<Customer> {

		@Override
		public int compare(Customer o1, Customer o2) {
			if(o1.arrive == o2.arrive) return o1.no - o2.no;
			else return o1.arrive - o2.arrive;
		}
	  
	}
	
	static class RepairRule implements Comparator<Customer> {

		@Override
		public int compare(Customer o1, Customer o2) {
			if(o1.arrive == o2.arrive) return o1.reception_no - o2.reception_no;
			else return o1.arrive - o2.arrive;
		}
		
	}

	static class Customer {
		int no;
		int arrive;
		int waiting;
		int reception_no;
		int repair_no;
		
		
		public Customer(int no, int arrive) {
			this.no = no;
			this.arrive = arrive;
		}
	}
	
	static PriorityQueue<Customer> receptionQ = new PriorityQueue<>(new ReceptionRule());
	static PriorityQueue<Customer> repairQ = new PriorityQueue<>(new RepairRule()); 
	static Customer[] reception;
	static Customer[] repair;
	static ArrayList<Customer> customerList;
	
	static int[] receptionInfo;
	static int[] repairInfo;
	static int T, N, M, K, A, B, ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		
		for(int t = 1 ; t <= T ; ++t) {
			init(sc);
			
			Customer cust;
			int time = 0;
			while(customerList.size() < K) {
				for(int i = 1 ; i <= N ; ++i) {
					if(reception[i] != null) {
						reception[i].waiting++;
						if(reception[i].waiting == receptionInfo[reception[i].reception_no]) {
							reception[i].waiting = 0;
							reception[i].arrive = time;
							repairQ.offer(reception[i]);
							reception[i] = null;
						}
					}
					if(reception[i] == null) {
						if(!receptionQ.isEmpty()) {
							if(receptionQ.peek().arrive <= time) {
								cust = receptionQ.poll();
								cust.reception_no = i;
								reception[i] = cust;
							}
						}
					}
				}
				
				for(int i = 1 ; i <= M ; ++i) {
					if(repair[i] != null) {
						repair[i].waiting++;
						if(repair[i].waiting == repairInfo[repair[i].repair_no]) {
							customerList.add(repair[i]);
							repair[i] = null;
						}
					}
					if(repair[i] == null) {
						if(!repairQ.isEmpty()) {
							cust = repairQ.poll();
							cust.repair_no = i;
							repair[i] = cust;
						}
					}
				}
				time++;
			}
			
			for(Customer customer : customerList) {
				if(customer.reception_no == A && customer.repair_no == B) {
					ans += customer.no;
				}
			}
			
			if(ans == 0) ans = -1;
			System.out.println("#" + t + " " + ans);
			
		}
		
	}
	
	private static void init(Scanner sc) {
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		A = sc.nextInt();
		B = sc.nextInt();
		
		receptionInfo = new int[N + 1];
		repairInfo = new int[M + 1];
		reception = new Customer[N + 1];
		repair = new Customer[M + 1];
		customerList = new ArrayList<>();
		ans = 0;
		
		for(int r = 1 ; r <= N ; ++r) receptionInfo[r] = sc.nextInt();
		for(int r = 1 ; r <= M ; ++r) repairInfo[r] = sc.nextInt();
		for(int r = 1 ; r <= K ; ++r) receptionQ.offer(new Customer(r, sc.nextInt()));
	}
}
