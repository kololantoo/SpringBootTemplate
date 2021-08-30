package com.kolosensei.springboottooltemplate.template;

import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengyang
 * @version 1.0
 * @date 2021/2/26 14:21
 * @description: java多线程相关，线程是操作系统调度的最小单元
 */
public class ThreadTemplate {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws Exception{

        Thread wait = new Thread(new Wait(),"waitThread");
        wait.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notify = new Thread(new Notify(),"notifyThread");
        notify.start();
    }

    static class Wait implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread()+" flag is true. wait @"+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread()+" flag is false. running @"+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread()+" hold lock. notify @"+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    lock.notifyAll();
                    flag=false;
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (lock) {
                System.out.println(Thread.currentThread()+" hold lock again. sleep @"+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 使用JMX查看包含哪些线程
     */
    private static void printThreadInfo() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId()+","+threadInfo.getThreadName());
        }
    }

    /**
     * 打印java对象头
     */
    private static void printJavaHead() {
        Boolean b = new Boolean(true);
        System.out.println(ClassLayout.parseInstance(b).toPrintable());
    }

    /**
     * 使用管道机制
     * @throws IOException
     */
    private static void usePipe() throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        out.connect(in);
    }
}
