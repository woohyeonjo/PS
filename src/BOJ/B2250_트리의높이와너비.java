package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2250_트리의높이와너비 {
	private static class TreeNode {
		int parent;
		int x;
		int left, right;

		TreeNode(int left, int right) {
			this.left = left;
			this.right = right;
			this.parent = -1;
		}
	}

	static TreeNode[] tree;
	static int[] left, right;
	static int root;
	static int maxLevel, nodeIdx;
	static int N, ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		
		// 트리 초기화
		maxLevel = 0;
		nodeIdx = 1;
		tree = new TreeNode[N + 1];
		left = new int[N + 1];
		right = new int[N + 1];

		Arrays.fill(left, Integer.MAX_VALUE);
		Arrays.fill(right, Integer.MIN_VALUE);
		
		for(int i = 1 ; i < N + 1 ; ++i) {
			tree[i] = new TreeNode(-1, -1);
			// 양끝값 초기
			left[i] = N + 1;
			right[i] = 0;
		}

		// 트리 데이터 입력 
		for(int i = 1 ; i < N + 1 ; ++i) {
			st = new StringTokenizer(br.readLine());
			int root = stoi(st.nextToken());
			int left = stoi(st.nextToken());
			int right = stoi(st.nextToken());
			
			tree[root].left = left;
			tree[root].right = right;
			
			if(left != -1) {
				tree[left].parent = root;
			}
			if(right != -1) {
				tree[right].parent = root;
			}
		}
		
		for(int i = 1 ; i < N + 1 ; ++i) {
			if(tree[i].parent == -1) root = i;
		}
		
		// 중위순회를 통한 양 끝값 갱신 
		dfs(root, 1);
		
		int level = 1;
		int maxSub = right[1] - left[1] + 1;
		for(int i = 2 ; i <= maxLevel ; i++) {
			int sub = right[i] - left[i] + 1;
			
			// 클 때만 갱신되기 때문에 넓이가 같을 때 작은 레밸이 보장된다.
			if(sub > maxSub) {
				level = i;
				maxSub = sub;
			}
		}

		System.out.println(level + " " + maxSub);
	}
	
	private static void dfs(int root, int level) {
		TreeNode tn = tree[root];
		
		// 트리레밸 갱신 
		maxLevel = level > maxLevel ? level : maxLevel;
		
		// 중위순회하며 각 노드의 열번호 갱신 
		if(tn.left != -1) {
			dfs(tn.left, level + 1);
		}
		
		// 열번호 늘리면서 해당 래벨의 양쪽 끝 갱신 (최솟값, 최댓값) 
		tn.x = nodeIdx++;
		left[level] = left[level] > tn.x ? tn.x : left[level];
		right[level] = tn.x;
		
		if(tn.right != -1) {
			dfs(tn.right, level + 1);
		}
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}

}
