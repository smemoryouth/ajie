package collection;

/**
 * description：LinkedList实现
 *
 * @author ajie
 * data 2018/7/16
 */
class MyLinkedList<T> {
    /**
     * 结点类
     */
    class Entry{
        /**
         * 后驱
         */
        private Entry next;
        /**
         * 前驱
         */
        private Entry prev;
        /**
         * 数据
         */
        private T data;
        /**
         * 下标
         */
        private int index;

        /**
         * 默认构造方法
         */
        private Entry(){
            this.data = null;
            this.index = -1;
            this.next = null;
            this.prev = null;
        }

        /**
         * 带数据的构造方法
         * @param data
         */
        private Entry(T data){
            this.data = data;
            this.index = -1;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * 构造函数
     */
    private Entry head;
    public MyLinkedList(){
        head = new Entry();
    }

    /**
     * 添加
     * @param data
     */
    public void add(T data){
        Entry cur = head;
        while (cur.next != null){
            cur = cur.next;
        }
        Entry entry = new Entry(data);
        cur.next = entry;
        entry.prev = cur;
        entry.index = entry.prev.index + 1;
    }

    /**
     * 默认删除最后一个结点
     * @return
     */
    public T remove(){
        Entry cur = head;
        while(cur.next != null) {
            cur = cur.next;
        }
        T oldValue = cur.data;
        cur.index = -1;
        cur.data = null;
        cur.prev.next = null;
        return oldValue;
    }

    /**
     * 删除指定结点
     * @param data
     */
    public void remove(T data){
        Entry cur = head.next;
        while (cur != null){
            if (cur.data == data){
                cur.data = null;
                cur.prev.next = cur.next;
                while(cur.next != null){
                    cur.next.index--;
                    cur = cur.next;
                }
                if (cur.next != null){
                    cur.next.prev = cur.prev;
                }
            }
            cur = cur.next;
        }
    }

    /**
     * get方法
     * @param index
     * @return
     */
    public T get(int index){
        Entry cur = head;
        while(cur.next != null) {
            cur = cur.next;
            if (cur.index == index){
                return cur.data;
            }
        }
        return null;
    }

    /**
     * set方法
     * @param index
     * @param data
     * @return
     */
    public T set(int index, T data){
        Entry cur = head;
        while (cur.next != null){
            cur = cur.next;
            if (cur.index == index){
                T oldValue = cur.data;
                cur.data = data;
                return oldValue;
            }
        }
        return null;
    }

    /**
     * 输出
     */
    public void print(){
        Entry cur = head.next;
        while (cur != null){
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.add(i);
        }
        linkedList.print();
        System.out.println(linkedList.remove());
        linkedList.print();
        linkedList.remove(5);
        linkedList.print();
        System.out.println(linkedList.get(6));
        System.out.println(linkedList.set(6, 100));
        linkedList.print();
    }

}
