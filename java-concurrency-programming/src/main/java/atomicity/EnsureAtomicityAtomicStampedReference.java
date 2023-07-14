package atomicity;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author ZhangShenao
 * @date 2023/7/14 10:12 AM
 * Description 通过使用StampedReference保证原子性
 * StampedReference中引入了版本号的概念,同时比较变量值与版本号,解决了CAS中的ABA问题
 */
public class EnsureAtomicityAtomicStampedReference {
    private static final AtomicStampedReference<String> v = new AtomicStampedReference<>("A", 1);

    public static void main(String[] args) throws InterruptedException {
        //线程T1将变量值修改为B,并将版本号+1
        Thread t1 = new Thread(() -> {
            boolean result = v.compareAndSet("A", "B", 1, 2);
            System.out.println("线程T1将变量值修改为B,并将版本号+1,操作结果: " + result);
        }, "T1");

        //线程T1要进行与T1相同的操作
        Thread t2 = new Thread(() -> {
            boolean result = v.compareAndSet("A", "B", 1, 2);
            System.out.println("线程T2将变量值修改为B,并将版本号+1,操作结果: " + result);
        }, "T2");

        //线程T3将变量值修改回A,并将版本号+1
        Thread t3 = new Thread(() -> {
            String reference = v.getReference();
            int stamp = v.getStamp();
            System.out.println("线程T3操作时,当前变量值为: " + reference + ", 版本号为: " + stamp);
            boolean result = v.compareAndSet(reference, "A", stamp, stamp + 1);
            System.out.println("线程T3将变量值修改回A,并将版本号+1,操作结果: " + result);
        }, "T3");

        t1.start();
        t1.join();

        t3.start();
        t3.join();

        t2.start();
        t2.join();

    }
}
