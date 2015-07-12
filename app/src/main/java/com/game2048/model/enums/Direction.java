package com.game2048.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Иван on 12.07.2015.
 */
public enum Direction {
    LEFT("L"),
    RIGHT("R"),
    UP("U"),
    BOTTOM("B");

    private final String id;

    private static final Map<String, Direction> data = new HashMap<String, Direction>();

    static {
        for(Direction direction : values()) {
            data.put(direction.id, direction);
        }
    }

    Direction (String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static Direction getById(String id) {
        return data.get(id);
    }
}
