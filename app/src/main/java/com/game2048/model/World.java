package com.game2048.model;

import com.framework.Game;
import com.framework.Graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Иван on 26.06.2015.
 */
public class World {
    List<Block> blocks;

    public World(Game game) {
        blocks = new ArrayList<>();

        Graphics g = game.getGraphics();
        int widthScreen = g.getWidth();
        int size = 55;
        int blocksWidth = 55 * 4;

        int marginHeight = widthScreen - blocksWidth;

        int margin = marginHeight / 6;
        int startX = (int) (margin * 1.5);
        int startY = 100;

        //[0, 0]
        blocks.add(new Block(startX, startY));
        //[0, 1]
        blocks.add(new Block(startX + margin + size, startY));
        //[0, 2]
        blocks.add(new Block(startX + (margin * 2) + (size * 2), startY));
        //[0, 3]
        blocks.add(new Block(startX + (margin * 3) + (size * 3), startY));
    }

    public List<Block> getBlocks() {
        return blocks;
    }
}
