    import java.util.Arrays;

    public class MergeSort {
        static int[] sorted;
        public static void main(String[] args) {
            int[] arr = {5, 2, 7, 1, 4, 6, 3};
            sorted = new int[arr.length];

            System.out.println(Arrays.toString(arr));
            mergeSort(arr, 0, arr.length - 1);
            System.out.println(Arrays.toString(arr));
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
                if(arr[i] > arr[j]) sorted[k++] = arr[j++];
                else sorted[k++] = arr[i++];
            }
            while(j <= right) sorted[k++] = arr[j++];
            while(i <= mid) sorted[k++] = arr[i++];
            for(int m = left ; m <= right ; ++m) arr[m] = sorted[m];
        }
    }
