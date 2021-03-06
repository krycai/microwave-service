package com.allen.algorithm.thread;

/**
 * 等待通知版
 * Function:两个线程交替执行打印 1~100 关键：对象等待、通知
 *
 * Created by xuguocai on 2021/1/6 15:06
 */
public class TwoThreadWaitNotify {
    private int start = 1;

    private boolean flag = false;

    public static void main(String[] args) {
        TwoThreadWaitNotify twoThread = new TwoThreadWaitNotify();
        // 偶数线程
        Thread t1 = new Thread(new OuNum(twoThread));
        t1.setName("t1");

        // 奇数线程
        Thread t2 = new Thread(new JiNum(twoThread));
        t2.setName("t2");

        t1.start();
        t2.start();
    }

    /**
     * 偶数线程
     */
    public static class OuNum implements Runnable {
        private TwoThreadWaitNotify number;

        public OuNum(TwoThreadWaitNotify number) {
            this.number = number;
        }

        @Override
        public void run() {

            while (number.start <= 10) {
                synchronized (TwoThreadWaitNotify.class) { // 对象锁
                    System.out.println("偶数线程抢到锁了");
                    if (number.flag) {
                        System.out.println(Thread.currentThread().getName() + "+-+偶数" + number.start);
                        number.start++;

                        number.flag = false;
                        TwoThreadWaitNotify.class.notify();// 通知
                    }else {
                        try {
                            TwoThreadWaitNotify.class.wait(); // 等待
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }


    /**
     * 奇数线程
     */
    public static class JiNum implements Runnable {
        private TwoThreadWaitNotify number;

        public JiNum(TwoThreadWaitNotify number) {
            this.number = number;
        }

        @Override
        public void run() {
            while (number.start <= 10) {
                synchronized (TwoThreadWaitNotify.class) { // 获取对象锁
                    System.out.println("奇数线程抢到锁了");
                    if (!number.flag) {
                        System.out.println(Thread.currentThread().getName() + "+-+奇数" + number.start);
                        number.start++;

                        number.flag = true;
                        TwoThreadWaitNotify.class.notify(); // 通知
                    }else {
                        try {
                            TwoThreadWaitNotify.class.wait(); // 等待
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
