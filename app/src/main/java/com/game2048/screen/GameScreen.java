package com.game2048.screen;

import com.framework.Game;
import com.framework.Graphics;
import com.framework.Screen;
import com.game2048.model.Block;
import com.game2048.model.World;
import com.game2048.util.Assets;

import java.util.List;

/**
 * Created by Иван on 26.06.2015.
 */
public class GameScreen extends Screen {
    World world;

    public GameScreen(Game game) {
        super(game);
        world = new World(game);
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.bg, 0, 0);

        List<Block> blocks = world.getBlocks();
        for (Block block : blocks) {
            g.drawPixmap(Assets.block, block.getX(), block.getY(), 50, 50);
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
