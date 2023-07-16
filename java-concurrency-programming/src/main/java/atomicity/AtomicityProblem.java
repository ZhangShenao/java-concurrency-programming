package atomicity;

import java.util.concurrent.CountDownLatch;

/**
 * @author ZhangShenao
 * @date 2023/7/13 2:28 PM
 * Description 并发编程的问题根源一：原子性问题
 */
public class AtomicityProblem {
    private static int count = 0;   //共享变量无法保证原子性

    public static void main(String[] args) throws InterruptedException {
        int threadNum = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        count++;
                        Thread.sleep(5L);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();

        //结果大概率小于10000
        System.out.println("The Result is : " + count);
    }
}
