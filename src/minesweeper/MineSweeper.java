package minesweeper;

import java.util.*;

public class MineSweeper {
    private int[][] field = new int[10][10];
    private boolean[][] visible = new boolean[10][10];
    private Random random = new Random();

    private static final int MINE = -1;

    public void setupField() {
        int minesToPlace = 99;
        while (minesToPlace > 0) {
            int row = random.nextInt(10);
            int col = random.nextInt(10);
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
                if (i >= 0 && i < 10 && j >= 0 && j < 10 && field[i][j] != MINE) {
                    field[i][j]++;
                }
            }
        }
    }

    public void displayBoard() {
        System.out.println("====Minesweeper======================================");
        System.out.println("=====================================================");
        System.out.print("===");
        for (int col = 0; col < 10; col++) {
            System.out.print("| " + col + " |");
        }
        System.out.println();
        for (int row = 0; row < 10; row++) {
            System.out.print(row + " |");
            for (int col = 0; col < 10; col++) {
                if (visible[row][col]) {
                    if (field[row][col] == MINE) {
                        System.out.print("[ * ]");
                    } else {
                        System.out.print("[ " + field[row][col] + " ]");
                    }
                } else {
                    System.out.print("[ ■ ]");
                }
            }
            System.out.println();
        }
    }

    public void revealCell(int row, int col) {
        if (row < 0 || row >= 10 || col < 0 || col >= 10 || visible[row][col]) {
            return;
        }
        visible[row][col] = true;
    }

    public boolean checkWin() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (!visible[row][col] && field[row][col] != MINE) {
                    return false;
                }
            }
        }
        return true;
    }

    public void startGame() {
        System.out.println("=====================================================");
        // setupField(); // Comment out this line to go to debug mode.

        Scanner sc = new Scanner(System.in);
        while (true) {
            displayBoard();
            System.out.print("Enter Row (X) Number: ");
            int row = sc.nextInt();
            System.out.print("Enter Column (Y) Number: ");
            int col = sc.nextInt();

            if (row < 0 || row >= 10 || col < 0 || col >= 10) {
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
        for (int col = 0; col < 10; col++) {
            System.out.print("  " + col + "  ");
        }
        System.out.println();
        for (int row = 0; row < 10; row++) {
            System.out.print(row + " |");
            for (int col = 0; col < 10; col++) {
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
        game.setupField();
        // Make sure to call setupField() to set up the mines before using
        // debugDisplay().
        game.debugDisplay();
        // Display the game board with mines on top of the game.
        game.startGame(); // Start playing the Minesweeper game.
    }
}
