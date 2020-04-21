package com.zampa.goosegame.io;

public interface IOutput {

    public String listPlayers(String playerNames);
    
    public String playerExists(String playerName);
    
    public String playerRoll(String playerName, int die1, int die2);
    
    public String playerMove(String playerName, int from, int to);
    
    public String playerJump(String playerName, int to);
    
    public String playerMoveAgain(String playerName, int to);
    
    public String playerBounce(String playerName, int to);
    
    public String playerWin(String playerName);
    
    public String playerPrank(String playerPranked, int returnsTo);
        
}
