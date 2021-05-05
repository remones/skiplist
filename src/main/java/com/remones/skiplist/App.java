package com.remones.skiplist;

import java.util.Random;


class Element<T> implements Item<T> {
    private final double score;
    private final T value;

    Element(double score, T value) {
        this.score = score;
        this.value = value;
    }

    public boolean less(Item<T> other) {
        return this.score < other.getScore();
    };

    public double getScore() {
        return this.score;
    }

    public T getValue() {
        return this.value;
    }
 }

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        SkipList<String> sl = new SkipList<>(10);
        final Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            int n = rand.nextInt(10);
            Element<String> e = new Element<>((double)n, String.format("value-%d", n));
            sl.insert(e);
        }
        sl.print();
    }
}
