package zhongfu;

import java.io.File;

/**
 * description：文件移动，利用renameTo方法
 *
 * @author ajie
 * data2018/12/10 14:15
 */
public class FileMove {
    public static void main(String[] args)  {
        String path = "D:\\HelloWorld.txt";
        String desk = "D:\\IOtest";
        move(path, desk);
    }

    private static void move(String path,String desk) {
        File file = new File(path);
        boolean flag = false;
        try {
            if (!file.exists()) {
                flag =  file.createNewFile();
            }
            if (!file.isDirectory() && flag){
                boolean flag1 = new File(desk).mkdir();
                if (flag1) {
                    File file1 = new File(desk + "\\Hello.txt");
                    System.out.println(file.renameTo(file1) ? "操作成功" : "操作失败");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
