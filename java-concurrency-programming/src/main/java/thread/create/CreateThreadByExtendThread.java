package thread.create;

/**
 * @author ZhangShenao
 * @date 2023/7/12 9:57 AM
 * Description 创建线程方式一:继承Thread类,重写run方法
 */
public class CreateThreadByExtendThread {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("Main Thread is running. i = " + i);
        }
    }

    //自定义线程
    private static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("My Thread is running. i = " + i);
            }
        }
    }
}
