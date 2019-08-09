package SWEA.go;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class S1266 {
	
	static ArrayList<Meeting> meetingList;
	static ArrayList<Meeting> confirmedList;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int availableStartTime = 0;
		meetingList = new ArrayList<Meeting>();
		confirmedList = new ArrayList<Meeting>();
		
		for(int n = 0; n < N; ++n) {
			int num = sc.nextInt();
			int startTime = sc.nextInt();
			int endTime = sc.nextInt();
			meetingList.add(new Meeting(num, startTime, endTime));
		}
		
		Collections.sort(meetingList);
		
		System.out.println("======= Sorted List =======");
		printTimeTable(meetingList);
		
		for(Meeting m : meetingList) {
			if(m.startTime >= availableStartTime) {
				confirmedList.add(m);
				availableStartTime = m.endTime;
			}
		}
		
		System.out.println("======= Confirmed List =======");
		printTimeTable(confirmedList);
	
	}
	
	static void printTimeTable(ArrayList<Meeting> list) {
		for(Meeting m : list) {
			System.out.print("No." + m.num + " : ");
			for(int i = 0 ; i < m.startTime ; ++i) System.out.print("□");
			for(int i = m.startTime ; i < m.endTime ; ++i) System.out.print("■");
			for(int i = m.endTime ; i < 24 ; ++i) System.out.print("□");
			System.out.println();
		}
	}
	
	static class Meeting implements Comparable<Meeting> {
		int num;
		int startTime;
		int endTime;

		public Meeting(int num, int startTime, int endTime) {
			this.num = num;
			this.startTime = startTime;
			this.endTime = endTime;
		}

		@Override
		public int compareTo(Meeting o) {
			int comp;
			
			if((comp = this.endTime - o.endTime) == 0) {
				if((comp = this.startTime - o.startTime) == 0) {
					return this.num - o.num;
				} else {
					return comp;
				}
			} else {
				return comp;
			}
		}
	}
}
