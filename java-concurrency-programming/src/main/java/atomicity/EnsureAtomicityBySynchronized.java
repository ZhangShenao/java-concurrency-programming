package atomicity;

import java.util.concurrent.CountDownLatch;

/**
 * @author ZhangShenao
 * @date 2023/7/14 9:18 AM
 * Description 使用synchronized保证原子性
 */
public class EnsureAtomicityBySynchronized {
    private static int count = 0;

    private static final Object lock = new Object();  //synchronized锁对象

    private static void incr(CountDownLatch countDownLatch) {
        try {
            for (int j = 0; j < 1000; j++) {
                synchronized (lock) {   //使用synchronized对临界区加锁
                    count++;
                }
                Thread.sleep(5L);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int threadNum = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> incr(countDownLatch)).start();
        }

        countDownLatch.await();

        //结果为10000
        System.out.println("The Result is : " + count);
    }
}
