package eclipse;

import java.util.ArrayList;
import java.util.Random;

/**
 * description：方格移动游戏
 *
 * @author ajie
 * data 2018/6/16
 */
public class PaneShiftGameMyself {
    public static int PANE_NUMBER = 10;

    /**
     * 递归实现，出现循环的情况没有解决
     * @param arr 数组
     * @param start 开始的索引
     * @return boolean
     */
    public static boolean gameRecurrence(ArrayList<Integer> arr, int start){
        int end = arr.get(start) + start;
        int end1 = arr.get(start) - start;
        int end2 = start - arr.get(start);
        if (start == arr.size() - 1){
            return true;
            /*
             * 右移
             */
        }else if (end < arr.size() && end != start){
            System.out.println("从" + start + "号位置的" + arr.get(start) + "移动到" + end + "号位置的" + arr.get(end));
            return gameRecurrence(arr, end);
            /*
             * 左移，避免了实际右移
             */
        }else if(end >= arr.size() && end1 > 0 && end1 < start){
            System.out.println("从" + start + "号位置的" + arr.get(start) + "移动到" + end1 + "号位置的" + arr.get(end1));
            return gameRecurrence(arr, end1);
            /*
             * 左移，避免了实际右移
             */
        }else if(end >= arr.size() && end2 > 0 && end2 < start) {
            System.out.println("从" + start + "号位置的" + arr.get(start) + "移动到" + end2 + "号位置的" + arr.get(end2));
            return gameRecurrence(arr, end2);
        }else {
            return false;
        }
    }

    /**
     * 一般的遍历循环
     * @param arr 数组
     * @param start 开始索引
     * @return boolean
     */
    public static boolean gameArray(ArrayList<Integer> arr, int start) {
        int end = start;
        // 保存每次移动过的索引，可以发现是否循环
        ArrayList<Integer> tmp = new ArrayList<>();
        for (; start < arr.size(); ) {
                /*
                 * 循环的结束条件
                 */
                if ((arr.get(start) + start == start)) {
                    System.out.println("循环，终止运行！");
                    break;
                }
            /*
             * 右移
             */
            if (arr.get(start) + start < arr.size()) {
                end = arr.get(start) + start;
                tmp.add(start);

                System.out.println("从" + start + "号位置的" + arr.get(start) + "移动到" + end + "号位置的" + arr.get(end));
                start = end;
                /*
                 * 满足条件
                 */
                if (end == arr.size() - 1) {
                    break;
                }
                /*
                 * 左移
                 */
            } else {
                /*
                 * 左移回到起始位置，终止运行
                 */
                if (arr.get(start) == start) {
                    System.out.println("循环，终止运行!");
                    break;
                } else if (arr.get(start) > start) {
                    end = arr.get(start) - start;
                } else {
                    end = start - arr.get(start);
                }
                tmp.add(start);

                System.out.println("从" + start + "号位置的" + arr.get(start) + "移动到" + end + "号位置的" + arr.get(end));
                /*
                 * 实际右移，终止运行
                 */
                if (end > start) {
                    System.out.println("实际右移，终止循环！");
                    break;
                }
                start = end;
            }
            /*
             * 循环，终止运行
             */
            for (int j = 0; j < tmp.size(); j++) {
                if (tmp.get(j) == start) {
                    arr.set(start, 0);
                    break;
                }
            }
        }
        if (end == arr.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        for (int start = 0; start < PANE_NUMBER; start++) {
            arr.add(random.nextInt(9) + 1);
        }
        System.out.println(arr);
        int start = 0;
        System.out.println(gameArray(arr, start));
        System.out.println(gameRecurrence(arr, start));
    }
}
