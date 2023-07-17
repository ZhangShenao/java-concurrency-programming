package sync;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author ZhangShenao
 * @date 2023/7/17 10:06 AM Description synchronized锁升级过程
 */
public class SynchronizedUpgradeProgress {
    
    public static void main(String[] args) throws InterruptedException {
        //等到偏向锁延迟结束
        Thread.sleep(6000L);
        
        Object lock = new Object();
        
        System.out.println("初识状态下的锁标识: ");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());  //无锁状态——匿名锁偏向
        
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("T1线程中的锁标识: ");
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());  //偏向锁
                //                try {
                //                    Thread.sleep(5000L);
                //                } catch (InterruptedException e) {
                //                    throw new RuntimeException(e);
                //                }
            }
        }, "T1").start();
        
        Thread.sleep(1000L);
        
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("T2线程中的锁标识: ");
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());  //轻量级锁
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "T2").start();
    }
}
