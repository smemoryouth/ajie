package io.util;

import java.io.File;
import java.io.FilenameFilter;

/**
 * description：
 *
 * @author ajie
 * data 2018/7/26 22:35
 */
public class SuffixFilter implements FilenameFilter {

    private String suffix;

    public SuffixFilter(String suffix) {
        super();
        this.suffix = suffix;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(suffix);
    }

}
