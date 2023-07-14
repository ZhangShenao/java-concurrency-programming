package atomicity;

/**
 * @author ZhangShenao
 * @date 2023/7/14 3:15 PM
 * Description 使用ThreadLocal保证原子性
 */
public class EnsureAtomicityByThreadLocal {
    private static ThreadLocal<String> t = ThreadLocal.withInitial(() -> "main");

    public static void main(String[] args) {
        System.out.println("ThreadLocal in main is :" + t.get());

        //每个线程独立操作自己的ThreadLocal对象,互不影响
        new Thread(() -> {
            t.set("t1");
            System.out.println("ThreadLocal in T1 is :" + t.get());
        }, "T1").start();

        new Thread(() -> {
            t.set("t2");
            System.out.println("ThreadLocal in T2 is :" + t.get());
        }, "T2").start();
    }
}
