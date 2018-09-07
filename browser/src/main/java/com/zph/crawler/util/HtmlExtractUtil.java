package com.zph.crawler.util;

import java.io.File;

/**
 *
 * @author wangchao
 * @date 2018/7/3
 */
public class HtmlExtractUtil {

    public static final String USER_HOME = System.getProperty("user.home");

    public static final String HTML_HOME = USER_HOME + File.separator + "html";

    private HtmlExtractUtil() {
    }

    public static void extract(String destFileName, String srcFileName) {
        File htmlDir = new File(HTML_HOME);
        if (!htmlDir.exists() && !htmlDir.mkdir()) {
            throw new RuntimeException(htmlDir + " make dir false");
        }
        LibraryLoader.extract(HTML_HOME + File.separator + destFileName, HtmlExtractUtil.class, srcFileName);
    }
}
