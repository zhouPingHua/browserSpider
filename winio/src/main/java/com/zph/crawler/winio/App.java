package com.zph.crawler.winio;

import com.zph.crawler.winio.handle.VirtualKeyBoard;

/**
 * @author zph  on 2018/9/7
 */
public class App {

    public static void main(String[] args) throws Exception {

//        VirtualKeyBoard.KeyPress("HHH125478".split(""),500,100);

        String s = "HelloWorldEverybody";
//        Runtime.getRuntime().exec("notepad");
        VirtualKeyBoard.KeyPress(s.split(""),0,50);
    }
}
