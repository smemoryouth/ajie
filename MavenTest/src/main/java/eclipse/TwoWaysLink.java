package eclipse;

/**
 * description：双向链表
 *
 * @author ajie
 * data 2018/6/14
 */
class DoubleLink{
    class Entry{
        int data;
        Entry next;
        Entry prev;
        public Entry(){
            data = -1;
            next = null;
            prev = null;
        }
        public Entry(int data){
            this.data = data;
            next = null;
            prev = null;
        }
    }
    private Entry head;
    DoubleLink(){
        head = new Entry();
    }

    /** 头插法 */
    public void insertHead(int data){
        Entry entry = new Entry(data);
        entry.next = head.next;
        entry.prev = head;
        head.next = entry;
        if (entry.next != null){
            entry.next.prev = entry;
        }
    }

    /** 尾插法 */
    void insertTail(int data){
        Entry cur = head;
        while (cur.next != null){
            cur = cur.next;
        }
        Entry entry = new Entry(data);
        cur.next = entry;
        entry.prev = cur;
    }

    /** 取长度*/
    public int getLength() {
        int len = 0;
        Entry cur = head;
        if (cur.next == null) {
            return -1;
        }
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }
        return len;
    }

    /** 删除指定结点 */
    public  void  delete(int data){
        Entry cur = head.next;
        while (cur != null){
            if (cur.data == data){
                cur.prev.next = cur.next;
                if (cur.next != null){
                    cur.next.prev = cur.prev;
                }
            }
            cur = cur.next;
        }
    }

    /** 制作环 */
    void makeLoop() {
        Entry cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = head.next;
        head.next.prev = cur;
    }

    /** 判断是否是环 */
    public boolean isLoop() {
        Entry fast = head;
        Entry slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /** 求环的起点 */
    public int startEntry() {
        Entry fast = head;
        Entry slow = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast.data;
    }

    /** 求环的长度2 */
    public int getLoopLength() {
        int len = 0;
        boolean flag = false;
        Entry fast = head;
        Entry slow = head;
        if (!isLoop()) {
            return -1;
        }
        while(isLoop()) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow && flag == true) {
                break;
            }
            if (fast == slow && flag == false) {
                flag= true;
            }
            if (flag == true) {
                len++;
            }
        }
        return len;
    }

    /** 输出 */
    public void printf(){
        Entry entry =  head.next;
        while (entry != null){
            System.out.print(entry.data + " ");
            entry = entry.next;
        }
        System.out.println();
    }

}

public class TwoWaysLink{
    public static void main(String[] args){
        DoubleLink link = new DoubleLink();
        for (int x = 0; x < 1; x++){
            link.insertTail(x);
        }

      /*  System.out.print("头插法获得的链表是：");
        link.printf();*/

        /*System.out.println("==================================");
        for (int x = 5; x < 10; x++){
            link.insertTail(x);
        }*/

       /* System.out.print("尾插法获得的链表是：");
        link.printf();

        System.out.println("==================================");
        link.delete(5);
        System.out.print("删除指定数据后的链表是：");
        link.printf();

        System.out.println(link.getLength());*/

        link.makeLoop();
        System.out.println(link.getLoopLength());
        System.out.println(link.startEntry());

    }
}

