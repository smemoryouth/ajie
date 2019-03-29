package collection;

/**
 * description：最大堆实现
 *
 * @author ajie
 * data 2018/7/7
 */
public class MaxHeap {
    /**
     * 默认容量
     */
    public static final int MAXCAPACITY = 11;
    private static final int MAXNUMBER = 10;

    /**
     * 默认最大容量
     */
    public int allSize;
    /**
     * 已使用容量
     */
    public int usedSize;
    /**
     * 存储用数组
     */
    public int[] arr;

    /**
     * 无参构造
     */
    public MaxHeap() {
        this.allSize = MAXCAPACITY;
        this.usedSize = -1;
        this.arr = new int[MAXCAPACITY];
    }

    /**
     * 有参构造
     *
     * @param capacity 自定义容量
     * @throws Exception 指定容量异常
     */
    public MaxHeap(int capacity) throws Exception {
        if (capacity < 1 || capacity > MAXCAPACITY) {
            throw new Exception("错误的容量！");
        }
        this.allSize = capacity;
        this.usedSize = -1;
        this.arr = new int[capacity];
    }

    /**
     * 添加元素
     *
     * @param value 元素值
     */
    public void add(int value) {
        try {
            arr[++usedSize] = value;
            int fatherIndex = (usedSize - 1) >> 1;
            int sonIndex = usedSize;
            rightAdjust(fatherIndex, sonIndex);
        } catch (Exception e) {
            System.out.println(e + "：容量不足！");
        }
    }

    /**
     * 获取头元素
     *
     * @return 头元素值
     */
    public int getTop() {
        return arr[0];
    }

    /**
     * 向上调整
     */
    private void rightAdjust(int fatherIndex, int sonIndex) {
        if (usedSize == 0) {
            return;
        }
        while (fatherIndex >= 0) {
            int tmp = arr[fatherIndex];
            if (tmp > arr[sonIndex]) {
                break;
            } else {
                arr[fatherIndex] = arr[sonIndex];
                arr[sonIndex] = tmp;
            }
            sonIndex = fatherIndex;
            fatherIndex = (fatherIndex - 1) >> 1;
        }
    }

    /**
     * 向下调整
     */
    private void leftAdjust(int fatherIndex, int sonIndex) {
        if (usedSize == 0) {
            return;
        }
        while (sonIndex < usedSize) {
            int tmp = arr[fatherIndex];
            if (arr[sonIndex] < arr[sonIndex + 1]) {
                sonIndex++;
            }
            if (tmp < arr[sonIndex]) {
                arr[fatherIndex] = arr[sonIndex];
                arr[sonIndex] = tmp;
            } else {
                break;
            }
            fatherIndex = sonIndex;
            sonIndex = sonIndex * 2 + 1;
        }
    }

    /**
     * 无参删除方法
     *
     * @return 堆顶元素
     */
    public int delete() {
        int oldValue = getTop();
        arr[0] = arr[usedSize];
        arr[usedSize--] = -1;
        leftAdjust(0, 1);
        return oldValue;
    }

    /**
     * 指定元素删除
     *
     * @param value 要删除的元素
     * @return
     */
    public boolean delete(int value) {
        for (int i = 0; i <= usedSize; i++) {
            if (arr[i] == value) {
                arr[i] = arr[usedSize];
                arr[usedSize--] = -1;
                leftAdjust(i, i * 2 + 1);
                return true;
            }
        }
        return false;
    }

    /**
     * 数组元素输出
     */
    public void print() {
        for (int i = 0; i <= usedSize; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 求10w个数据中的10个最大值
     */
    public void getTenMinNumber() {
        for (int i = 0; i < MAXNUMBER; i++) {
            System.out.print(delete() + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) throws Exception {
        MaxHeap maxHeap = new MaxHeap(9);
        maxHeap.add(20);
        maxHeap.add(30);
        maxHeap.add(10);
        maxHeap.add(40);
        maxHeap.add(15);
        maxHeap.add(50);
        maxHeap.add(1);
        maxHeap.add(12);
        maxHeap.add(22);

        maxHeap.print();
        System.out.println(maxHeap.delete());
        maxHeap.print();
        System.out.println(maxHeap.delete(20));
        maxHeap.print();
        System.out.println(maxHeap.getTop());
    }
}
