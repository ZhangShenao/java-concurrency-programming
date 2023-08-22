package thread.api;


/**
 * @author ZhangShenao
 * @date 2023/7/13 9:22 AM
 * Description 线程抢占
 */
public class ThreadJoin {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": i = " + i);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "T1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": i = " + i);
                try {
                    if (5 == i) {
                        //线程抢占,阻塞当前线程2s,让线程T1先执行
                        t1.join(2000L);
                    }
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "T2");

        t1.start();

        t2.start();
    }
}
