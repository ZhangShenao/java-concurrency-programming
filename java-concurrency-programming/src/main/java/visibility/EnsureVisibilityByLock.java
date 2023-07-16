package visibility;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ZhangShenao
 * @date 2023/7/16 3:33 PM
 * Description 通过Lock锁保证可见性
 */
public class EnsureVisibilityByLock {
    private static boolean flag = true;

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (flag) {
                lock.lock();

                try {
                    //T1 is Running...
                } finally {
                    lock.unlock();
                }
            }

            System.out.println("Thread T1: Stopped by flag");
        }, "T1").start();

        Thread.sleep(2000L);

        new Thread(() -> {
            //根据JMM模型,此处main线程对flag变量的修改,对T1不可见,因此T1无法终止
            flag = false;
            System.out.println("Thread T2: Set flag to false");
        }, "T2").start();

    }
}
