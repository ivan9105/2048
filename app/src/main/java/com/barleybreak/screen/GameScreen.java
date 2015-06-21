package com.barleybreak.screen;

import android.graphics.Color;

import com.barleybreak.model.Square;
import com.barleybreak.model.World;
import com.barleybreak.util.Assets;
import com.barleybreak.util.Settings;
import com.framework.Graphics;
import com.framework.Input;
import com.framework.Input.TouchEvent;
import com.framework.Game;
import com.framework.Screen;

import java.util.List;

/**
 * Created by Иван on 02.06.2015.
 */
public class GameScreen extends Screen {
    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }

    GameState state = GameState.Ready;
    World world;
    int oldScore = 0;
    String score = "0";

    public GameScreen(Game game) {
        super(game);
        world = new World();
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        if (touchEvents.size() > 0) {
            int len = touchEvents.size();
            for (int i = 0; i < len; i++) {
                TouchEvent event = touchEvents.get(i);
                if (event.type == TouchEvent.TOUCH_UP) {
                    state = GameState.Running;
                }
            }
        }
    }

    private void updateRunning(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                world.getClickedSquare(event.x, event.y);

                int[] numbs = {1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15, 4, 8, 12, 0};
                if (checkGameOver(numbs)) {
                    world.setGameOver(true);
                    state = GameState.GameOver;
                }

                if (event.x < 64 && event.y < 64) {
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    state = GameState.Paused;
                    return;
                }
            }

            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x < 64 && event.y < 64) {
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    state = GameState.Paused;
                    return;
                }
            }
        }
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (event.x > 60 && event.x <= 240) {
                    if (event.y > 175 && event.y <= 225) {
                        if (Settings.soundEnabled)
                            Assets.click.play(1);
                        state = GameState.Running;
                        return;
                    }

                    if (event.y > 235 && event.y < 285) {
                        if (Settings.soundEnabled)
                            Assets.click.play(1);
                        game.setScreen(new MainMenuScreen(game));
                        return;
                    }
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (event.x >= 128 && event.x <= 192 &&
                        event.y >= 200 && event.y <= 264) {
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    public boolean checkGameOver(int[] numbs) {
        List<Integer> listN = world.getNums();
        int count = 0;
        for (int i = 0; i < numbs.length; i++) {
            if (listN.get(i) == numbs[i]) {
                count++;
            }
        }

        return count == numbs.length;
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        drawWorld();

        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();
    }

    private void drawWorld() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.ready, 47, 100);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        List<Square> squares = world.getSquares();
        for (Square square : squares) {
            if (square.getValue() != 0) {
                g.drawPixmap(Assets.square, square.getX(), square.getY());
                drawText(g, String.valueOf(square.getValue()), square.getX() + 22, square.getY() + 17);
            }
        }

        g.drawPixmap(Assets.buttons, 0, 0, 64, 128, 64, 64);
    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.pause, 60, 175);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.gameOver, 62, 100);
        g.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            if (i == 1) {
                x = x - 10;
            }

            if (line.equals("1")) {
                x = x + 5;
            }

            if (character == '1') {
                g.drawPixmap(Assets.numbers, x, y, 0, 0, 10, 54);
            }

            if (character == '2') {
                g.drawPixmap(Assets.numbers, x, y, 10, 0, 25, 54);
            }

            if (character == '3') {
                g.drawPixmap(Assets.numbers, x, y, 36, 0, 24, 54);
            }

            if (character == '4') {
                g.drawPixmap(Assets.numbers, x, y, 61, 0, 27, 54);
            }

            if (character == '5') {
                g.drawPixmap(Assets.numbers, x, y, 89, 0, 22, 54);
            }

            if (character == '6') {
                g.drawPixmap(Assets.numbers, x, y, 112, 0, 22, 54);
            }

            if (character == '7') {
                g.drawPixmap(Assets.numbers, x, y, 138, 0, 21, 54);
            }

            if (character == '8') {
                g.drawPixmap(Assets.numbers, x, y, 160, 0, 19, 54);
            }

            if (character == '9') {
                g.drawPixmap(Assets.numbers, x, y, 179, 0, 23, 54);
            }

            if (character == '0') {
                g.drawPixmap(Assets.numbers, x, y, 203, 0, 24, 54);
            }

            if (character == '.') {
                g.drawPixmap(Assets.numbers, x, y, 228, 0, 10, 54);
            }

            if (character == ':') {
                g.drawPixmap(Assets.numbers, x, y, 239, 0, 10, 54);
            }

            if (character == '-') {
                g.drawPixmap(Assets.numbers, x, y, 250, 0, 14, 54);
            }

            x += srcWidth;
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
