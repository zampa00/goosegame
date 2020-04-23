package com.zampa.goosegame.io;

import java.io.BufferedWriter;
import java.io.IOException;

public class CLOutput implements IOutput{

    BufferedWriter writer;
    StringBuffer buffer;

    public CLOutput(BufferedWriter writer) {
        this.writer = writer;
        buffer = new StringBuffer();
    }

    public void append(String info) {
        buffer.append(info);
    }

    public void output() throws IOException {
        writer.write(buffer.toString());
        writer.write("\n");
        writer.flush();
        buffer.setLength(0);
    }

}
