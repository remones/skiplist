package com.remones.skiplist;

public interface Item<T> {
    public boolean less(Item<T> other);
    public double getScore();
    public T getValue();
}


