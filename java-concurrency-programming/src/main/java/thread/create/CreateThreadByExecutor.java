package thread.create;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShenao
 * @date 2023/7/12 10:12 AM
 * Description 创建线程方式四:使用线程池
 */
public class CreateThreadByExecutor {
    public static void main(String[] args) throws Exception {
        //创建线程池
        ExecutorService executor = Executors.newCachedThreadPool();

        //提交任务
        executor.submit(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("Task is running. i = " + i);
            }
        });

        for (int i = 0; i < 100; i++) {
            System.out.println("Main Thread is running. i = " + i);
        }

        //关闭线程池
        executor.awaitTermination(1L, TimeUnit.SECONDS);
    }
}
