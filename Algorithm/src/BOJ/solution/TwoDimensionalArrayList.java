package BOJ.solution;
import java.util.ArrayList;

public class TwoDimensionalArrayList {
	public static void main(String[] args) {
		ArrayList<Integer>[][] list = new ArrayList[5][];
		for(int i = 0;  i < list.length; i++) {
			list[i] = new ArrayList[5];
		}
		for(int i = 0; i < list.length; i++) {
			for(int j = 0 ; j < list.length; j++) {
				list[i][j] = new ArrayList<>();
				list[i][j].add(i + j);
				list[i][j].add(i - j);
				list[i][j].add(i * j);
			}
		}
		for(int i = 0 ; i < list.length; i++) {
			for(int j = 0; j < list.length; j++) {
				System.out.println("go " + i + ", " + j);
				//for(Integer e: list[i][j]) System.out.print(e + ", ");
				for(int k = 0 ; k < list[i][j].size(); k++) System.out.print(list[i][j].get(k) + ", ");
				System.out.println();
			}System.out.println();
		}
		
		int[][] a = new int[5][5];
	}
}
