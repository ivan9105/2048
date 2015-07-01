package com.game2048;

import com.framework.Screen;
import com.framework.impl.AndroidGame;
import com.game2048.screen.LoadingScreen;

/**
 * Created by Иван on 22.06.2015.
 */
public class Game2048 extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
