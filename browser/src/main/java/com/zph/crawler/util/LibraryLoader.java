package com.zph.crawler.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author wangchao
 * @date 2018/6/12 0012
 */
public class LibraryLoader {

    public static boolean extract(final String fileName, final Class clazz, final String name) {
        File file = new File(fileName);
        try(InputStream inputStream = clazz.getResourceAsStream("/" + name);
            FileOutputStream outputStream = new FileOutputStream(fileName)) {
            if (file.exists()) {
                FileUtils.deleteQuietly(file);
            }
            IOUtils.copy(inputStream, outputStream);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    private static String getOsName() {
        return System.getProperty("os.name") + System.getProperty("java.specification.vendor");
    }

    public static boolean isWindows() {
        return getOsName().startsWith("Windows");
    }

    public static boolean isMac() {
        return getOsName().startsWith("Mac");
    }

    public static boolean isLinux() {
        return getOsName().startsWith("Linux");
    }
}
