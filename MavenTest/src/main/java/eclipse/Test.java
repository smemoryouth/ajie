package eclipse;

class Linked<T>{

    class Entry{
        T data;
        Entry next;
        public Entry(){
            this.data = null;
            this.next = null;
        }
        public Entry(T data){
            this.data = data;
            this.next = null;
        }
    }

    public Entry head;

    public Linked(){
        this.head = new Entry();
    }

    /**
     * 头插法
     * @param data
     */
    public void insertHead(T data){
        Entry entry = new Entry(data);
        entry.next = head.next;
        head.next = entry;
    }

    /**
     * 尾插法
     * @param data
     */
    public void insertTail(T data){
        Entry entry = new Entry(data);
        Entry cur = head;
        while (cur.next != null){
            cur = cur.next;
        }
        cur.next = entry;
    }

    public int getLength(){
        int length = 0;
        Entry entry = head;
        if (entry.next == null){
            return -1;
        }
        while(entry.next != null){
            length++;
            entry = entry.next;
        }
        return length;
    }


}
/**
 * description：test
 *
 * @author ajie
 * data 2018/9/5 14:07
 */
public class Test {
    public static void main(String[] args){
        for (int i = 9; i >= 0; i--) {
            for(int j = 0; j <= i; j++){
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println(Math.PI);
    }
}
