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
        this.head = new Node<>((int) maxLevel, null);
    }

    public long getLength() { return length; }

    public long getLevel() { return level; }

    public long getMaxLevel() { return maxLevel; }

    private long getRandLevel() {
        long level = 1;
        while ((rand.nextInt()&0xffff) < (factor*0xffff)) {
            level++;
        }
        if (level < this.maxLevel) {
            return level;
        }
        return this.maxLevel;
    }

    public void insert(Item<T> item) {
        Node<T> node = this.head;
        List<Node<T>> updates = new ArrayList<>();
        for (int i = 0; i < this.maxLevel; i++) {
            updates.add(null);
        }
        for (int i = (int) this.level - 1; i >= 0; i--) {
			while (Objects.nonNull(node.forwards.get(i)) && node.forwards.get(i).item.less(item)) {
                node = node.forwards.get(i);
            }
            updates.set(i, node);
        }
        long level = this.getRandLevel();
        if (level > this.level) {
            for (int i = (int) this.level; i < level; i++) {
                updates.set(i, this.head);
            }
            this.level = level;
        }
        node = new Node<>((int) level, item);
        for (int i = 0; i < level; i++) {
            Node<T> forward = updates.get(i).forwards.get(i);
            node.forwards.set(i, forward);
            updates.get(i).forwards.set(i, node);
        }
        this.length++;
    }

    public Item<T> pop() {
        if (this.length == 0) {
            return null;
        }
        Node<T> node = this.head.forwards.get(0);
        for (int i = (int) this.level - 1; i >= 0; i--) {
            if (this.head.forwards.get(i).equals(node)) {
                this.head.forwards.set(i, node.forwards.get(i));
            }
        }
        while (this.level > 1 && Objects.isNull(this.head.forwards.get((int) (this.level - 1)))) {
            this.level--;
        }
        this.length--;
        return node.item;
    }

    public void clear() {
    }

    public Item<T> get(Item<T> item) {
        Node<T> node = this.head;
        for (int i = (int)(this.level - 1); i >= 0; i--) {
            while (Objects.nonNull(node.forwards.get(i)) && node.forwards.get(i).item.less(item)) {
                node = node.forwards.get(i);
            }
        }
        node = node.forwards.get(0);
        if (Objects.nonNull(node) && !node.item.less(item) && !item.less(node.item)) {
            return node.item;
        }
        return null;
    }

    public boolean has(Item<T> item) {
        return Objects.nonNull(this.get(item));
    }

    public Item<T> delete(Item<T> item) {
        Node<T> node = this.head;
        List<Node<T>> updates = new ArrayList<>();
        for (int i = (int)(this.level - 1); i >= 0; i--) {
            while (Objects.nonNull(node.forwards.get(i)) && node.forwards.get(i).item.less(item)) {
                node = node.forwards.get(i);
            }
            updates.set(i, node);
        }
        if (Objects.nonNull(node) && !node.item.less(item) && !item.less(node.item)) {
            return node.item;
        }

        for (int i = 0; i < this.level; i++) {
            if (updates.get(i).forwards.get(i).equals(node)) {
                updates.get(i).forwards.set(i, node.forwards.get(i));
            }
        }

        while (this.level > 1 && Objects.isNull(this.head.forwards.get((int)this.level - 1))) {
            this.level--;
        }
        this.length--;
        return node.item;
    }

    // for debug
    public void print() {
        for (int i = 0; i < this.level; i++) {
            Node<T> n = this.head.forwards.get(i);
            System.out.printf("Level[%d]: ", i);
            while (Objects.nonNull(n) && Objects.nonNull(n.item)) {
                System.out.printf("%d -> ", (int)n.item.getScore());
                n = n.forwards.get(i);
            }
            System.out.println("nil");
        }
    }

    public Item<T> peek() {
        return null;
    }
}