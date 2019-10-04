package DS;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 7, 1, 4, 6, 3};
        Max_Heap heap = new Max_Heap(100);

        System.out.println(Arrays.toString(arr));
        for(int i = 0 ; i < arr.length ; ++i) heap.push(arr[i]);
        for(int i = 0 ; i < arr.length ; ++i) arr[i] = heap.pop();
        System.out.println(Arrays.toString(arr));
    }

    static class Max_Heap {
        int[] memory;
        int max_heap_size;

        public Max_Heap(int size){
            memory = new int[size];
            max_heap_size = 0;
        }

        public void push(int num){
            memory[++max_heap_size] = num;
            for(int i = max_heap_size ; i > 1 ; i /= 2){
                if(memory[i / 2] < memory[i]) swap(i / 2, i);
            }
        }

        public int pop() {
            if(max_heap_size == 0) return 0;
            int temp = memory[1];
            memory[1] = memory[max_heap_size];
            memory[max_heap_size--] = 0;

            for(int i = 1 ; i * 2 <= max_heap_size ;){
                if(memory[i * 2] < memory[i] && memory[i * 2 + 1] < memory[i]) break;
                else if(memory[i * 2] > memory[i * 2 + 1]){
                    swap(i, i * 2);
                    i = i * 2;
                }
                else {
                    swap(i, i * 2 + 1);
                    i = i * 2 + 1;
                }
            }
            return temp;
        }

        public void swap(int a, int b){
            int temp = memory[a];
            memory[a] = memory[b];
            memory[b] = temp;
        }

    }
}
