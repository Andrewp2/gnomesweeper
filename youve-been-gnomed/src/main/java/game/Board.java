package game;

import java.util.Random;

public class Board {

    private static final int MINE_VALUE = -1;
    private static final int MIN_PROBABILITY = 0;
    private static final int MAX_PROBABILITY = 100;
    private int[][] squares;

    private static String[] strings = {":zero:", ":one:", ":two:", ":three:", ":four:", ":five:", ":six:", ":seven:", ":eight:"};

    public Board(int x, int y) {
        squares = new int[x][y];
    }

    public void generate(int probability) {
        if (probability < MIN_PROBABILITY || probability > MAX_PROBABILITY) {
            throw new IllegalArgumentException();
        } else {
            Random rand = new Random();
            for (int x = 0; x < squares.length; x++) {
                for (int y = 0; y < squares[0].length; y++) {
                    if (rand.nextInt(MAX_PROBABILITY) < probability) {
                        squares[x][y] = MINE_VALUE;
                        increment(x, y);
                    }
                }
            }
        }
    }

    private class Pair<L, R> {
        L left;
        R right;

        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }

        public L getLeft() {
            return left;
        }

        public void setLeft(L left) {
            this.left = left;
        }

        public R getRight() {
            return right;
        }

        public void setRight(R right) {
            this.right = right;
        }
    }

    private void increment(int x, int y) {
        if (x < 0 || x >= squares.length || y < 0 || y >= squares[0].length) {
            throw new IllegalArgumentException();
        }
        Pair<Integer, Integer> xCoords = new Pair<>(x-1, x+1);
        Pair<Integer, Integer> yCoords = new Pair<>(y-1, y+1);
        if (x == 0) {
            //handle being on the left side of the board
            xCoords.left = 0;
        }
        if (x == squares.length - 1) {
            //handle being on the right side of the board
            xCoords.right = squares.length-1;
        }
        if (y == 0) {
            //handle being on the top of the board
            yCoords.left = 0;
        }
        if (y == squares[0].length - 1) {
            //handle being on bottom of the board
            yCoords.right = squares[0].length - 1;
        }
        iterateOverCoordinates(xCoords, yCoords);
    }

    private void iterateOverCoordinates(Pair<Integer, Integer> xCoords, Pair<Integer, Integer> yCoords) {
        iterateOverNeighbors(xCoords.left, yCoords.left, xCoords.right, yCoords.right);
    }

    private void iterateOverNeighbors(int xStart, int yStart, int xFinish, int yFinish) {
        for (int x2 = xStart; x2 <= xFinish; x2++) {
            for (int y2 = yStart; y2 <= yFinish; y2++) {
                //Check to see that we aren't accidentally incrementing a mine square
                if (squares[x2][y2] != MINE_VALUE) {
                    squares[x2][y2]++;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < squares.length; x++) {
            for (int y = 0; y < squares[0].length; y++) {
                if (squares[x][y] > 0) {
                    sb.append(squares[x][y]);
                } else if (squares[x][y] == 0) {
                    sb.append('.');
                } else {
                    sb.append('X');
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public String toDiscordString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hohoho me old chum, ready to play again?\n");
        for (int x = 0; x < squares.length; x++) {
            for (int y = 0; y < squares[0].length; y++) {
                sb.append("||");
                if (squares[x][y] >= 0) {
                    sb.append(strings[squares[x][y]]);
                } else {
                    sb.append("<:gnome:542176480315179048>");
                }
                sb.append("||");
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}