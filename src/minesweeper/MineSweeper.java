package minesweeper;

import java.util.*;

public class MineSweeper {
    private int magicGridNumber = 30;
    private int[][] field = new int[magicGridNumber][magicGridNumber];
    private boolean[][] visible = new boolean[magicGridNumber][magicGridNumber];
    private Random random = new Random();

    private static final int MINE = -1;

    public void setupField() {
        int minesToPlace = 10;
        while (minesToPlace > 0) {
            int row = random.nextInt(magicGridNumber);
            int col = random.nextInt(magicGridNumber);
            if (field[row][col] != MINE) {
                field[row][col] = MINE;
                incrementNeighborCounts(row, col);
                minesToPlace--;
            }
        }
    }

    private void incrementNeighborCounts(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < magicGridNumber && j >= 0 && j < magicGridNumber && field[i][j] != MINE) {
                    field[i][j]++;
                }
            }
        }
    }

    public void displayBoard() {
        System.out.println("====Minesweeper======================================");
        System.out.println("=====================================================");
        System.out.print("===");
        for (int col = 0; col < magicGridNumber; col++) {
            if (col > 9) {
                System.out.print("| " + col + "|");
            } else {
                System.out.print("| " + col + " |");
            }
        }
        System.out.println();
        for (int row = 0; row < magicGridNumber; row++) {
            if (row > 9) {
                System.out.print(row + "|");
            } else {
                System.out.print(row + " |");
            }
            for (int col = 0; col < magicGridNumber; col++) {
                if (visible[row][col]) {
                    if (field[row][col] == MINE) {
                        System.out.print("[ * ]");
                    } else {
                        System.out.print("[ " + (field[row][col] == 0 ? " " : field[row][col]) + " ]");
                    }
                } else {
                    System.out.print("[ ■ ]");
                }
            }
            System.out.println();
        }
    }

    public void revealCell(int row, int col) {
        if (row < 0 || row >= magicGridNumber || col < 0 || col >= magicGridNumber || visible[row][col]) {
            return;
        }

        visible[row][col] = true;

        if (field[row][col] == 0) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    revealCell(i, j);
                }
            }
        }
    }

    public boolean checkWin() {
        for (int row = 0; row < magicGridNumber; row++) {
            for (int col = 0; col < magicGridNumber; col++) {
                if (!visible[row][col] && field[row][col] != MINE) {
                    return false;
                }
            }
        }
        return true;
    }

    public void startGame() {
        System.out.println("=====================================================");
        setupField(); // Comment out this line to go to debug mode.

        Scanner sc = new Scanner(System.in);
        while (true) {
            displayBoard();
            System.out.print("Enter Row (X) Number: ");
            int row = sc.nextInt();
            System.out.print("Enter Column (Y) Number: ");
            int col = sc.nextInt();

            if (row < 0 || row >= magicGridNumber || col < 0 || col >= magicGridNumber) {
                System.out.println("Incorrect Input!");
                continue;
            }

            if (field[row][col] == MINE) {
                visible[row][col] = true;
                displayBoard();
                System.out.println("!!! You stepped on a mine !!!\nGame Over!");
                break;
            } else {
                revealCell(row, col);
            }

            if (checkWin()) {
                displayBoard();
                System.out.println("Congratulations! You won!");
                sc.close();
                break;
            }
        }
    }

    public void debugDisplay() {
        System.out.println("=== Minesweeper (Debug Mode) ===");
        System.out.print("   ");
        for (int col = 0; col < magicGridNumber; col++) {
            System.out.print("  " + col + "  ");
        }
        System.out.println();
        for (int row = 0; row < magicGridNumber; row++) {
            System.out.print(row + " |");
            for (int col = 0; col < magicGridNumber; col++) {
                if (field[row][col] == MINE) {
                    System.out.print("[ * ]");
                } else if (visible[row][col]) {
                    System.out.print("[ " + field[row][col] + " ]");
                } else {
                    System.out.print("[ ■ ]");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MineSweeper game = new MineSweeper();
        // game.setupField();
        // Make sure to call setupField() to set up the mines before using
        // debugDisplay().
        // game.debugDisplay();
        // Display the game board with mines on top of the game.
        game.startGame(); // Start playing the Minesweeper game.
    }
}
