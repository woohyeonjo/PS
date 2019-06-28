import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {5, 2, 7, 1, 4, 6, 3};

        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        //bubbleSort_Improved(arr);
    }

    public static void bubbleSort(int[] arr){
        for(int i = 0 ; i < arr.length - 1 ; ++i){
            for(int j = 0 ; j < arr.length - 1 ; ++j){
                if(arr[j] > arr[j + 1]) swap(arr, j, j + 1);
                System.out.println(Arrays.toString(arr));
            }
        }
    }

    public static void bubbleSort_Improved(int[] arr){
        int last_unsorted_index = arr.length - 1;
        for(int i = 0 ; i < arr.length - 1 ; ++i){
            boolean isSwaped = false;
            for(int j = 0 ; j < last_unsorted_index ; ++j){
                if(arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    isSwaped = true;
                }
            }
            last_unsorted_index--;
            System.out.println(Arrays.toString(arr));
            if(!isSwaped) break;
        }
    }

    private static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
