package DS;

public class Queue_Array {

    static Object[] memory;
    static int front, rear;

    public Queue_Array(int size){
        memory = new Object[size];
        front = 0;
        rear = -1;
    }

    public void enqueue(Object object){
        memory[++rear] = object;
    }

    public Object dequeue(){
        return memory[front++];
    }

    public boolean isEmpty() {
        return front > rear ? true : false;
    }
}
