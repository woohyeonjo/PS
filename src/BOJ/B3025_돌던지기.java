package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B3025_돌던지기 {
	
	static char[][] map;
	static int[][] point;
	static int R, C, N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		point = new int[C][2];
		
		for(int r = 0 ; r < R ; ++r) {
			char[] line = br.readLine().toCharArray();
			for(int c = 0 ; c < C ; ++c) {
				map[r][c] = line[c];
			}
		}
		
		for(int c = 0 ; c < C ; ++c) cal(c);
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0 ; i < N ; ++i) {
			int col = Integer.parseInt(br.readLine()) - 1;
			
			// 계산된 위치에 착석
			map[point[col][0]][point[col][1]] = 'O';
			// 재계산 
			cal(col);
		}
		
		for(int r = 0 ; r < R ; ++r) {
			for(int c = 0 ; c < C ; ++c) {
				bw.append(map[r][c]);
			}
			bw.append('\n');
		}
		
		bw.flush();
		bw.close();
	}
	
	private static void cal(int throwCol) {
		int start = point[throwCol][0] - 1;
		
		slide(start, throwCol, throwCol);
	}

	private static void slide(int row, int col, int throwCol) {
		// 아래쪽에 돌이 없으면 수직 하강
		while(row + 1 < R && map[row + 1][col] == '.') {
			row++;
		}
		
		// 아래가 돌일 때만 미끄러진다. 
		if(row != R - 1 && map[row + 1][col] == 'O') {
			// 왼쪽을 확인한다.
			// 가장 왼쪽 또는 가장 아래쪽이 아니어야 한다.
			if(row != R - 1 && col != 0) {
				// 왼쪽과 왼쪽 아래가 빈 칸일 때 
				if(map[row][col - 1] == '.' && map[row + 1][col - 1] == '.') {
					slide(row + 1, col - 1, throwCol);
					return;
				}
			}
			
			// 오른쪽을 확인한다.
			// 가장 오른쪽 또는 가장 아래쪽이 아니어야 한다. 
			if(row != R - 1 && col != C - 1) {
				if(map[row][col + 1] == '.' && map[row + 1][col + 1] == '.') {
					slide(row + 1, col + 1, throwCol);
					return;
				}
			}
		}
		
		// 양쪽이 다 안될 때 돌을 안착시킨다.
		point[throwCol][0] = row;
		point[throwCol][1] = col;
	}
	
}
