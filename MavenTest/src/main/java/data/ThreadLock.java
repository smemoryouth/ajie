package data;

/**
 * description：
 *
 * @author 阿劼
 * data 2019/3/1 11:22
 */
public class ThreadLock {

        public static void main(String[] args) {
            int s;
            for (int i = 1; i <= 10; i++) {
                s = 0;
                for (int j = 1; j < i; j++) {
                    if (i % j == 0){

                        s = s + j;
                    }
                }
                if (s == i){
                    System.out.print(i + " ");

                }
            }
            System.out.println();
        }

}

//class E extends ThreadLock{
//
//    public E(int a) {
//        super(a);
//    }
//
//    @Override
//    public int fun(String b, int a){
//        return 2;
//    }
//
//    public static void fun3(){}
//    public static void main(String[] args){
//        System.out.println((9 | 10));
//        System.out.println((9 ^ 9));
//        System.out.println((12 % 10));
//    }
//}
//
//interface B{
//    void fun();
//
//}
//interface C{
//    void fun1();
//}
//
//interface D extends B,C{
//
//}