package com.remones.skiplist;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    public IItem item;
    public List<Node<T>> forwards;

    public Node(IItem item) {
        this.forwards = new ArrayList<>();
        this.item = item;
    }
}