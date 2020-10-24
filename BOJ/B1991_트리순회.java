package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1991_트리순회 {

	private static class Tree {
		TreeNode root;

		Tree(char data, char left, char right) {
			if(data != '.') {
				this.root = new TreeNode(data);
			}
			if(left != '.') {
				this.root.left = new TreeNode(left);
			}
			if(right != '.') {
				this.root.right = new TreeNode(right);
			}
		}

		// 입력받은 서브트리가 data가 일치하는 곳 추가되도록 한다.
		void add(char data, char left, char right) {
			next(this.root.left, data, left, right);
			next(this.root.right, data, left, right);
		}

		// 데이터가 일치하는 노드를 재귀적으로 찾아낸다.
		void next(TreeNode tn, char data, char left, char right) {
			if (tn == null)
				return;

			if (tn.data == data) {
				if (left != '.') {
					tn.left = new TreeNode(left);
				}
				if (right != '.') {
					tn.right = new TreeNode(right);
				}
			}

			next(tn.left, data, left, right);
			next(tn.right, data, left, right);
		}

		void preorder(TreeNode tn) {
			System.out.print(tn.data);

			if (tn.left != null) {
				preorder(tn.left);
			}

			if (tn.right != null) {
				preorder(tn.right);
			}
		}

		void inorder(TreeNode tn) {
			if (tn.left != null) {
				inorder(tn.left);
			}

			System.out.print(tn.data);

			if (tn.right != null) {
				inorder(tn.right);
			}
		}

		void postorder(TreeNode tn) {
			if (tn.left != null) {
				postorder(tn.left);
			}

			if (tn.right != null) {
				postorder(tn.right);
			}

			System.out.print(tn.data);
		}

	}

	private static class TreeNode {
		TreeNode left, right;
		char data;

		TreeNode(char data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		Tree t = new Tree(st.nextToken().charAt(0), st.nextToken().charAt(0), st.nextToken().charAt(0));

		for (int i = 0; i < N - 1; ++i) {
			st = new StringTokenizer(br.readLine());
			t.add(st.nextToken().charAt(0), st.nextToken().charAt(0), st.nextToken().charAt(0));
		}

		t.preorder(t.root);
		System.out.println();
		t.inorder(t.root);
		System.out.println();
		t.postorder(t.root);
	}
}
