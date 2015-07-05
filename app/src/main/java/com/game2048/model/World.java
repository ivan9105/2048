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
        int startX = (int) (margin * 1.5) + 2;
        int startY = 100;

        //[0, 0]
        blocks.add(new Block(startX, startY));
        //[0, 1]
        blocks.add(new Block(startX + margin + size, startY));
        //[0, 2]
        blocks.add(new Block(startX + (margin * 2) + (size * 2), startY));
        //[0, 3]
        blocks.add(new Block(startX + (margin * 3) + (size * 3), startY));

        //[1, 0]
        blocks.add(new Block(startX, startY + margin + size));
        //[1, 1]
        blocks.add(new Block(startX + margin + size, startY + margin + size));
        //[1, 2]
        blocks.add(new Block(startX + (margin * 2) + (size * 2), startY + margin + size));
        //[1, 3]
        blocks.add(new Block(startX + (margin * 3) + (size * 3), startY + margin + size));

        //[2, 0]
        blocks.add(new Block(startX, startY + (margin * 2) + (size * 2)));
        //[2, 1]
        blocks.add(new Block(startX + margin + size, startY + (margin * 2) + (size * 2)));
        //[2, 2]
        blocks.add(new Block(startX + (margin * 2) + (size * 2), startY + (margin * 2) + (size * 2)));
        //[2, 3]
        blocks.add(new Block(startX + (margin * 3) + (size * 3), startY + (margin * 2) + (size * 2)));

        //[3, 0]
        blocks.add(new Block(startX, startY + (margin * 3) + (size * 3)));
        //[3, 1]
        blocks.add(new Block(startX + margin + size, startY + (margin * 3) + (size * 3)));
        //[3, 2]
        blocks.add(new Block(startX + (margin * 2) + (size * 2), startY + (margin * 3) + (size * 3)));
        //[3, 3]
        blocks.add(new Block(startX + (margin * 3) + (size * 3), startY + (margin * 3) + (size * 3)));
    }

    public List<Block> getBlocks() {
        return blocks;
    }
}
