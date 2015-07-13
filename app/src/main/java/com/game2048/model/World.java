package com.game2048.model;

import com.framework.Game;
import com.framework.Graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Иван on 26.06.2015.
 */
public class World {
    private List<Block> blocks;
    private Block[][] matrix;

    public World(Game game) {
        blocks = new ArrayList<>();
        matrix = new Block[4][4];

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
        matrix[0][0] = blocks.get(0);
        //[0, 1]
        blocks.add(new Block(startX + margin + size, startY));
        matrix[0][1] = blocks.get(1);
        //[0, 2]
        blocks.add(new Block(startX + (margin * 2) + (size * 2), startY));
        matrix[0][2] = blocks.get(2);
        //[0, 3]
        blocks.add(new Block(startX + (margin * 3) + (size * 3), startY));
        matrix[0][3] = blocks.get(3);

        //[1, 0]
        blocks.add(new Block(startX, startY + margin + size));
        matrix[1][0] = blocks.get(4);
        //[1, 1]
        blocks.add(new Block(startX + margin + size, startY + margin + size));
        matrix[1][1] = blocks.get(5);
        //[1, 2]
        blocks.add(new Block(startX + (margin * 2) + (size * 2), startY + margin + size));
        matrix[1][2]= blocks.get(6);
        //[1, 3]
        blocks.add(new Block(startX + (margin * 3) + (size * 3), startY + margin + size));
        matrix[1][3] = blocks.get(7);

        //[2, 0]
        blocks.add(new Block(startX, startY + (margin * 2) + (size * 2)));
        matrix[2][0] = blocks.get(8);
        //[2, 1]
        blocks.add(new Block(startX + margin + size, startY + (margin * 2) + (size * 2)));
        matrix[2][1] = blocks.get(9);
        //[2, 2]
        blocks.add(new Block(startX + (margin * 2) + (size * 2), startY + (margin * 2) + (size * 2)));
        matrix[2][2] = blocks.get(10);
        //[2, 3]
        blocks.add(new Block(startX + (margin * 3) + (size * 3), startY + (margin * 2) + (size * 2)));
        matrix[2][3] = blocks.get(11);

        //[3, 0]
        blocks.add(new Block(startX, startY + (margin * 3) + (size * 3)));
        matrix[3][0] = blocks.get(12);
        //[3, 1]
        blocks.add(new Block(startX + margin + size, startY + (margin * 3) + (size * 3)));
        matrix[3][1] = blocks.get(13);
        //[3, 2]
        blocks.add(new Block(startX + (margin * 2) + (size * 2), startY + (margin * 3) + (size * 3)));
        matrix[3][2] = blocks.get(14);
        //[3, 3]
        blocks.add(new Block(startX + (margin * 3) + (size * 3), startY + (margin * 3) + (size * 3)));
        matrix[3][3] = blocks.get(15);
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public Block[][] getMatrix() {
        return matrix;
    }
}
