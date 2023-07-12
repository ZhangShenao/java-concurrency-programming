package thread.api;

/**
 * @author ZhangShenao
 * @date 2023/7/12 2:15 PM
 * Description 线程让步
 */
public class ThreadYield {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ": i = " + i);

                //线程让步
                //是一个hint,提示当前线程期望交出CPU执行权,让CPU重新调度
                //实际的执行情况取决于CPU的调度情况,可能让步后仍然调度到当前线程
                if (50 == i) {
                    Thread.yield();
                }
            }
        });
        t1.setName("T1");
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ": i = " + i);
            }
        });
        t2.setName("T2");
        t2.start();

        Thread.sleep(1000L);

        //预期情况:T1先执行,直到i的值到达50左右时,切换到T2执行
    }
}
