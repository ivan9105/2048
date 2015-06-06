package com.barleybreak;

import com.barleybreak.screen.LoadingScreen;
import com.framework.Screen;
import com.framework.impl.AndroidGame;


public class BarleyBreakGame extends AndroidGame {
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}

