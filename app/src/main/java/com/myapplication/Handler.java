package com.myapplication;



/**
 * Author LI .
 * Time 2018/9/13
 * Description is Handler
 */
public class Handler {

    private Looper looper;
    private MessageQueue queue;

    public Handler(){
        looper = Looper.myLooper();
        queue = looper.mQueue;
    }

    public void sendMessage(Message message){
        message.target = this;
        queue.enqueueMessage(message);
    }

    public void handlerMessage(Message message){

    }

    public void dispatchMessage(Message message){
        handlerMessage(message);
    }
}
