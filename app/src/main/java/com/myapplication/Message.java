package com.myapplication;

/**
 * Author LI .
 * Time 2018/9/13
 * Description is Message
 */
public class Message {
    public int what;
    public Object obj;
    Handler target;

    public Message(){

    }

    @Override
    public String toString() {
        return "Message{" +
                "what=" + what +
                ", obj=" + obj +
                ", target=" + target +
                '}';
    }
}
