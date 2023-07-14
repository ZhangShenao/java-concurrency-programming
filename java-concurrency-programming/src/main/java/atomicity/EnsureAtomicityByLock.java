package atomicity;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ZhangShenao
 * @date 2023/7/14 10:30 AM
 * Description 使用Lock锁保证原子性
 */
public class EnsureAtomicityByLock {
    private static int count = 0;

    private static final Lock lock = new ReentrantLock();

    private static void incr(CountDownLatch countDownLatch) {
        for (int j = 0; j < 1000; j++) {
            try {
                //通过加锁来保护临界资源
                lock.lock();
                count++;
                Thread.sleep(1L);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                //释放锁
                lock.unlock();
            }
        }

        countDownLatch.countDown();
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
