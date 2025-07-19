package DS;

public class Queue_Circular {
    static Object[] memory;
    static int front, rear, max_size;

    public Queue_Circular(int size){
        max_size = size + 1;
        memory = new Object[max_size];
        front = 0;
        rear = -1;
    }

    public void enqueue(Object object){
        if(rear == max_size - 1) rear = -1;
        memory[++rear] = object;
    }

    public Object dequeue(){
        if(front == max_size) front = 0;
        return memory[front++];
    }

    public Object peek(){
        return memory[front];
    }

    public boolean isEmpty() {
        return front > rear ? true : false;
    }
}
