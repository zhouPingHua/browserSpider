package com.zph.crawler.util;



import java.io.File;

/**
 *
 * @author Administrator
 * @date 2018/6/13 0013
 */
public class SeleniumApp {

    private static final String IE_DRIVER = "IEDriverServer.exe";

    public synchronized static boolean initial() {
        String home = System.getProperty("user.home");
        String path = home + File.separator + IE_DRIVER;
        LibraryLoader.extract(path, SeleniumApp.class, IE_DRIVER);
        System.setProperty("webdriver.ie.driver", path);
        return true;
    }

    public static void main(String[] args) {
        SeleniumApp.initial();
    }
}
