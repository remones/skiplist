package com.remones.skiplist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.util.Random;

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

public class SkipListTest {
    /**
     * Rigorous Test.
     */
    @Test
    public void testInsert() {
        SkipList<String> sl = new SkipList<>(10);
        for (int i = 0; i < 10; i++) {
            Element<String> e = new Element<>((double)i, String.format("value-%d", i));
            sl.insert(e);
        }
        for (int i = 0; i < 10; i++) {
            Element<String> item = (Element<String>) sl.pop();
            Assertions.assertEquals(i, item.getScore());
        }
    }
}
