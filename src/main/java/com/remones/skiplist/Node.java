package com.remones.skiplist;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    public Item<T> item;
    public List<Node<T>> forwards;

    public Node(Item<T> item) {
        this.forwards = new ArrayList<>();
        this.item = item;
    }
}