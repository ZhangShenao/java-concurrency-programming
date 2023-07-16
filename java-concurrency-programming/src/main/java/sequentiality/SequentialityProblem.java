package sequentiality;

/**
 * @author ZhangShenao
 * @date 2023/7/16 4:16 PM
 * Description 并发编程的问题根源三：有序性问题
 */
public class SequentialityProblem {
    private static int a, b, x, y;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            a = b = x = y = 0;

            //验证指令重排序
            Thread t1 = new Thread(() -> {
                a = 1;
                x = b;
            }, "T1");

            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
            }, "T2");

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            //验证指令重排序
            if (x == 0 && y == 0) {
                System.out.println("x=y=0! i = " + i);
            }
        }
    }
}
