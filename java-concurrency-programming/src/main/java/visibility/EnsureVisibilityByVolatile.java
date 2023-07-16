package visibility;

/**
 * @author ZhangShenao
 * @date 2023/7/16 3:33 PM
 * Description 通过volatile保证可见性
 */
public class EnsureVisibilityByVolatile {
    private static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (flag) {
                //T1 running...
            }

            System.out.println("Thread T1: Stopped by flag");
        }, "T1").start();

        Thread.sleep(2000L);

        new Thread(() -> {
            //根据JMM模型,此处main线程对flag变量的修改,对T1不可见,因此T1无法终止
            flag = false;
            System.out.println("Thread T2: Set flag to false");
        }, "T2").start();

    }
}
