package com.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileIO {
    public InputStream readAsset(String fileName) throws IOException;

    public String readFile(String fileName) throws IOException;

    public OutputStream writeFile(String fileName) throws IOException;
}
