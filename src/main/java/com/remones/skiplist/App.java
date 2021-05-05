package com.remones.skiplist;


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
        for (int i = 0; i < 10; i++) {
            Element<String> e = new Element<>((double)i, String.format("value-%d", i));
            sl.insert(e);
        }
        sl.print();
    }
}
