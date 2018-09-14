package com.myapplication;



/**
 * Author LI .
 * Time 2018/9/13
 * Description is Looper
 */
public class Looper {

    MessageQueue mQueue;
    static final ThreadLocal<Looper> threadLocal = new ThreadLocal<>();

    private Looper(){
        mQueue = new MessageQueue();
//        mThread = Thread.currentThread();
    }

    public static void prepare(){
        if (threadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        threadLocal.set(new Looper());
    }

    public static  Looper myLooper(){
        return threadLocal.get();
    }

    public static  void loop(){
        Looper me = Looper.myLooper();
        if (me == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }
        MessageQueue mQueue = me.mQueue;
        for (; ; ) {
            Message next = mQueue.next();
            if(next==null){
                continue;
            }
            next.target.dispatchMessage(next);
        }
    }
}
