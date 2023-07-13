package thread.terminate;

/**
 * @author ZhangShenao
 * @date 2023/7/13 10:05 AM
 * Description 通过共享的状态变量终止线程
 */
public class TerminateThreadBySharedFlag {
    private static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (!stop) {
                System.out.println(Thread.currentThread().getName() + " is running...");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + " is terminated! ");
        }, "T1");

        t.start();

        Thread.sleep(2000L);

        //通过共享的状态变量终止线程
        stop = true;
    }
}
