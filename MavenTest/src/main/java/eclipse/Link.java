package eclipse;
/** 单链表全部 */

class LinkTest {

    class Entry {
        int data;
        Entry next;
        public Entry() {
            data = -1;
            next = null;
        }
        public Entry(int data) {
            this.data = data;
            next = null;
        }
    }

    public Entry head;
    public LinkTest() {
        head = new Entry();
    }

    /** 相交链表的头结点确定 */
    public LinkTest(Entry newHead) {
        head = newHead;
    }

    /** 头插法 */
    public void insertHead(int data) {
        Entry entry = new Entry(data);
        entry.next = head.next;
        head.next = entry;
    }

    /** 尾插法 */
    public void insertTail(int data) {
        Entry cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = new Entry(data);
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

    /** 指定位置插入 */
    public void insertPos(int pos, int data) {
        if (pos < 0 || pos > getLength()) {
            return;
        }
        Entry cur = head;
        for (int x = 0; x < pos; x++) {
            cur = cur.next;
        }
        Entry entry = new Entry(data);
        entry.next = cur.next;
        cur.next = entry;
    }

    /** 逆置一*/
    public void reverse() {
        Entry cur = head;
        if (cur.next == null || cur.next.next == null) {
            return;
        }
        Entry pos = head.next.next;
        cur.next.next = null;
        while (pos != null) {
            Entry temp = pos.next;
            pos.next = cur.next;
            cur.next = pos;
            pos = temp;
        }
    }

    /** 逆置二 */
    public Entry reverse1() {
        Entry newHead = null;
        Entry prev = null;
        Entry cur = head;
        while(cur != null){
            Entry curNext = cur.next;
            if(curNext == null){
                newHead = cur;
            }
            cur.next = prev;
            prev = cur;
            cur = curNext;
        }
        return newHead;
    }

    /** 求倒数第k个结点 */
    public int search(int key) {
        if (key < 0 || key > getLength()) {
            return -1;
        }
        Entry cur = head;
        for (int x = 0; x <= getLength() - key; x++) {
            cur = cur.next;
        }
        return cur.data;
    }

    /** 求倒数第k个结点2 */
    public int search1(int key) {
        if (key < 0 || key > getLength()) {
            return -1;
        }
        Entry cur1 = head;
        Entry cur2 = head;
        while (key - 1 > 0) {
            if (cur1.next != null) {
                cur1 = cur1.next;
                --key;
            }else {
                return -1;
            }
        }
        while (cur1.next != null) {

            cur1 = cur1.next;
            cur2 = cur2.next;

        }
        return cur2.data;
    }

    /** 删除指定结点 */
    public void delete(int key) {
        Entry entry1 = head;
        Entry entry2 = head.next;
        while (entry2 != null) {
            if (entry2.data == key) {
                entry1.next = entry2.next;
                entry2 = entry2.next;
            }else {
                entry1 = entry1.next;
                entry2 = entry2.next;
            }
        }
    }

    /** 输出 */
    public void print() {
        Entry cur = head.next;
        while(cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    /** 逆置对应的输出 */
    public void print1(Entry newHead) {
        Entry cur = newHead;
        while(cur.next != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    /** 制作环 */
    public void makeLoop() {
        Entry cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = head.next.next;
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

    /** 求环的长度1 *//*
    public int getLoopLength() {
        int len = 0;
        Entry fast = head;
        Entry slow = head;
        if (!isLoop()) {
            return -1;
        }
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
            len++;
        }
        return len;
    }*/

    /** 求环的长度2 */
    public int getLoopLength1() {
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
}

public class Link{

    /** 有序链表的合并 */
    public static<T> LinkTest mergeLink(LinkTest link1, LinkTest link2) {
        LinkTest.Entry newHead = null;
        LinkTest.Entry pos1 = link1.head.next;
        LinkTest.Entry pos2 = link2.head.next;
        if(pos1.data < pos2.data) {
            newHead = link1.head;
        }else {
            newHead = link2.head;
        }

        LinkTest.Entry newPos = newHead;
        while (pos1 !=null && pos2 != null) {
            if (pos1.data < pos2.data) {
                newPos.next = pos1;
                pos1 = pos1.next;
            }else {
                newPos.next = pos2;
                pos2 = pos2.next;
            }
            newPos = newPos.next;
        }
        if (pos1 != null) {
            newPos.next = pos1;
        }
        if (pos2 != null) {
            newPos.next = pos2;
        }
        return new LinkTest(newHead);
    }

    /** 有序链表合并后的输出 */
    public static void printf(LinkTest link) {
        link.print();
    }

    /** 制作两个相交链表 */
    public static void makeIntersectLink(LinkTest link1,LinkTest link2) {
        LinkTest.Entry head1 = link1.head;
        LinkTest.Entry head2 = link2.head;
        head2.next = head1.next.next;
    }

    /** 判断两个链表是否相交 */
    public static boolean isCut(LinkTest link1, LinkTest link2) {
        LinkTest.Entry head1 = link1.head;
        LinkTest.Entry head2 = link2.head;
        int newLength = link1.getLength() - link2.getLength();
        if (newLength < 0) {
            head1 = link2.head;
            head2 = link1.head;
        }
        for (int x = 0; x < newLength; x++) {
            head1 = head1.next;
        }
        while (head1.next != null && head2.next != null && head1 != head2) {
            head1 = head1.next;
            head2 = head2.next;
        }
        if (head1 == head2 && head1.next != null && head2.next != null) {
            return true;
        }
        return false;
    }

    public static void main(String [] args) {
        LinkTest link1 = new LinkTest();
        LinkTest link2 = new LinkTest();

        for (int x = 0; x < 12; x++) {
            link1.insertTail(x);
        }

		for (int x = 5; x < 8; x++) {
			link2.insertTail(x);
		}

		/*link1.insertTail(56);
		link2.insertTail(45);*/
		/*System.out.print("单链表1是 ：");
		link1.print();*/
		/*System.out.println(link2.search(4));
		System.out.println(link2.search1(4));*/
        //System.out.print("单链表2是 ：");
        //link2.print();

		/*System.out.println("单链表1的长度是 ：" + link1.getLength());
		System.out.println("单链表2的长度是 ：" + link2.getLength());*/

		/*link1.insertPos(2,111);
		System.out.print("链表1指定位置插入元素后是 ：");
		link1.print();

		link1.reverse();
		System.out.print("链表1用方法1逆置后是 ：");
		link1.print();*/

		/*System.out.print("链表2用方法2逆置后是 ：");
		link2.print1(link2.reverse1());*/

		/*link2.delete(5);
		System.out.print("单链表2删除指定元素后是 ：");
		link2.print();*/

        link2.makeLoop();
        System.out.print("链表2");
        System.out.println(link2.isLoop()? "是环":"不是环");
        System.out.println("环的开始点是 ：" + link2.startEntry());
        //System.out.println("环的长度是 ：" + link2.getLoopLength());
        System.out.println("环的长度是 ：" + link2.getLoopLength1());

		/*makeIntersectLink(link1,link2);
		System.out.print("两个链表");
		System.out.println(isCut(link1,link2)?"是相交的":"不是相交的");
		System.out.println("链表1是 ：");
		link1.print();
		System.out.println("链表2是 ：");
		link2.print();*/
		/*System.out.println("两个有序链表合并后的链表是 ：");
		printf(mergeLink(link1,link2));*/

    }
}


