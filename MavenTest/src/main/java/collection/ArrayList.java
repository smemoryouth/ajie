package collection;


import java.util.Arrays;

/**
 * description：实现MyArrayList
 *
 * @author ajie
 * data 2018/7/16
 */
class MyArrayList<T> {
    /**
     * 默认初始容量
     */
    private static final int MENDACITY = 10;
    /**
     * 底层数组
     */
    private int[] array;
    /**
     * 容量
     */
    private int size;

    /**
     * 无参构造函数
     */
    public MyArrayList() throws Exception {
        this(MENDACITY);
    }

    /**
     * 指定容量构造函数
     *
     * @param capacity
     * @throws Exception 非法容量
     */
    public MyArrayList(int capacity)throws Exception {
        if (capacity < 0) {
            throw new Exception("error capacity!");
        }
        this.size = -1;
        this.array = new int[capacity];
    }

    /**
     * 添加元素
     *
     * @param value
     */
    public void add(Integer value) {
        if (size < array.length - 1) {
            array[++size] = value;
        } else {
            array = Arrays.copyOfRange(array, 0, (3 * array.length) >> 1);
            array[++size] = value;
        }

    }

    /**
     * 添加数组
     *
     * @param arr
     */
    public void addAll(int[] arr) {
        if (array.length - 1 > size + arr.length) {
            System.arraycopy(arr, 0, array, size + 1, arr.length);
        } else {
            array = Arrays.copyOfRange(array, 0, size + 1 + arr.length);
            System.arraycopy(arr, 0, array, size + 1, arr.length);
        }
        size = size + arr.length;
    }

    /**
     * 默认删除
     *
     * @return
     */
    public int remove() {
        if (array.length == 0) {
            return -1;
        }
        int val = array[size];
        array[size--] = -1;
        return val;
    }

    /**
     * 指定下标删除
     *
     * @param index
     * @throws Exception 非法下标
     */
    public boolean remove(int index) throws Exception {
        if (array.length == 0 || index < 0 || index > size) {
            throw new Exception("error index!");
        } else {
            System.arraycopy(array, index + 1, array, index, array.length - 1 - index);
            array[size--] = -1;
            return true;
        }
    }

    /**
     * 修改
     *
     * @param index
     * @param val
     * @return
     * @throws Exception
     */
    public int set(int index, Integer val) throws Exception {
        if (index < 0 || index > size) {
            throw new Exception("角标越界！");
        }
        int oldValue = array[index];
        array[index] = val;
        return oldValue;
    }

    /**
     * 获取
     *
     * @param index
     * @return
     */
    public int get(int index) {
        return (index < 0 || index > size) ? -1 : array[index];
    }

    public void print() {
        for (int i = 0; i <= size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        MyArrayList<Integer> arrayList = new MyArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
        }
        int[] a = new int[]{10, 20, 30};
        //arrayList.add(1000);
        arrayList.print();
        System.out.println(arrayList.get(8));
        System.out.println(arrayList.remove());
        System.out.println(arrayList.remove(2));
        System.out.println(arrayList.set(5, 100));
        arrayList.print();
        arrayList.addAll(a);
        arrayList.print();
    }
}
