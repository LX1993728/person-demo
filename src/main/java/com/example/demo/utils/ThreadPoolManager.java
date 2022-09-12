package com.example.demo.utils;

import com.example.demo.tasks.BaseTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 线程池管理 管理整个项目中所有的线程，所以不能有多个实例对象
 */
@Slf4j
public class ThreadPoolManager {

    private static ThreadPoolManager mInstance = new ThreadPoolManager();

    public static ThreadPoolManager getInstance() {
        return mInstance;
    }

    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime = 1;
    private TimeUnit unit = TimeUnit.HOURS;
    private ThreadPoolExecutor executor;

    private ThreadPoolManager() {
        corePoolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
        maximumPoolSize = corePoolSize;
        executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        ){
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                if (t == null){
                    return;
                }

                if (r instanceof BaseTask){
                    BaseTask task = (BaseTask) r;
                    log.error("{}---{}", task.getDesc(), task.getT().toString(), t);
                }else {
                    log.error("UNKNOWN TASK ", t);
                }
            }
        };
    }

    /**
     * 执行任务
     */
    public void execute(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        executor.execute(runnable);
    }

    /**
     * 从线程池中移除任务
     */
    public void remove(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        executor.remove(runnable);
    }
}
