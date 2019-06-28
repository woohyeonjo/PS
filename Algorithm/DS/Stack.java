public class Stack {

    static Object[] memory;
    static int top;

    public Stack(int size) {
        memory = new Object[size];
        top = -1;
    }

    public void push(Object object) {
        memory[++top] = object;
    }

    public Object pop() {
        return memory[top--];
    }

    public Object peek() {
        return memory[top];
    }

    public boolean isEmpty(){
        return top == -1 ? true : false;
    }

    public static void main(String[] args) {
        Stack stack = new Stack(10);

        System.out.println(stack.isEmpty());
        stack.push(new Integer(1));
        stack.push(new Integer(2));
        System.out.println(stack.isEmpty());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());

    }
}
