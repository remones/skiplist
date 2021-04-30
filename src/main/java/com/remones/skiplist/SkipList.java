package com.remones.skiplist;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SkipList<T> {
    private long length;
    private long level;
    private long maxLevel;
    private Node<T> head;
    private final Random rand = new Random(System.currentTimeMillis());
    private static final double factor = 0.5;


    public SkipList(long maxLevel) {
        this.maxLevel = maxLevel;
        this.head = new Node<>(null);
    }

    public long getLength() { return length; }

    public long getLevel() { return level; }

    public long getMaxLevel() { return maxLevel; }

    private long getRandLevel() {
        long level = 1;
        while ((rand.nextLong()&0xffff) < (factor*0xffff)) {
            level++;
        }
        if (level < this.maxLevel) {
            return level;
        }
        return this.maxLevel;
    }

    public void insert(IItem item) {
        Node<T> node = this.head;
        List<Node<T>> updates = new ArrayList<>();
        for (int i = (int) this.level - 1; i >= 0; i--) {
			while (Objects.nonNull(node.item) && node.item.less(item)) {
                node = node.forwards.get(i);
            }
            updates.set(i, node);
        }
        long level = this.getRandLevel();
        if (level > this.level) {
            for (int i = (int) this.level; i < level; i++) {
                updates.get(i).forwards.set(i, this.head);
            }
        }
        this.level = level;
        node = new Node<>(item);
        for (int i = 0; i < this.level; i++) {
            Node<T> forward = updates.get(i).forwards.get(i);
            node.forwards.set(i, forward);
            updates.get(i).forwards.set(i, node);
        }
    }

    public IItem pop() {
        return null;
    }

    public void clear() {

    }

    public boolean has(IItem item) {
        return false;
    }

    public IItem delete(IItem item) {
        return null;
    }

    public IItem peek() {
        return null;
    }
}