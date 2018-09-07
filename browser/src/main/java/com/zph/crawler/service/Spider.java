package com.zph.crawler.service;

import com.zph.crawler.bean.Chanle;
import com.zph.crawler.bean.Result;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author zph  on 2018/9/7
 */
public interface Spider{

    /**
     * 开始执行逻辑
     *
     * @param driver   driver
     * @param chanle   chanle
     * @return
     * @throws Exception
     */
    Result go(RemoteWebDriver driver, Chanle chanle) throws Exception;
}
