package com.zph.crawler.api;

import com.sun.jna.platform.win32.WinDef;
import com.zph.crawler.bean.Chanle;
import com.zph.crawler.bean.Result;
import com.zph.crawler.service.Spider;
import com.zph.crawler.user32.User32Ext;
import com.zph.crawler.user32.Win32MessageConstants;
import com.zph.crawler.user32.Win32Util;
import com.zph.crawler.util.SeleniumApp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zph  on 2018/9/7
 */
@RestController
public class DemoApi {

    private Lock lock = new ReentrantLock();

    public DemoApi(){
        SeleniumApp.initial();
    }

    @Autowired
    private Spider spider;

    @RequestMapping(value = "/ie/go")
    public Result go(Chanle chanle) {

        RemoteWebDriver driver = null;
        try {
            lock.lock();
            driver = builderWebDriver();
            return spider.go(driver,chanle);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(-1, e.getMessage());
        }finally {
            if (null != driver) {
                driver.quit();
            }
            WinDef.HWND hwnd = Win32Util.findHandleByClassName(null, "IEFrame");
            if (null != hwnd) {
                User32Ext.USER32EXT.SendMessage(hwnd, Win32MessageConstants.WM_CLOSE, (byte)'0', 0);
            }
            lock.unlock();
        }
    }


    /**
     * 构建WebDriver
     * @return
     */
    private RemoteWebDriver builderWebDriver() {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.ignoreZoomSettings();
        internetExplorerOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        RemoteWebDriver driver = new InternetExplorerDriver(internetExplorerOptions);
        return driver;
    }

}
