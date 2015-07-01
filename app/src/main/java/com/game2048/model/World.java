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
        int heightScreen = g.getHeight();
        int widthScreen = g.getWidth();

//        int size = 50;
//        int margin = 5;
//
//        int startX = 30;
//        int startY = 100;

//        Todo сделать расчет чтобы с пробелмаи они там помещалист

        for (int i = 0; i < 4; i++) {
            boolean isMargin = false;
            for (int j = 0; j < 4; j++) {
                if (!isMargin) {
//                    blocks.add(new Block(i * size + startX, j * size + startY));
                    isMargin = true;
                } else {
//                    blocks.add(new Block(i * size + startX + margin, j * size + startY));
                }
            }
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }
}
