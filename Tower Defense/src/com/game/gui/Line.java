package com.game.gui;

import com.game.Vector2f;

public final class Line {
    private final Vector2f a, b;

    public Line(Vector2f a, Vector2f b) {
        this.a = a;
        this.b = b;
    }

    public Vector2f getA() {
        return a;
    }

    public Vector2f getB() {
        return b;
    }
}