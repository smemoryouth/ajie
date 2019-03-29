package io;

import java.io.*;
import java.util.Properties;
import java.util.Set;

/**
 * description：Properties集合综合测试
 *
 * @author ajie
 * data 2018/7/13
 */
public class PropertiesTest {
    public static void main(String[] args) throws IOException {
//        propertiesTest();
//        storeTest();
//        loadTeat();
//        myLoadTest();
//        propertiesStreamTest();
        test();
    }

    /**
     * 对已有的配置文件中的信息进行修改
     * 读取这个文件。
     * 并将这个文件中的键值数据存储到集合中。
     * 在通过集合对数据进行修改。
     * 在通过流将修改后的数据存储到文件中。
     */
    public static void test() throws IOException {

        // 读取这个文件。
        File file = new File("infodfd0.txt");
        if (!file.exists()) {
            throw new RuntimeException("读取失败");
        }
        FileReader fr = new FileReader(file);

        // 创建集合存储配置信息。
        Properties prop = new Properties();

        // 将流中信息存储到集合中。
        prop.load(fr);
        // 修改信息
        prop.setProperty("wangwu", "16");

        FileWriter fw = new FileWriter(file);
        prop.store(fw, "new info");
		prop.list(System.out);
        fw.close();
        fr.close();
    }


    /**
     * 模拟load方法
     */
    public static void myLoadTest() throws IOException {

        Properties prop = new Properties();

        BufferedReader bufr = new BufferedReader(new FileReader("info.txt"));

        String line;

        while ((line = bufr.readLine()) != null) {
            if (line.startsWith("#")) {
                continue;
            }
            String[] arr = line.split("=");
            prop.setProperty(arr[0], arr[1]);
        }
        prop.list(System.out);
        bufr.close();
    }

    public static void loadTeat() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("info.txt");
        prop.load(fis);
        prop.list(System.out);
    }

    public static void storeTest() throws IOException {
        Properties prop = new Properties();

        //存储元素。
        prop.setProperty("zhangsan", "30");
        prop.setProperty("lisi", "31");
        prop.setProperty("wangwu", "36");
        prop.setProperty("zhaoliu", "20");

        //想要将这些集合中的字符串键值信息持久化存储到文件中需要关联输出流
        FileOutputStream fos = new FileOutputStream("info.txt");
        //将集合中数据存储到文件中，使用store方法。
        prop.store(fos, "info");
        fos.close();

    }

    /**
     * 演示Properties集合和流对象相结合的功能。
     */
    public static void propertiesStreamTest() {
        Properties prop = System.getProperties();
        prop.list(System.out);
    }

    /**
     * Properties集合的存和取。
     */
    public static void propertiesTest() throws IOException {
        //创建一个Properties集合。
        Properties prop = new Properties();

        //存储元素。
        prop.setProperty("zhangsan", "30");
        prop.setProperty("lisi", "31");
        prop.setProperty("wangwu", "36");
        prop.setProperty("zhaoliu", "20");

        //修改元素。
//        prop.setProperty("wangwu", "26");

        //取出所有元素。
        Set<String> names = prop.stringPropertyNames();

        for (String name : names) {
            String value = prop.getProperty(name);
            System.out.println(name + ":" + value);
        }
        //想要将这些集合中的字符串键值信息持久化存储到文件中需要关联输出流
        FileOutputStream fos = new FileOutputStream("infodfd0.txt");
        //将集合中数据存储到文件中，使用store方法。
        prop.store(fos, "info");
        fos.close();
    }
}
