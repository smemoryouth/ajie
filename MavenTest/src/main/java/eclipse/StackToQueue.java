package eclipse;

class Stack {
    int[] arr;
    int top;

    public Stack() {
        this(10);
    }

    public Stack(int length) {
        arr = new int[length];
        top = 0;
    }

    public boolean isFull() {
        return top == arr.length;
    }

    public void insert(int data) {
        if (isFull()) {
            return;
        }
        arr[top++] = data;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public int getTop() {
        if (isEmpty()) {
            return -1;
        }
        return arr[top - 1];
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        int num = arr[top - 1];
        top--;
        return num;
    }

    public void print() {
        for (int x = 0; x < top; x++) {
            System.out.print(arr[x] + " ");
        }
        System.out.println();
    }
}

/**
 * @author 阿劼
 */
public class StackToQueue {
    public static void insertQueue(int data, Stack s1) {
        s1.insert(data);
    }

    /**
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int popQueue(Stack s1, Stack s2) {
        //int i = -1;
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.insert(s1.getTop());
                s1.pop();
            }
        }
        int num = -1;
        if (!s2.isEmpty()) {
            num = s2.getTop();
            s2.pop();
        } else {
            System.out.println("wushujv");
        }
        return num;
    }

    public static void main(String[] args) {
        Stack s1 = new Stack();
        Stack s2 = new Stack();

        insertQueue(10, s1);
        insertQueue(20, s1);
        insertQueue(30, s1);
        insertQueue(40, s1);
        s1.print();
        System.out.println(popQueue(s1, s2));
        s2.print();
    }
}

