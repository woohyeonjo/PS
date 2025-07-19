package DS;

import java.util.Arrays;

public class SelectionSort {
    static int[] arr = {5, 2, 7, 1, 4, 6, 3};
    public static void main(String[] args) {
        System.out.println(Arrays.toString(arr));

        for(int i = 0 ; i < arr.length - 1 ; ++i){
            int min = arr[i];
            int minIdx = i;
            for(int j = i + 1 ; j < arr.length ; ++j){
                if(min > arr[j]) {
                    min = arr[j];
                    minIdx = j;
                }
            }
            swap(i, minIdx);
            System.out.println(Arrays.toString(arr));
        }
    }

    private static void swap(int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
