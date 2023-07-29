# Project: Minesweeper

## Simply Run Java to play the game.

### To Run Debug Mode & Reveal the Mine Location, Uncomment this lines

```
game.setupField();
game.debugDisplay();
```

on the java main() method AND comment

```
setupField();
```

On the StartGame Method.

To prevent Setting Up the Field Twice , Because Setting up the field twice will make the mine move to another place as the debug mode mine location only exist on the first setup. and the second setup will reset the mine location.

### To Change the Number of Mine,

Change minesToPlace int data in setupField() method.

## To Do List

1. Configuration for Grid Size.
2. Discovering an empty square should reveal all squares around it, and cascade into other nearby empty squares
3. Flag Method?

## MVP

- Recreate a simplified version of the game Minesweeper to be played in the java console ✅︎
- The game should be able to randomly generate 10 mines in a 10x10 grid ✅︎
- The user will be able to enter a command that represents a coordinate to check a location for a mine ✅︎
- The application will display a number from 0-8 depending on how many mines surround that location ✅︎
- If the user selects a mine, the game will respond "boom!" and the game will be lost ✅︎
- If every non-mine square has been revealed, the game is won✅︎
- Render the grid to the console after every user command ✅︎

## Bonuses (optional)

Allow for the user to configure number of mines ✅︎ and grid size via a configuration.
(Difficult) Discovering an empty square should reveal all squares around it, and cascade into other nearby empty squares
