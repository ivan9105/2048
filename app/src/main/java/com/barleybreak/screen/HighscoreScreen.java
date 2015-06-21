package com.barleybreak.screen;

import com.barleybreak.util.Assets;
import com.barleybreak.util.Settings;
import com.framework.Game;
import com.framework.Graphics;
import com.framework.Screen;
import com.framework.Input.TouchEvent;
import com.framework.Input.KeyEvent;

import java.util.List;

/**
 * Created by Иван on 02.06.2015.
 */
public class HighscoreScreen extends Screen {
    String lines[] = new String[5];

    public HighscoreScreen(Game game) {
        super(game);

        for (int i = 0; i < 5; i++) {
            lines[i] = "" + (i + 1) + ". " + Settings.results[i];
        }
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        List<KeyEvent> keyEvents = game.getInput().getKeyEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x > 256 && event.y > 416) {
                    game.setScreen(new MainMenuScreen(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);

        int y = 100;
        for (int i = 0; i < 5; i++) {
            drawText(g, lines[i], 20, y);
            y += 50;
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

    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            int srcX = 0;
            int srcWidth = 0;

            if (character == '1'){
                srcX = 0;
                srcWidth = 10;
            } else if (character == '2') {
                srcX = 10;
                srcWidth = 25;
            } else if (character == '3') {
                srcX = 36;
                srcWidth = 24;
            } else if (character == '4') {
                srcX = 61;
                srcWidth = 27;
            } else if (character == '5') {
                srcX = 89;
                srcWidth = 22;
            } else if (character == '6') {
                srcX = 112;
                srcWidth = 22;
            } else if (character == '7') {
                srcX = 138;
                srcWidth = 21;
            } else if (character == '8') {
                srcX = 160;
                srcWidth = 19;
            } else if (character == '9') {
                srcX = 179;
                srcWidth = 23;
            } else if (character == '0') {
                srcX = 203;
                srcWidth = 24;
            } else if (character == '.') {
                srcX = 228;
                srcWidth = 10;
            } else if (character == ':') {
                srcX = 239;
                srcWidth = 10;
            } else if (character == '-') {
                srcX = 250;
                srcWidth = 14;
            }

            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 54);
            x += srcWidth;
        }
    }
}
