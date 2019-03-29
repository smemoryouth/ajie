package io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;


class FilterEndwith implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(".txt");
    }
}

/**
 * description：课堂测试
 *
 * @author ajie
 * data 2018/7/20
 */
public class ClassSearch {

    /**
     * 判断指定文件是否存在，存在则删除，不存在则创建
     *
     * @param file
     * @throws IOException
     */
    public static void isExist(File file) throws IOException {
        if (file.exists()) {
            System.out.println("file exist");
            System.out.println("file delete " + file.delete());
        } else {
            System.out.println("file creat " + file.createNewFile());
        }
    }

    /**
     * 判断一个指定的目录是否是目录
     *
     * @param file
     */
    public static void isDir(File file) {
        System.out.println("file is directory ? " + file.isDirectory());
    }

    /**
     * 列出指定目录下的全部文件
     *
     * @param file
     */
    public static void show(File file) {
        File[] list = file.listFiles();
        assert list != null;
        for (File aList : list) {
            if (!aList.isFile()) {
                continue;
            }
            System.out.println(aList.getName());
        }
    }

    /**
     * 列出指定目录下的全部内容
     *
     * @param file
     */
    public static void showAll(File file) {
        String[] list = file.list();
        assert list != null;
        for (String i : list) {
            System.out.println(i);
        }
    }

    /**
     * 列出指定目录下所有文件（目录内的内容递归打印）
     *
     * @param file
     */
    public static void list(File file) {
        File[] files = file.listFiles();
        for (int x = 0; x < files.length; x++) {
            if (files[x].isDirectory()) {
                list(files[x]);
            } else {
                System.out.println(files[x].getName());
            }
        }
    }

    /**
     * 树形打印指定目录下所有文件
     *
     * @param file
     * @param level
     */
    public static void listAll(File file, int level) {
        System.out.println(getSpace(level) + file.getName());
        level++;
        File[] files = file.listFiles();
        if (files != null) {
            for (int x = 0; x < files.length; x++) {
                if (files[x].isDirectory()) {
                    listAll(files[x], level);
                } else {
                    System.out.println(getSpace(level) + files[x].getName());
                }
            }
        }
    }

    /**
     * 层数
     *
     * @param level
     * @return
     */
    private static String getSpace(int level) {
        StringBuilder sb = new StringBuilder();
        sb.append("|--");
        for (int x = 0; x < level; x++) {
            sb.insert(0, "|--  ");
        }
        return sb.toString();
    }

    /**
     * 指定目录下的包含指定内容的文件
     *
     * @param file
     */
    public static void listAdmit(File file) {
        File[] files = file.listFiles(new FilterEndwith());
        for (File file1 : files) {
            if (file1.isDirectory()) {
                listAdmit(file1);
            } else {
                System.out.println(file1.getName());
            }
        }
    }

    /**
     * 指定目录下的包含指定内容的文件，老师程序
     *
     * @param fileName
     * @param filter
     */
    public static void fileFilter(String fileName, String filter) {
        File file = new File(fileName);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null) {
                int length = files.length;
                for (int j = 0; j < length; j++) {
                    if (!files[j].isHidden()) {
                        if (files[j].isFile()) {
                            //files[j].getName().contains(filter)
                            if (files[j].getName().endsWith(filter)) {
                                System.out.println(files[j].getName());
                            }
                        } else {
                            fileFilter(files[j].getAbsolutePath(), filter);
                        }
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        File file = new File("H:");
//        isExist(file);
//        showAll(file);
//        System.out.println("===============");
//        show(file);
//        listAll(file, 0);
//        list(file);
//        listAdmit(file);
        fileFilter("g:\\demo", ".java");
    }
}
