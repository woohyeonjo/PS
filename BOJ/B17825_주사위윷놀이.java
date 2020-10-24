package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B17825_주사위윷놀이 {
	
	static class Node {
		int value;
		boolean isEmpty, isEnd;
		Node next, shortcut;
		
		Node(int value){
			this.value = value;
			this.isEmpty = true;
			this.isEnd = false;
			this.next = null;
			this.shortcut = null;
		}
		
		Node addNext(int value) {
			Node nextNode = new Node(value);
			
			this.next = nextNode;
			
			return nextNode;
		}
		
		static Node getNode(int target) {
			Node temp = start;
			
			while(true) {
				if(temp == null) return null;
				if(temp.value == target) return temp;
				
				temp = temp.next;
			}
		}
	}
	
	static int[] dice;
	static int[] selections;
	static Node[] markers;
	static Node start;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		selections = new int[10];
		dice = new int[10];
		markers = new Node[4];
		ans = Integer.MIN_VALUE;
		
		for(int i = 0 ; i < 10 ; ++i) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		init();
		permutation(0);
		System.out.println(ans);
	}

	private static void permutation(int depth) {
		if(depth == 10) {
			// 마커를 시작점으로 초기화 
			Arrays.fill(markers, start);
			
			int score = play();
			ans = score > ans ? score : ans;
			
			// 윷판의 상태를 원래대로 돌려놓는다. 
			// 기존에 말을 옮기면서 말이 위치하고 있다고 표시해뒀으므로 
			recovery();
			
			return;
		}
		
		for(int i = 0 ; i < 4 ; ++i) {
			selections[depth] = i;
			permutation(depth + 1);
		}
	}

	private static void recovery() {
		for (int i = 0; i < 4; i++) {
			markers[i].isEmpty = true;
		}
	}

	private static int play() {
		int score = 0;
		
		for(int i = 0 ; i < 10 ; ++i) {
			// 현재 선택된 말을 이동시킨다.
			Node cur = markers[selections[i]];
			// 이전에 있던 자리를 비운다. 
			cur.isEmpty = true;
			
			for(int j = 0 ; j < dice[i] ; ++j) {
				if(j == 0 && cur.shortcut != null) {
					// 현재 위치에 지름길이 있다면 그 쪽으로 이동 
					cur = cur.shortcut;
				} else {
					// 현재 주사위 눈 만큼 이동
					cur = cur.next;
				}
			}

			markers[selections[i]] = cur;
			
			// 마지막 노드에 도착하지 않았으며 이미 말이 있는 노드라면 
			if(!cur.isEmpty && !cur.isEnd) {
				return 0;
			} else {
				cur.isEmpty = false;
				score += cur.value;
			}
		}
		
		return score;
	}

	private static void init() {
		start = new Node(0);
		Node end = null;
		Node center = new Node(25);
		
		// 바깥쪽
		Node temp = start;
		for(int i = 2 ; i <= 40 ; i += 2) {
			temp = temp.addNext(i);
		}
		
		// 마지막 도착지점은 자기순회하도록 한다. 
		end = temp.addNext(0);
		end.next = end;
		end.isEnd = true;
		
		// 10 -> 13 16 19 -> 25
		Node n = Node.getNode(10);
		n = n.shortcut = new Node(13);
		n = n.addNext(16);
		n = n.addNext(19);
		n.next = center;
		
		// 20 -> 22 24 -> 25
		n = Node.getNode(20);
		n = n.shortcut = new Node(22);
		n = n.addNext(24);
		n.next = center;
		
		// 30 -> 28 27 26 -> 25
		n = Node.getNode(30);
		n = n.shortcut = new Node(28);
		n = n.addNext(27);
		n = n.addNext(26);
		n.next = center;
		
		// 25 -> 30 35 40
		n = center.addNext(30);
		n = n.addNext(35);
		n.next = Node.getNode(40);
	}
}
