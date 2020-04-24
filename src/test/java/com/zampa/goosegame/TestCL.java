package com.zampa.goosegame;

import com.zampa.goosegame.gamelogic.Game;
import com.zampa.goosegame.gamelogic.GooseGame;
import com.zampa.goosegame.gamelogic.exception.PlayerNotFoundException;
import com.zampa.goosegame.io.CLInput;
import com.zampa.goosegame.io.CLOutputLogger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCL {

    Game gooseGame;
    CLInput cli;

    @Before
    public void setUp() {
        CLOutputLogger.init();
        this.gooseGame = new GooseGame();
        this.cli = new CLInput(gooseGame);
    }

    @Test
    public void testAddPlayer() {
        String command = "add Pippo";
        boolean commandResult = cli.parse(command);
        Assert.assertTrue("Unable to add a player.", commandResult);
        Assert.assertTrue("Command 'add Pippo' returned success, but player is not in the game.",
                this.gooseGame.hasPlayer("Pippo"));
    }

    @Test
    public void testAddPlayerTwice() {
        String command = "add Pippo";
        cli.parse(command);
        boolean commandResult = cli.parse(command);
        Assert.assertFalse("It's possible to add a player twice.", commandResult);
    }

    @Test
    public void testMovement() throws PlayerNotFoundException {
        cli.parse("add Pippo");
        String command = "move Pippo";
        boolean commandResult = cli.parse(command);
        Assert.assertTrue("Unable to move a player.", commandResult);
        Assert.assertNotEquals("Command 'move Pippo' returned success, but player did not move.",
                0,
                gooseGame.getPlayer("Pippo").getCurrentSlot().getNumber());
    }

    @Test
    public void testCustomMovement() throws PlayerNotFoundException {
        cli.parse("add Pippo");
        String command = "move Pippo 1, 2";
        boolean commandResult = cli.parse(command);
        Assert.assertTrue("Unable to move a player.", commandResult);
        Assert.assertEquals("Command 'move Pippo' returned success, but player did not move.",
                3,
                gooseGame.getPlayer("Pippo").getCurrentSlot().getNumber());
    }

    @Test
    public void testMoveOutOfRange() {
        cli.parse("add Pippo");
        String command = "move Pippo 7, 2";
        boolean commandResult = cli.parse(command);
        Assert.assertFalse("Player moved out of dice range.", commandResult);
    }

    @Test
    public void testMoveNaN() {
        cli.parse("add Pippo");
        String command = "move Pippo o, 2";
        boolean commandResult = cli.parse(command);
        Assert.assertFalse("Game parsed NaN.", commandResult);
    }

    @Test
    public void testMoveArguments() {
        cli.parse("add Pippo");
        String command = "move Pippo 2";
        boolean commandResult = cli.parse(command);
        Assert.assertFalse("Game parsed an invalid number of parameters for 'move'.", commandResult);
    }    @Test
    public void testMoveArguments2() {
        cli.parse("add Pippo");
        String command = "move Pippo 2 3 4";
        boolean commandResult = cli.parse(command);
        Assert.assertFalse("Game parsed an invalid number of parameters for 'move'.", commandResult);
    }

    @Test
    public void testAddArguments() {
        boolean commandResult = cli.parse("add");
        Assert.assertFalse("Game parsed an invalid number of parameters for 'add'.", commandResult);
    }
    @Test
    public void testAddArguments2() {
        boolean commandResult = cli.parse("add Pippo, Pluto");
        Assert.assertFalse("Game parsed an invalid number of parameters for 'add'.", commandResult);
    }
}
