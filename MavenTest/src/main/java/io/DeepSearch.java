package io;

import io.util.SuffixFilter;

import java.io.File;

/**
 * description：深度遍历
 *
 * @author ajie
 * data 2018/7/13
 */
public class DeepSearch {
    public static void main(String[] args) {
        File dir = new File("g:\\demo");
        String filter = ".java";
//        listAll(dir, 0);
        listAllFiles(dir, filter);
//        mdf(dir, ".java");
    }

    /**
     * www.github.com
     */
    private static void listAllFiles(File dir, String filter) {
        if (dir == null || !dir.exists()) {
            return;
        }
        if (dir.isFile()) {
            System.out.println(dir.getName());
            return;
        }
        for (File file : dir.listFiles(new SuffixFilter(filter))) {
            listAllFiles(file, filter);
        }
    }

    private static void listAll(File dir, int level) {
        System.out.println(getSpace(level) + dir.getName());
        //获取指定目录下当前的所有文件夹或者文件对象
        level++;
        File[] files = dir.listFiles();
        for (int x = 0; x < files.length; x++) {
            if (files[x].isDirectory()) {
                listAll(files[x], level);
            } else {
                System.out.println(getSpace(level) + files[x].getName());
            }
        }
    }

    private static String getSpace(int level) {
        StringBuilder sb = new StringBuilder();
        sb.append("|--");
        for (int x = 0; x < level; x++) {
            sb.insert(0, "|  ");
        }
        return sb.toString();
    }
    public static void mdf(File file, String filter){
        String[] s = file.list(new SuffixFilter(filter));
        assert s != null;
        for (String value : s) {
            System.out.println(value);
        }
    }
}

