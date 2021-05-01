package com.remones.skiplist;


class Element implements Item<Element> {
    private final String value;
    private final int score;

    Element(int score, String value) {
        this.score = score;
        this.value = value;
    }

    public boolean less(Element other) {
        return this.score < other.getScore();
    };

    public Element self() {
        return this;
    }

    public int getScore() {
        return this.score;
    }

    public String print() {
        return String.format("(%d, %s)", this.score, this.value);
    }
 }

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        SkipList<Element> sl = new SkipList<>(10);
        for (int i = 0; i < 10; i++) {
            Element e = new Element(i, String.format("value-%d", i));
            sl.insert(e);
        }
        sl.print();
    }
}
