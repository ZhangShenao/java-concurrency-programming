package atomicity;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZhangShenao
 * @date 2023/7/14 9:54 AM
 * Description 通过CAS机制保证原子性
 */
public class EnsureAtomicityByCAS {
    //使用Atomic变量保证原子性
    private static final AtomicInteger count = new AtomicInteger(0);

    private static void incr(CountDownLatch countDownLatch) {
        try {
            for (int j = 0; j < 1000; j++) {
                count.incrementAndGet();
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
        System.out.println("The Result is : " + count.get());
    }
}
