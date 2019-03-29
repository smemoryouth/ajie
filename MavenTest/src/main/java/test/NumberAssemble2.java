package test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * description：
 *
 * @author ajie
 * data 2018/11/26 16:46
 */
public class NumberAssemble2 {
    TreeSet<String> hashSet = new TreeSet <String>();

    public Set<String> getSet() {
        return this.hashSet;
    }


    public void moveIndex(int[] num, int beginIdx, int endIdx) {
        if (beginIdx == endIdx) {
            //从指定位置一直交换到尾部，判断是否满足要求
            //要求‘4’不能在第三位
            if (num[2] == 4) {
                return;
            }
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < num.length; i++) {
                buffer.append(num[i]);
            }

            String string = buffer.toString();
            //‘3’和‘5’不能相连
            if (string.contains("35") || string.contains("53")) {
                return;
            }
            //满足要求数据，通过TreeSet去重并排序处理
            hashSet.add(string);
        } else {
            for (int i = beginIdx; i <= endIdx; i++) {
                swap(num,beginIdx, i);
                //递归调用，位置交换处理
                moveIndex(num, beginIdx+1, endIdx);
                swap(num, beginIdx, i);
            }
        }
    }

    private void swap(int[] num ,int xIdx, int yIdx) {
        if (xIdx == yIdx) {
            return;
        }
        int tmp = num[xIdx];
        num[xIdx] = num[yIdx];
        num[yIdx] = tmp;
    }


    public static void main(String[] args) {

        //原始数组
        int num[] = new int[]{1, 2, 2, 3, 4, 5};

        NumberAssemble2 printSort = new NumberAssemble2();

        printSort.moveIndex(num,0, num.length -1);

        Set<String> set = printSort.getSet();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
        System.out.println("数量："+set.size());
    }
}
