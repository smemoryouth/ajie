package zhongfu;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * description：统计一个文档中字符出现的字数
 *
 * @author ajie
 * data 2018/12/14 13:56
 */
public class Count {
    public static void main(String[] args)  {
        String path = "test1.txt";
        countNum(path);
    }
    public static void countNum(String path){
        try {
            FileInputStream fis = new FileInputStream(path);
            BufferedInputStream bufis = new BufferedInputStream(fis);

            Map<Character, Integer> map = new HashMap<>();
            byte[] buf = new byte[1024];
            int len;
            while ((len = bufis.read(buf)) != -1) {
                for (int i = 0; i < len; i++) {
                    if ((buf[i] >= 'a' && buf[i] <= 'z') || (buf[i] >= 'A' && buf[i] <= 'Z')) {
                        Integer count = map.get((char) buf[i]);
                        map.put((char) buf[i], null == count ? 1 : count + 1);
                    }
                }
            }

            for (Map.Entry<Character, Integer> enter : map.entrySet()) {
                System.out.println("字母：" + enter.getKey() + "出现：" + enter.getValue() + "次");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
