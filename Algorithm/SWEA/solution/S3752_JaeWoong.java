package Algorithm.SWEA.solution;

import java.util.Scanner;

public class S3752_JaeWoong
{
	static int T, N;
	static char[][] a = new char[100][100];
	static int dir[][] = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
	static int res;
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++)
		{
			res=0;
			N=sc.nextInt();
			for (int i = 0; i < N; i++) for(int j=0;j<N;j++) a[i][j] =sc.next().charAt(0);
			
			for(int i=0;i<N;i++) for(int j=0;j<N;j++)
			{
				res=Math.max(calc(i,j), res);
			}
			System.out.println("#"+tc+" "+res);
		}
	}
	
	public static int calc(int row,int col)
	{
		int cnt=0;
		
		for(int i=0;i<8;i++)
		{
			int r =row+dir[i][0];
			int c = col+dir[i][1];
			
			if(r<0 || c<0 || r>=N || c>=N) continue;
			if(a[r][c] =='W') cnt++;
		}
		
		return cnt;
	}

}
