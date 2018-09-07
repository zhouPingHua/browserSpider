package com.zph.crawler.service.impl;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.zph.crawler.bean.Chanle;
import com.zph.crawler.bean.Result;
import com.zph.crawler.service.Spider;
import com.zph.crawler.user32.Win32Util;
import com.zph.crawler.util.HtmlExtractUtil;
import com.zph.crawler.winio.handle.VirtualKeyBoard;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author zph  on 2018/9/7
 */
@Service
public class SpiderDemoImpl implements Spider{

    private static final String LOGIN_HTML = "ShenZhenGjjLogin.html";

    static {
        HtmlExtractUtil.extract(LOGIN_HTML, "static/login.html");
    }


    @Override
    public Result go(RemoteWebDriver driver, Chanle chanle) throws Exception {

        driver.get("file://" + HtmlExtractUtil.HTML_HOME + File.separator + LOGIN_HTML);
        WinDef.HWND textBox = Win32Util.findHandleByClassName(null, "ThunderRT6UserControlDC", 10, TimeUnit.SECONDS);
        User32.INSTANCE.SetForegroundWindow(textBox);
        VirtualKeyBoard.KeyPress(chanle.getPassword().split(""),50,100);
        String password = (String) driver.executeScript("return pwd.getVal();");
        return Result.ok(password);
    }
}
