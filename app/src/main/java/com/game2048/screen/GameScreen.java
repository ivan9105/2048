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
        Log.d(Game2048.LOG_TAG, direction.getId());
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
