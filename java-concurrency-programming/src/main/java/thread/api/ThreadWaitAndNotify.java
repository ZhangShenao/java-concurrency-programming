package thread.api;

/**
 * @author ZhangShenao
 * @date 2023/7/13 9:49 AM
 * Description 线程的等待与唤醒
 */
public class ThreadWaitAndNotify {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Runnable task = () -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ": i = " + i);
                        Thread.sleep(1000L);
                        if (5 == i) {
                            //线程等待,从阻塞队列中出队,进入等待队列
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        Thread t1 = new Thread(task, "T1");
        Thread t2 = new Thread(task, "T2");

        t1.start();
        t2.start();

        Thread.sleep(12000L);

        //唤醒等待队列中的线程
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
