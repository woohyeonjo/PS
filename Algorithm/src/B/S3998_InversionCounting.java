package B;

import java.util.Scanner;

public class S3998_InversionCounting {
	
	static int[] arr, sorted;
	static int T, N, cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int t = 1; t <= T ; ++t) {
			N = sc.nextInt();
			cnt = 0;
			arr = new int[N];
			sorted = new int[N];
			
			for(int i = 0 ; i < N ; ++i) arr[i] = sc.nextInt();
			
			mergeSort(arr, 0, arr.length - 1);
			
			System.out.println("#" + t + " " + cnt);
		}
	}
	
	public static void mergeSort(int[] arr, int left, int right){
        if(left < right){
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int i = left; // 왼쪽 배열의 인덱스
        int j = mid + 1; // 오른쪽 배열의 인덱스
        int k = left; // 정렬된 배열의 인덱스

        while(i <= mid && j <= right){
            if(arr[i] > arr[j]) {
            	sorted[k++] = arr[j++];
            	cnt++;
            }
            else sorted[k++] = arr[i++];
        }
        while(j <= right) sorted[k++] = arr[j++];
        while(i <= mid) sorted[k++] = arr[i++];
        for(int m = left ; m <= right ; ++m) arr[m] = sorted[m];
    }
}
