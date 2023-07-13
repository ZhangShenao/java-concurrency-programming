package thread.terminate;

/**
 * @author ZhangShenao
 * @date 2023/7/13 10:53 AM
 * Description 通过响应中断标志位的方式终止线程
 * 响应InterruptedException中断异常后,线程会自动重置中断标志位
 */
public class TerminateThreadByInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": i = " + i);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    boolean interrupted = Thread.currentThread().isInterrupted();
                    //响应InterruptedException中断异常后,线程会自动重置中断标志位
                    System.out.println(Thread.currentThread().getName() + ": interrupt flag is " + interrupted);

                    //通过响应中断标志位的方式终止线程
                    System.out.println(Thread.currentThread().getName() + ": terminated by interrupt !");
                    return;
                }
            }
        }, "T1");

        t.start();

        Thread.sleep(2000L);

        //将中断标志位置位
        t.interrupt();
    }
}
