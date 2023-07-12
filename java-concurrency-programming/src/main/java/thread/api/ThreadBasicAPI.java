package thread.api;

/**
 * @author ZhangShenao
 * @date 2023/7/12 2:02 PM
 * Description 线程的基本API
 */
public class ThreadBasicAPI {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            //获取当前线程
            Thread thread = Thread.currentThread();
            System.out.println("Current Thread: " + thread);
        });

        //设置线程名称
        //无论是使用自定义的线程还是线程池,都要为线程设置能代表具体业务含义的名称,方便故障排查
        t.setName("My-Thread-1");

        t.start();

        Thread main = Thread.currentThread();
        System.out.println("Main Thread: " + main);
    }
}
