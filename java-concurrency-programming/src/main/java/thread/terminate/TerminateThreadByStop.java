package thread.terminate;

/**
 * @author ZhangShenao
 * @date 2023/7/13 10:07 AM
 * Description 直接调用stop方法终止线程
 * stop方法不安全,可能导致当前线程持有的资源(如锁对象等)没有被正常释放,造成线程的执行异常和状态不一致。不推荐使用。
 */
public class TerminateThreadByStop {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ": i = " + i);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t.start();

        Thread.sleep(2000L);

        //直接调用stop方法终止线程
        t.stop();
    }
}
