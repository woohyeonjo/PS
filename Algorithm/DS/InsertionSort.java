import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {5, 1, 6, 2, 3, 4};

        System.out.println(Arrays.toString(arr));
        insertionSort(arr);
    }

    static private void insertionSort(int[] arr){
        int temp, j;

        for(int i = 1 ; i < arr.length ; ++i){
            temp = arr[i];
            for(j = i - 1 ; j >= 0 && arr[j] > temp ; --j)  arr[j + 1] = arr[j];
            arr[j + 1] = temp;
            System.out.println(Arrays.toString(arr));
        }
    }
}
