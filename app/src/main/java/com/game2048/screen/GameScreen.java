package com.game2048.screen;

import android.graphics.Color;
import android.util.Log;

import com.framework.Game;
import com.framework.Graphics;
import com.framework.Screen;
import com.game2048.Game2048;
import com.game2048.model.Block;
import com.game2048.model.World;
import com.game2048.model.enums.Direction;
import com.game2048.util.Assets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Иван on 26.06.2015.
 */
public class GameScreen extends Screen {
    World world;

    public GameScreen(Game game) {
        super(game);
        world = new World(game);
        generateTips();
        generateTips();
    }

    private void generateTips() {
        Random random = new Random();
        int value = generateValue(random);
        Block block = getItem(random);
        block.setValue(value);
    }

    private Block getItem(Random random) {
        Block item = world.getBlocks().get(random.nextInt(world.getBlocks().size()));
        if (item.getValue() > 0) {
            return getItem(random);
        }
        return item;
    }

    private int generateValue(Random random) {
        int value = random.nextInt(5);
        if (value != 4 && value != 2) {
            return generateValue(random);
        }
        return value;
    }

    @Override
    public void update(float deltaTime) {

    }

    public void update(Direction direction) {
        List<Block[]> lines;
        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            lines = getLines(true);
            for (Block[] line : lines) {
                collectLine(line, direction);
            }
        } else {
            lines = getLines(false);
            for (Block[] line : lines) {
                collectLine(line, direction);
            }
        }
    }

    private Block[] collectLine(Block[] line, Direction direction) {
        boolean isCollected = false;

        List<Block> notZeroLine = new ArrayList<>();
        for (int i = 0; i < line.length - 1; i++) {
            //избавляемся от нулей
            if (line[i].getValue() > 0) {
                notZeroLine.add(line[i]);
            }
        }

        for (int i = 0; i < notZeroLine.size() - 1; i++) {
            if (notZeroLine.get(i).getValue() == notZeroLine.get(i + 1).getValue()) {
                notZeroLine.get(i).setValue(notZeroLine.get(i).getValue() * 2);
                notZeroLine.get(i + 1).setValue(0);
                isCollected = true;
            }
        }

        if (direction == Direction.RIGHT || direction == Direction.BOTTOM) {
            int count = 0;
            int[] newLine = new int[4];
            for (Block block : line) {
                if (block.getValue() == 0) {
                    newLine[count] = block.getValue();
                    count++;
                }
            }

            for (Block block : line) {
                if (block.getValue() > 0) {
                    newLine[count] = block.getValue();
                    count++;
                }
            }

            for (int i = 0; i < line.length; i++) {
                line[i].setValue(newLine[i]);
            }
        } else if (direction == Direction.LEFT || direction == Direction.UP) {
            int count = 0;
            int[] newLine = new int[4];

            for (Block block : line) {
                if (block.getValue() > 0) {
                    newLine[count] = block.getValue();
                    count++;
                }
            }

            for (Block block : line) {
                if (block.getValue() == 0) {
                    newLine[count] = block.getValue();
                    count++;
                }
            }

            for (int i = 0; i < line.length; i++) {
                line[i].setValue(newLine[i]);
            }
        }

        if (isCollected) {
            return collectLine(line, direction);
        } else {
            return line;
        }
    }

    private List<Block[]> getLines(boolean isHorizontal) {
        Block[][] matrix = world.getMatrix();

        List<Block[]> blocksList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Block[] blockArr = new Block[4];
            for (int j = 0; j < 4; j++) {
                if (isHorizontal) {
                    blockArr[j] = matrix[i][j];
                } else {
                    blockArr[j] = matrix[j][i];
                }

                if (j == 3) {
                    blocksList.add(blockArr);
                }
            }
        }


        return blocksList;
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.bg, 0, 0);

        List<Block> blocks = world.getBlocks();
        for (Block block : blocks) {
            g.drawRectWithText(block.getX(), block.getY(), 55, 55, Color.BLACK,
                    block.getValue() > 0 ? String.valueOf(block.getValue()) : "", Color.GREEN);
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
