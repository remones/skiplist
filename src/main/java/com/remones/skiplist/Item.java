package com.remones.skiplist;

public interface Item<T> {
    public boolean less(T value);
    public T self();
}


