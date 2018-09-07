package com.zph.crawler;

import com.zph.crawler.util.HtmlExtractUtil;
import com.zph.crawler.util.SeleniumApp;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author zph  on 2018/9/7
 */
public class WebDriveTest {

//    private static final String LOGIN_HTML = "ShenZhenGjjLogin.html";
//
//    static {
//        HtmlExtractUtil.extract(LOGIN_HTML, "static/login.html");
//    }

    public static void main(String[] args) throws Exception {

        SeleniumApp.initial();
//        System.setProperty("webdriver.chrome.driver", "D://tanzhenTest//chromedriver_win32//chromedriver.exe");
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.ignoreZoomSettings();
        internetExplorerOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        RemoteWebDriver driver = new InternetExplorerDriver(internetExplorerOptions);
        driver.get("https://www.baidu.com");
        // 获取 网页的 title
        System.out.println("The testing page title is: " + driver.getTitle());
        driver.quit();
    }
}
