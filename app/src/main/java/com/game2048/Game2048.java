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
    private float threshold = 50;

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

                float differenceX = x1 - x2;
                float differenceY = y1 - y2;

                Direction direction;
                if (y1 < y2 && Math.abs(differenceY) > Math.abs(differenceX)) {
                    direction = Direction.BOTTOM;
                } else if (y2 < y1 && Math.abs(differenceY) > Math.abs(differenceX)) {
                    direction = Direction.UP;
                } else if (x1 < x2 && Math.abs(differenceX) > Math.abs(differenceY)) {
                    direction = Direction.RIGHT;
                } else if (x2 < x1 && Math.abs(differenceX) > Math.abs(differenceY)) {
                    direction = Direction.LEFT;
                } else if (differenceX == differenceY) {
                    //Todo нужно достать разрешение экрана и разбираться в какой части экрана была нажата кнопка
                    //Todo можно по проценту от максимального разрешения узнавать в какой части экрана нажато
                }

                /*
                по основному расхождение по х или по у
                         х1       у1          х2          у2
                down (636.4107, 241.87402) (623.4227, 1641.1451)
                up (574.4681, 1751.0879) (542.4977, 69.96356)
                right (209.80574, 986.4862) (1034.838, 908.94574)
                left (938.13135, 1196.3768) (112.89547, 1210.3696)


                 */


                x1 = 0;
                x2 = 0;
                y1 = 0;
                y2 = 0;

                //Todo здесь нужно определять в какую сторону мы нажали кнопку
            }

//            ((GameScreen) getCurrentScreen()).update();
        }
        return super.doTouch(view, event);
    }
}
