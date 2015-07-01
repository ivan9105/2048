package com.game2048.model;

/**
 * Created by Иван on 26.06.2015.
 */
public class Block {
    private int x;
    private int y;
    private int value;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Block(int x, int y, int value) {
        this(x, y);
        this.value = value;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
