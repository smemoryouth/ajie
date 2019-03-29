package fuxi;

/**
 * description：循环单链表
 *
 * @author ajie
 * data 2018/9/14 1:53
 */
class Link{

    Entry head;
    /** 初始化一个循环单链表 */
    public Link() {
        head = new Entry();
        head.next = head;
    }

    /** 节点类，用于节点初始化 */
    class Entry {
        int data;
        Entry next;
        public Entry() {
            this.data = -1;
            next = null;
        }
        public Entry(int data) {
            this.data = data;
            next = null;
        }
    }

    /** 头插法 */
    public void insertHead(int data) {
        Entry entry = new Entry(data);
        entry.next = head.next;
        head.next = entry;
    }

    /** 尾插法 */
    public void insertTail(int data) {
        Entry entry = head;
        while (entry.next != head) {
            entry = entry.next;
        }
        Entry cur = new Entry (data);
        // 最后一个结点指向头结点
        cur.next = entry.next;
        entry.next = cur;
    }

    /** 删除指定数据 */
    public void delete(int key) {
        // 定义两个指针，他们相差一个结点
        Entry prev = head;
        Entry entry = head.next;
        while (entry != head) {
            /* 若前一个指针对应数据是要删除的数据，则后一个指针直接指向前一个指针的下
            一个结点，从而达到删除节点的目的，此方法可以删除多个带有相同数据的结点*/
            if (entry.data == key) {
                prev.next = entry.next;
                entry = entry.next;
            }else{
                prev = entry;
                entry = entry.next;
            }
        }
    }

    /** 求单次循环的长度 */
    public int getlength() {
        int len = 0;
        Entry entry = head;
        if(entry.next == head) {
            return -1;
        }
        while(entry.next != head) {
            len++;
            entry = entry.next;
        }
        return len;
    }

    /** 输出 */
    public void printf(){
        Entry entry = head.next;
        while (entry != head){
            System.out.print(entry.data + " ");
            entry = entry.next;
        }
        System.out.println();
    }
}

/** 主类 */
public class Demo0 {

    public static void main (String[] args) {
        Link link = new Link();
        // 动态制作一个循环单链表
        for (int x = 0; x < 5; x++){
            link.insertHead(x);
        }

        System.out.print("单次循环的链表数据是:");
        link.printf();

        System.out.println("=======================================");
        link.insertHead(5);
        link.insertTail(5);
        System.out.print("插入数据后的单次循环的链表数据是:");
        link.printf();

        System.out.println("=======================================");
        link.delete(5);
        System.out.print("删除指定数据后的单次循环的链表数据是:");
        link.printf();

        System.out.println("=======================================");
        System.out.println("单次循环的链表长度是: " + link.getlength());
    }
}
