package sync;

/**
 * @author ZhangShenao
 * @date 2023/8/24 3:47 PM
 * @description: TODO
 * <p>通过 javap -v SynchronizedDemo.class 指令,对字节码进行反编译,可以看到同步代码块对应的monitorenter/monitorexit指令</p>
 */
public class SynchronizedDemo {
    
    //同步方法
    public synchronized int incr(int i) {
        return ++i;
    }
    
    //同步代码块
    public int incrBy(int i, int step) {
        synchronized (this) {
            return (i + step);
        }
    }
}
