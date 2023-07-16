package sequentiality;

/**
 * @author ZhangShenao
 * @date 2023/7/16 4:23 PM
 * Description 双重检查锁单例模式
 */
public class DoubleCheckLockSingleton {
    //解决方式：将单例实例声明为volatile,禁止指令重排序
    private static volatile DoubleCheckLockSingleton INSTANCE;

    private DoubleCheckLockSingleton() {
    }

    public static DoubleCheckLockSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleCheckLockSingleton.class) {
                if (INSTANCE == null) {
                    //DCL双重检查锁单例模式存在的问题：
                    //由于指令重排序问题,在单例的构造器中,可能先执行内存地址的分配,后执行属性的初始化
                    //导致其它线程可能获取到未初始化完成的单例对象,进而出现一些不可预知的异常
                    INSTANCE = new DoubleCheckLockSingleton();
                }
            }
        }

        return INSTANCE;
    }
}
