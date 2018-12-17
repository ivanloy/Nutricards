package com.ivanloy.nutricards.ds;

/**
 * FIFO Queue data structure
 *
 * @author Ignacio Van Loy
 *
 * @param <T>
 */
public class Stequeue<T>{ //TODO Iterator, doc, refactor, invertir push/draw/enqueue, extend ds

    private int size;
    private T[] queue;

    public Stequeue() {

        size = 0;
        queue = (T[]) new Object[2];

    }

    public Stequeue(int initialSize) {

        this.size = 0;
        queue = (T[]) new Object[initialSize];

    }

    public int size() { return size; }


    public void enqueue(T item) {

        if(size >= queue.length) resize(size * 2);
        queue[size++] = item;

    }

    public void push(T item) {

        if(size >= queue.length) resize(size * 2);
        shiftToRight();
        queue[size++] = item;

    }


    public T pop() {

        T item = queue[0];
        shiftToLeft();
        size--;

        if (size > 0 && size == queue.length/4) resize(queue.length/2);

        return item;

    }

    public void shiftToLeft(){

        for(int i = size - 1; i > 0; i++){
            queue[i-1] = queue[i];
        }

        queue[size - 1] = null;

    }

    public void shiftToRight(){

        for(int i = 0; i < size; i++){
            queue[i+1] = queue[i];
        }

        queue[0] = null;

    }

    public void shuffle() {

        int random;
        T n;

        for(int i = size - 1; i > 0; i--) {

            random = (int)( Math.floor( Math.random() * (i + 1) ) );
            n = queue[random];
            queue[random] = queue[i];
            queue[i] = n;

        }

    }

    public boolean isEmpty(){
        boolean ret = false;
        if(size == 0) ret = true;
        return ret;
    }

    public void resize(int max) {

        T[] queue_ = (T[]) new Object[max];
        for(int i = 0; i < size; i++) queue_[i] = queue[i];
        queue = queue_;

    }

}