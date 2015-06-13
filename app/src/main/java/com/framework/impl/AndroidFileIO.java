package com.framework.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.preference.PreferenceManager;

import com.framework.FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AndroidFileIO implements FileIO {
    Context context;
    AssetManager assets;

    public AndroidFileIO(Context context) {
        this.context = context;
        this.assets = context.getAssets();
    }

    public InputStream readAsset(String fileName) throws IOException {
        return assets.open(fileName);
    }

    public String readFile(String fileName) throws IOException {
        createTestResultIfNotExists(fileName);

        String temp="";
        try{
            FileInputStream fin = context.openFileInput(fileName);
            int c;


            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }
        }catch(Exception ignored){
        }
        return temp;
    }

    private void createTestResultIfNotExists(String fileName) throws IOException {
        try {
            File file = context.getFileStreamPath(fileName);
            if (file != null && !file.exists()) {
                String content = "32 - 12:32\n124 - 44:00\n15 - 01:13\n122 - 12:12\n14 - 00:12";
                FileOutputStream fOut = context.openFileOutput(fileName, context.MODE_WORLD_READABLE);
                fOut.write(content.getBytes());
                fOut.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream(fileName);
    }
    
    public SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
