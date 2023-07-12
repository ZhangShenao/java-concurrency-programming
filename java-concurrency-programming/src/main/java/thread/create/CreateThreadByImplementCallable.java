package thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author ZhangShenao
 * @date 2023/7/12 10:05 AM
 * Description 创建线程方式三:实现Callable接口,重写call方法,并配合FutureTask获取执行结果
 */
public class CreateThreadByImplementCallable {
    public static void main(String[] args) throws Exception {
        //1.创建自定义的Callable对象
        MyCallable c = new MyCallable();

        //2.创建FutureTask对象,对Callable进行包装
        FutureTask<Integer> f = new FutureTask<>(c);

        //3.创建Thread对象,传入FutureTask,用于执行任务并接收结果
        Thread t = new Thread(f);

        //4.启动线程
        t.start();

        //5.获取执行结果
        Integer result = f.get();
        System.out.println("Result is : " + result);
    }

    //自定义Callable,用于执行具有返回值的任务
    private static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() {
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
