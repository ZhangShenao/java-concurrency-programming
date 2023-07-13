package thread.api;

/**
 * @author ZhangShenao
 * @date 2023/7/13 9:32 AM
 * Description 守护线程
 */
public class DaemonThread {
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

        //设置为守护线程
        //所有线程创建时,默认均为非守护线程
        //JVM会在所有非守护线程都终止时,退出JVM进程
        t1.setDaemon(true);
        t1.start();
    }
}
