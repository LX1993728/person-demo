package com.example.demo.tasks;

import com.example.demo.config.SpringBootBeanAutowiringSupport;

public abstract class BaseTask<T> extends SpringBootBeanAutowiringSupport implements Runnable {
    private T t;

    public BaseTask(T t) {
        this.t = t;
    }

    @Override
    public void run() {
        doRunnable(t);
    }

    protected abstract  void doRunnable(T t);

    public abstract String getDesc();


    public T getT() {
        return t;
    }

}
