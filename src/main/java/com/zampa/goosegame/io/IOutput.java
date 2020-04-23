package com.zampa.goosegame.io;

import java.io.IOException;

public interface IOutput {

    public void append(String info);

    public void output() throws IOException;
        
}
