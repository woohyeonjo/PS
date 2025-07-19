package DS;

import java.util.Arrays;

public class CountingSort {
	public static void main(String[] args) {
		int[] arr = {1, 8, 3, 11, 2, 6, 1, 3, 4, 5};

		arr = countingSort(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static int[] countingSort(int[] arr) {
		int max = 0;
		for (int i = 0 ; i < arr.length ; ++i) max = arr[i] > max ? arr[i] : max;
		int[] output = new int[arr.length];
		int[] counting = new int[max + 1];
		
		for(int i = 0 ; i < arr.length ; ++i) counting[arr[i]]++;
		for(int i = 1 ; i < counting.length ; ++i) counting[i] += counting[i - 1];
		for(int i = arr.length - 1  ; i >= 0 ; --i) {
			output[counting[arr[i]] - 1] = arr[i];
			counting[arr[i]]--;
		}
		return output;
	}
}
