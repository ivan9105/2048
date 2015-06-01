package com.barleybreak.util;

import com.framework.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Иван on 31.05.2015.
 */
public class Settings {
    public static boolean soundEnabled = true;
    //Todo избавиться от хардкода
    public static String[] results = {"32 - 12:32", "124 - 44:00", "15 - 01:13", "122 - 12:12", "14 - 00:12"};

    public static void load(FileIO files) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    files.readFile(".bb")));
            soundEnabled = Boolean.parseBoolean(in.readLine());
            for (int i = 0; i < 5; i++) {
                results[i] = in.readLine();
            }
        } catch (IOException | NumberFormatException ignored) {
            // :( It's ok we have defaults
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException ignored) {
            }
        }
    }

    public static void save(FileIO files) {
        BufferedWriter out = null;
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
        }
    }
}
