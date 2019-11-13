package com.zsx.test;

import org.junit.jupiter.api.Test;

public class LambdaTest {

    @Test
    void testThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
        // 上面写法等价于
        new Thread(()-> {
            System.out.println(Thread.currentThread().getName());
        }).start();
    }
}
