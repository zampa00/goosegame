package com.zampa.goosegame.io;

import java.util.Arrays;
import java.util.List;

public class Command {

    String name;
    List<String> parameters;

    public Command(String commandString) {
        String[] i = commandString.split("[\\s,]+");
        this.name = i[0];
        this.parameters = Arrays.asList(i).subList(1, i.length);
    }

    public String getName() {
        return name;
    }

    public List<String> getParameters() {
        return parameters;
    }
}
