package eclipse;

/**
 *  链式队列
 */
class Link0{
    class Entry{
        int data;
        Entry next;
        public Entry(){
            data = -1;
            next = null;
        }
        public Entry(int data){
            this.data = data;
            next = null;
        }
    }

    public Entry front = null;
    public Entry rear = null;
    public int usedSize = 0;

    public boolean isEmpty(){
    return usedSize == 0;
    }
    public void insetTail(int data) {
        if (isEmpty()) {
            rear = new Entry(data);
            front = rear;
        } else {
            rear.next = new Entry(data);
            rear = rear.next;
        }
        usedSize++;
    }

    public void pop(){
        if(isEmpty()){
            return;
        }
        Entry cur = front;
            front = front.next;
            cur.next = null;
            usedSize--;
    }

    public int getTop(){
        if (isEmpty()){
            return -1;
        }
        return front.data;
    }

    public void print(){
        Entry cur = front;
        while (cur != null){
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}

public class LinkQueue {
    public static void main(String[] args){
        Link0 link = new Link0();
        link.insetTail(10);
        link.insetTail(20);
        link.insetTail(30);
        link.print();
        link.pop();
        link.print();
        System.out.println(link.getTop());
    }
}
