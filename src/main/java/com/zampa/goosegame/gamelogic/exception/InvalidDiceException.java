package com.zampa.goosegame.gamelogic.exception;

public class InvalidDiceException extends Exception {

    public InvalidDiceException() {
        super();
    }

    public InvalidDiceException(String message) {
        super(message);
    }

    public InvalidDiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDiceException(Throwable cause) {
        super(cause);
    }
}
