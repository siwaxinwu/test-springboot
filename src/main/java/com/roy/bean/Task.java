package com.roy.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dingyawu
 * @Description: TODO
 * @Date: Created in 21:50 2023/7/3
 */
public class Task implements  Runnable{

    List<Byte> list = new ArrayList<>();
    @Override
    public void run() {
        List<byte[]> list = new ArrayList<>();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        for (int i = 0; i < 10; i++) {
            list.add(new byte[20 * 1024 * 1024]);
        }
    }
}
