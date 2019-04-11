package Algorithm.BOJ.go;

public class B17136 {
	public static void main(String[] args) {
		dfs(0, 0);
	}

	private static void dfs(int I, int J) {
		
		for(int i = I ; i < 10 ; ++i) {
			for(int j = J ; j < 10 ; ++j) {
				System.out.println(i + ", " + j);
				dfs((J >= 9 ? I + 1 : I), (J + 1) %  10);
			}
		}
	}
}
