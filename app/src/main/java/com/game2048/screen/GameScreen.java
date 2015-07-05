package com.game2048.screen;

import android.graphics.Color;

import com.framework.Game;
import com.framework.Graphics;
import com.framework.Input;
import com.framework.Screen;
import com.game2048.model.Block;
import com.game2048.model.World;
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
    }

    private void generateTips() {
        Random random = new Random();
        int value = generateValue(random);

        Block block = getItem(random);
    }

    private Block getItem(Random random) {
        //Todo
        return null;
    }

    private int generateValue(Random random) {
        int value = random.nextInt(4);
        if (value != 4 && value != 2) {
            return generateValue(random);
        }
        return value;
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {

            }
        }
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
