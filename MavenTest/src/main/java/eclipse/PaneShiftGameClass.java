package eclipse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;


/**
 * description：puzzle课堂程序，递归
 *
 * @author ajie
 * data 2018/6/16
 */
public class PaneShiftGameClass {
    /**
     * 原始数组默认大小
     */
    private int DEFAULT_SIZE = 10;
    /**
     * 存放原始数据
     */
    private ArrayList<Integer> puzzleList;
    private int size;
    /**
     * 栈,正确执行的步骤记录，记录当前位置
     */
    private LinkedList<Integer> stack;

    /**
     * 默认构造方法
     */
    public PaneShiftGameClass() {
        this.size = this.DEFAULT_SIZE;
        this.puzzleList = new ArrayList<>();
        this.stack = new LinkedList<>();
        init();
        printPaneShift();
        // 默认从1位置开始
        this.stack.addFirst(1);
    }

    /**
     * 有参构造方法
     * @param size 原始数据大小
     */
    public PaneShiftGameClass(int size) {
        if (size < 0 && size > Integer.MAX_VALUE) {
            System.out.println("输入参数不合法");
            return;
        }
        this.size = size;
        this.puzzleList = new ArrayList<>(size);
        this.stack = new LinkedList<>();
        init();
        printPaneShift();
        this.stack.addFirst(1);
    }

    /**
     * 数组初始化
     */
    private void init() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            // 由[0,9)的随机数变为[1,9]的随机数
            int val = random.nextInt(9) + 1;
            puzzleList.add(val);
        }
    }

    /**
     * 判断是否能执行完
     *
     * @return
     */
    public boolean isSuccess() {
        boolean result = rightMove();
        return result;
    }

    /**
     * 向右移动
     *
     * @return 右移是否成功
     */
    public boolean rightMove() {
        Integer id = this.stack.getFirst();
        if (id == size) {
            // 移动到最后一个位置，成功
            return true;
        }
        Integer val = this.puzzleList.get(id - 1);
        id = id + val;
        if (id > size) {
            // 右移越界
            return false;
        }
        if (this.stack.contains(id)) {
            // 位置重复
            return false;
        }
        this.stack.addFirst(id);
        boolean rihtMove = rightMove();
        if (!rihtMove) {
            // 左移
            rihtMove = lefttMove();
        }
        if (!rihtMove) {
            this.stack.removeFirst();
        }
        return rihtMove;
    }

    /**
     * 左移
     *
     * @return
     */
    public boolean lefttMove() {
        Integer id = this.stack.getFirst();
        Integer val = this.puzzleList.get(id - 1);
        id = id - val;
        if (id < 1) {
            // 左移越界
            return false;
        }
        if (this.stack.contains(id)) {
            // 位置重复
            return false;
        }
        this.stack.addFirst(id);
        boolean move = rightMove();
        if (!move) {
            move = lefttMove();
        }
        if (!move) {
            this.stack.removeFirst();
        }
        return false;
    }

    /**
     * 打印数组
     */
    public void printPaneShift() {
        for (Integer aPuzzleList : puzzleList) {
            System.out.print(aPuzzleList + " ");
        }
        System.out.println();
    }

    /**
     * 输出移动步骤
     */
    private void printMove() {
        Integer val = this.stack.getFirst();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("移动过程:\n");
        if (val == size) {
            Integer last = this.stack.getLast();
            int stackSize = this.stack.size();
            while (stackSize > 0) {
                this.stack.removeLast();
                int next = 0;
                stackSize = this.stack.size();
                if (stackSize > 0) {
                    next = this.stack.getLast();
                }
                if (next != 0) {
                    if (next > last) {
                        // 右移
                        stringBuffer.append(last);
                        stringBuffer.append("号位置右移到");
                        stringBuffer.append(next);
                        stringBuffer.append("号位置");
                        stringBuffer.append("\n");
                    } else {
                        // 左移
                        stringBuffer.append(last);
                        stringBuffer.append("号位置左移到");
                        stringBuffer.append(next);
                        stringBuffer.append("号位置");
                        stringBuffer.append("\n");
                    }
                    last = next;
                }

            }
            System.out.println(stringBuffer.toString());
        }
    }

    public static void main(String[] args) {
        PaneShiftGameClass paneShiftGameTest = new PaneShiftGameClass();
        System.out.println(paneShiftGameTest.isSuccess());
        paneShiftGameTest.printMove();

    }
}


