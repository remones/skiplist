package com.remones.skiplist;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    public Item<T> item;
    public List<Node<T>> forwards;

    public Node(int size, Item<T> item) {
        this.forwards = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            this.forwards.add(null);
        }
        this.item = item;
    }
}