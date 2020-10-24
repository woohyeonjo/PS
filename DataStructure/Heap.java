package DS;

public class Heap {
    static int[] memory;
    static int heap_size;

    public Heap(int memory_size) {
        memory = new int[memory_size];
        heap_size = 0;
    }

    public void insert(int num){
        memory[++heap_size] = num;

        for(int i = heap_size ; i > 1 ; i /= 2){
            if (memory[i/2] < memory[i]) swap(i/2, i);
        }
    }

    public int delete(){
        if(heap_size == 0) return 0;

        int temp = memory[1];
        memory[1] = memory[heap_size];
        memory[heap_size--] = 0;

        for(int i = 1; i * 2 <= heap_size;){
            if(memory[i] > memory[i * 2] && memory[i] > memory[i * 2 + 1]) break;
            else if (memory[i * 2] > memory[i * 2 + 1]) {
                swap(i, i * 2);
                i *= 2;
            } else {
                swap(i, i * 2 + 1);
                i = i * 2 + 1;
            }
        }

        return temp;
    }

    private void swap(int first, int second){
        int temp = memory[first];
        memory[first] = memory[second];
        memory[second] = memory[temp];
    }
}
