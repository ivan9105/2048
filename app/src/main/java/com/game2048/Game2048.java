package com.game2048;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.framework.Screen;
import com.framework.impl.AndroidGame;
import com.game2048.model.enums.Direction;
import com.game2048.screen.GameScreen;
import com.game2048.screen.LoadingScreen;

/**
 * Created by Иван on 22.06.2015.
 */
public class Game2048 extends AndroidGame {
    public static final String LOG_TAG = "myTag";

    private float x1 = 0, x2 = 0, y1 = 0, y2 = 0;
    //if move length < threshold direction == screen part
    //threshold percent from screenHeight, screenWidth
    private double thresholdX = 3, thresholdY = 1.5;

    private int screenWidth, screenHeight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager)
                getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
    }

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
            GameScreen gameScreen = (GameScreen) getCurrentScreen();

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
                float differenceX = x1 - x2;
                float differenceY = y1 - y2;
                float percentValueX = (float) ((screenWidth / 100) * thresholdX);
                float percentValueY = (float) ((screenHeight / 100) * thresholdY);

                Direction direction = null;
                if (y1 < y2 && Math.abs(differenceY) > Math.abs(differenceX)
                                                                  && Math.abs(differenceY) > percentValueY) {
                    direction = Direction.BOTTOM;
                } else if (y2 < y1 && Math.abs(differenceY) > Math.abs(differenceX)
                                                                  && Math.abs(differenceY) > percentValueY) {
                    direction = Direction.UP;
                } else if (x1 < x2 && Math.abs(differenceX) > Math.abs(differenceY)
                                                                  && Math.abs(differenceX) > percentValueX) {
                    direction = Direction.RIGHT;
                } else if (x2 < x1 && Math.abs(differenceX) > Math.abs(differenceY)
                                                                  && Math.abs(differenceX) > percentValueX) {
                    direction = Direction.LEFT;
                }
                x1 = 0;
                x2 = 0;
                y1 = 0;
                y2 = 0;
                if (direction != null) {
                    if (!gameScreen.isGameOver()) {
                        ((GameScreen) getCurrentScreen()).update(direction);
                    }
                }
            }
        }
        return super.doTouch(view, event);
    }
}
