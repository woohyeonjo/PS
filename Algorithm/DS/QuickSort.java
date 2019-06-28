import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {5, 6, 4, 10, 9, 1, 8, 7, 2, 3};
        System.out.println(Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        if(left >= right) return;

        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    public static int partition(int[] arr, int left, int right){
        int pivot = left;
        int low = left;
        int high = right;

        while(low < high){
            while(arr[pivot] >= arr[low]) low++;
            while(arr[pivot] < arr[high]) high--;
            if(low < high) swap(arr, low, high);
        }
        swap(arr, pivot, high);
        return high;
    }


    public static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
