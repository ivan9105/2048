package com.game2048;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.framework.Screen;
import com.framework.impl.AndroidGame;
import com.game2048.screen.GameScreen;
import com.game2048.screen.LoadingScreen;

/**
 * Created by Иван on 22.06.2015.
 */
public class Game2048 extends AndroidGame {
    public static final String LOG_TAG = "myTag";

    float x1 = 0, x2 = 0, y1 = 0, y2 = 0;

    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean doTouch(View view, MotionEvent event) {
        if (getCurrentScreen() instanceof GameScreen) {
            //event
            int actionMask = event.getActionMasked();

            switch (actionMask) {
                case MotionEvent.ACTION_DOWN: // первое касание
                    x1 = event.getX();
                    y1 = event.getY();
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                    x2 = event.getX();
                    y2 = event.getY();
                    break;
            }

            if (x1 > 0 && y1 > 0 && x2 > 0 && y2 > 0) {
                Log.d(LOG_TAG, String.format("(%s, %s) (%s, %s)", x1, y1, x2, y2));
                x1 = 0;
                x2 = 0;
                y1 = 0;
                y2 = 0;
            }

//            ((GameScreen) getCurrentScreen()).update();
        }
        return super.doTouch(view, event);
    }
}
