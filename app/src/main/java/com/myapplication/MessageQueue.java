package com.myapplication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author LI .
 * Time 2018/9/13
 * Description is MessageQueue
 */
public class MessageQueue {

    private Message[] item;

    private int putIndex;
    private int takeIndex;
    int count;
    private Lock lock;
    private Condition notEmpty;
    private Condition notFull;

    public MessageQueue() {
        item = new Message[50];
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();

    }

    public void enqueueMessage(Message message) {
        try{
            lock.lock();
            while(count == 50){
                notFull.await();
            }
            item[putIndex] = message;
            putIndex = (++putIndex == item.length) ? 0 : putIndex;
            count++;
            notEmpty.signalAll();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public Message next() {
        Message message = null;
        try{
            lock.lock();
            while(count == 0){
                notEmpty.await();
            }

            message = item[takeIndex];
            item[takeIndex] = null;
            takeIndex = (++takeIndex == item.length) ? 0 : takeIndex;
            count--;
            notFull.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return message;
    }
}
