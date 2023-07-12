package thread.api;

/**
 * @author ZhangShenao
 * @date 2023/7/12 2:08 PM
 * Description 线程优先级
 */
public class ThreadPriority {
    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ": i = " + i);
            }
        };

        Thread t1 = new Thread(task);
        t1.setName("T1");
        Thread t2 = new Thread(task);
        t2.setName("T2");

        //设置线程优先级
        //线程优先级本质上是CPU调度的优先级
        //设置优先级理论上具有一定的作用,但是最终的执行情况还是取决于CPU的调度
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        //理论上的情况是:T2先执行完成,再执行T1
        t1.start();
        t2.start();
    }
}
