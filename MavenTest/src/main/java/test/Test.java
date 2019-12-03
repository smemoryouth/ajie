package test;

/**
 * description：
 *
 * @author 阿劼
 * data 2019/10/30 8:44
 */
public class Test {

    public static int testIs() {

        return 1;
    }

    public static int a(){
        return testIs();
    }

    public static void main(String[] args) {
        String[] arr = {"小小明明", "张三", "李四", "王麻子哦"};
        getException(arr);
    }

    private static void getException(String[] arr) {
        for (String name : arr) {
            int length = name.length();
            if (length > 3) {
                System.out.println(new Exception() + "-> name:[" + name + "]长度不符");
                continue;
            }
            System.out.println(name);
        }
    }
}