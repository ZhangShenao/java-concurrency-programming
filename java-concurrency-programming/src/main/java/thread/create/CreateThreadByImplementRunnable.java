package thread.create;

/**
 * @author ZhangShenao
 * @date 2023/7/12 10:01 AM
 * Description 创建线程方式二:实现Runnable接口,重写run方法
 */
public class CreateThreadByImplementRunnable {
    public static void main(String[] args) {
        Runnable r = new MyTask();
        Thread t = new Thread(r);
        t.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("Main Thread is running. i = " + i);
        }
    }

    //自定义Runnable任务
    private static class MyTask implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("My MyTask is running. i = " + i);
            }
        }
    }
}
