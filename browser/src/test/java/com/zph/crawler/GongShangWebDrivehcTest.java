package com.zph.crawler;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.zph.crawler.user32.Win32Util;
import com.zph.crawler.util.HtmlExtractUtil;
import com.zph.crawler.util.SeleniumApp;
import com.zph.crawler.winio.handle.VirtualKeyBoard;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

/**
 * @author zph  on 2018/9/11
 */
public class GongShangWebDrivehcTest {

    private static final String LOGIN_HTML = "gongshangCaptcha.html";

    static {
        HtmlExtractUtil.extract(LOGIN_HTML, "static/gongshangCaptcha.html");
    }

    public static void main(String[] args) throws Exception {

        SeleniumApp.initial();
        String randomCode = null;
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.ignoreZoomSettings();
        internetExplorerOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        RemoteWebDriver driver = new InternetExplorerDriver(internetExplorerOptions);
        try {
            driver.get("file://" + HtmlExtractUtil.HTML_HOME + File.separator + LOGIN_HTML+"?pubkey="+"CNEWDXIGEOCDIBHUFFDYHSGRHBBTGTFFEKCMDJEC");
            WinDef.HWND explorerServer = Win32Util.findHandleByClassName(null, "ATL:Edit");
            User32.INSTANCE.SetForegroundWindow(explorerServer);
            driver.executeScript("document.getElementById('verifyCode').focus();");
            VirtualKeyBoard.KeyPress("yxir".split(""), 500, 60);
            driver.executeScript("getPassWord('"+randomCode+"');");
            String hackPassword = (String)driver.executeScript("return document.getElementById('hackStr').innerText;");

            System.out.println("加密密文："+hackPassword);
            // 获取 网页的 title
            System.out.println("The testing page title is: " + driver.getTitle());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        } finally {
            if(driver != null){
                driver.quit();
            }
            WinDef.HWND hwnd = Win32Util.findHandleByClassName(null, "IEFrame");
        }

    }
}