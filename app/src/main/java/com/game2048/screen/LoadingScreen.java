package com.game2048.screen;

import com.framework.Game;
import com.framework.Graphics;
import com.framework.Screen;
import com.game2048.util.Assets;
import com.game2048.util.Settings;

/**
 * Created by Иван on 22.06.2015.
 */
public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.bg = g.newPixmap("bg.png", Graphics.PixmapFormat.RGB565);
        Assets.block = g.newPixmap("block.png", Graphics.PixmapFormat.ARGB4444);
        Settings.load(game.getFileIO());
        game.setScreen(new GameScreen(game));
    }

    @Override
    public void present(float deltaTime) {

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
