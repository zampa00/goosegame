# Goose Game
A simple implementation of [https://github.com/xpeppers/goose-game-kata](https://github.com/xpeppers/goose-game-kata) in Java.

## Requirements
Java 1.8 and maven 3.6.

## How to run

 1. Clone this repository with git `git clone` or download it. 
 2. From inside the repository's directory, run `mvn clean package` to test and produce a jar.
 3. Run `java -cp
    .\target\goosegame-1.0-SNAPSHOT.jar com.zampa.goosegame.Main` to start the game.

## How to play

 - `add <PlayerName>` - adds <Playername\> if not already in game.
 - `move <PlayerName>` - Automatically roll dice and move.
 - `move <PlayerName> <die>, <die>` - If you prefer manually rolling
   dice.
 - `exit` - Terminate the game.