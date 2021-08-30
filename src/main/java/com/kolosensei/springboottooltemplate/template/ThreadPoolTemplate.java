package com.kolosensei.springboottooltemplate.template;

import com.kolosensei.springboottooltemplate.util.DefaultThreadFactory;

import java.util.concurrent.*;

/**
 * @author: zhengyang
 * @version: 1.0
 * @date: 2021/5/20
 * @description:
 */
public class ThreadPoolTemplate {

    public static void main(String[] args) {

//        ExecutorService parallelPool = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new DefaultThreadFactory());
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService parallelPool = Executors.newFixedThreadPool(10);

        for (int i = 1; i < 11; i++) {
            parallelPool.submit(new MyThread(i,countDownLatch));
        }

        try {
            countDownLatch.await(1000, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            parallelPool.shutdown();
        }
        System.out.println(countDownLatch.getCount());
        System.out.println("主线程执行完毕");
    }

    static class MyThread implements Runnable {

        int i;
        CountDownLatch countDownLatch;

        public MyThread(int i, CountDownLatch countDownLatch) {
            this.i = i;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            } finally {
                countDownLatch.countDown();
            }
            System.out.println("线程" + i + "执行完毕");
        }
    }
}
