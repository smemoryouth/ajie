package io;

import java.io.File;

/**
 * description：删除一个非空文件夹，显示文件夹内容
 *
 * @author ajie
 * data 2018/7/13
 */
public class FileRemoveTest {
    public static void main(String[] args) {
        File dir  = new File("g:\\文档");
        removeDir(dir);
    }

    public static void removeDir(File dir) {
        File[] files = dir.listFiles();
        for(File file : files){
            if(file.isDirectory()){
                removeDir(file);
            }else{
                System.out.println(file+":"+file.delete());
            }
        }
        System.out.println(dir+":"+dir.delete());
    }
}
