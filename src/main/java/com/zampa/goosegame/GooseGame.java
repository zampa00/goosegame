package com.zampa.goosegame;

import com.zampa.goosegame.gamelogic.Game;
import com.zampa.goosegame.gamelogic.Player;

import java.util.HashMap;
import java.util.Map;

public class GooseGame implements Game {

    private Map<String, Player> players;


    public GooseGame() {
        players = new HashMap<>();
    }

    @Override
    public boolean addPlayer(String playerName) {
        Player newPlayer = new Player(playerName);

        if (!hasPlayer(playerName)) {
            players.put(playerName, newPlayer);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean hasPlayer(String playerName) {
        return players.containsKey(playerName);
    }
}
