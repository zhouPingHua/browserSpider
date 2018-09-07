package com.zph.crawler;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.zph.crawler.user32.User32Ext;
import com.zph.crawler.user32.Win32MessageConstants;
import com.zph.crawler.user32.Win32Util;
import com.zph.crawler.util.HtmlExtractUtil;
import com.zph.crawler.util.SeleniumApp;
import com.zph.crawler.winio.handle.VirtualKeyBoard;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author zph  on 2018/9/7
 */
public class ShenZhengWebDriveTest {

    private static final String LOGIN_HTML = "ShenZhenGjjLogin.html";

    static {
        HtmlExtractUtil.extract(LOGIN_HTML, "static/login.html");
    }

    public static void main(String[] args) throws Exception {

        SeleniumApp.initial();
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.ignoreZoomSettings();
        internetExplorerOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        RemoteWebDriver driver = new InternetExplorerDriver(internetExplorerOptions);
        try {
            driver.get("file://" + HtmlExtractUtil.HTML_HOME + File.separator + LOGIN_HTML);
            WinDef.HWND textBox = Win32Util.findHandleByClassName(null, "ThunderRT6UserControlDC", 10, TimeUnit.SECONDS);
            User32.INSTANCE.SetForegroundWindow(textBox);
            VirtualKeyBoard.KeyPress("HHH125478".split(""), 500, 100);
            String password = (String) driver.executeScript("return pwd.getVal();");
            System.out.println(password);
            // 获取 网页的 title
            System.out.println("The testing page title is: " + driver.getTitle());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        } finally {
            driver.quit();
            WinDef.HWND hwnd = Win32Util.findHandleByClassName(null, "IEFrame");
            if (null != hwnd) {
                User32Ext.USER32EXT.SendMessage(hwnd, Win32MessageConstants.WM_CLOSE, (byte)'0', 0);
            }
        }

    }
}
