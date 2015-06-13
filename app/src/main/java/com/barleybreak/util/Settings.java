package com.barleybreak.util;

import android.util.Log;

import com.framework.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Иван on 31.05.2015.
 */
public class Settings {
    public static boolean soundEnabled = true;
    public static String[] results = new String[5];

    public static void load(FileIO files) {
        BufferedReader in = null;
        try {
            String s = files.readFile("highScore.txt");
            results = s.split("\n");
        } catch (IOException | NumberFormatException ignored) {
            Log.e("TAG", ignored.getMessage());
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException ignored) {
            }
        }
    }

    public static void save(FileIO files) {
        /*BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    files.writeFile(".bb")));
            out.write(Boolean.toString(soundEnabled));
            for (int i = 0; i < 5; i++) {
                out.write(results[i]);
            }
        } catch (IOException ignored) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException ignored) {
            }
        }*/
        //Todo переписать убрать ориентацию
    }
}
