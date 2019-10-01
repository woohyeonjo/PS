package CodingTest.ESTsoft_191001;

public class Solution3 {
	
	static int[][] M;
	public static void main(String[] args) {
		int[] C = {0, 0, 1, 1, 2};
		System.out.println(solution(2, 3, C));
	}

	
	 public static String solution(int U, int L, int[] C) {
	        M = new int[2][C.length];
	        fill(0, 0, 0, U, L, C);
	        
	        String upper = "";
	        String lower = "";
	        
	        for(int i = 0 ; i < C.length ; ++i){
	           upper += M[0][i];
	           lower += M[1][i];
	        }
	        
	        if((upper + lower).length() < 1) return "IMPOSSIBLE";
	        else return upper + "," + lower;
	    }
	    static boolean check(int U, int L, int[] C){
	        int u = 0;
	        int l = 0;
	        
	        for(int i = 0 ; i < C.length ; ++i){
	            if((u += M[0][i]) > U) return false;
	            if((l += M[1][i]) > L) return false;
	            if(M[0][i] + M[1][i] != C[i]) return false;
	            
	        }
	        if(u == U && l == L) return true;
	        else return false;
	    }
	    
	    static boolean fill(int index, int u, int l, int U, int L, int[] C){
	        
	        if(index == C.length){
	            if(check(U, L, C)) {
	                return true;
	            } else return false;
	        }
	        
	        if(C[index] == 0){
	            M[0][index] = 0;
	            M[1][index] = 0;
	            if(fill(index + 1, u, l, U, L, C)) return true;
	        } else if(C[index] == 2) {
	            M[0][index] = 1;
	            M[1][index] = 1;
	            if(fill(index + 1, u + 1, l + 1, U, L, C)) return true;
	        } else {
	            M[0][index] = 1;
	            M[1][index] = 0;
	            if(fill(index + 1, u + 1, l, U, L, C)) return true;
	            M[0][index] = 0;
	            M[1][index] = 1;
	            if(fill(index + 1, u, l + 1, U, L, C)) return true;
	        }
	        return false;
	    }
}

   

