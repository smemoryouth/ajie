package zhongfu;


import java.util.Arrays;

class OperateMatrix {

    private int[][] matrix1;
    private int[][] matrix2;
    private int[][] result;
    public static int line = 0;

    OperateMatrix(int[][] m1, int[][] m2) {

        this.matrix1 = m1;
        this.matrix2 = m2;
        result = new int[matrix1.length][matrix2[0].length];
    }


    int[][] getResult() {

        try {
            while (OperateMatrix.line < matrix1.length) {
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.result;

    }

    void operate() {

        OperateMatrix.line += 1;

        for (int i = 0; i < matrix1[0].length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix2.length; j++) {
                sum += matrix1[OperateMatrix.line - 1][j] * matrix2[j][i];
            }
            result[OperateMatrix.line - 1][i] = sum;
        }
    }
}

class ThreadOperate extends Thread {

    private OperateMatrix om;

    ThreadOperate(OperateMatrix om, String name) {
        super(name);
        this.om = om;
    }

    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
         * 每次调用只计算一行结果
         *
         * */
        this.om.operate();
    }

}

/**
 * description：矩阵乘法
 *
 * @author 阿劼
 */
public class OperateTest {

    public static void main(String args[]) {

        //定义矩阵
        int[][] m1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        int[][] m2 = {{1, 2, 1}, {1, 1, 2}, {2, 1, 1}};

        OperateMatrix om = new OperateMatrix(m1, m2);

        //根据第一个矩阵的行数，启动对应数量的线程
        for (int i = 0; i < m1.length; i++) {
            new ThreadOperate(om, "计算第一个矩阵的第" + (i + 1) + "行 * 第二个矩阵的所有列").start();
        }
        System.out.println(Arrays.deepToString(om.getResult()));
    }
}
