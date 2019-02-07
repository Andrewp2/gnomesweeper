import java.util.Random;

public class Board {
    private int[][] squares;

    private static String[] strings = {":zero:",":one:", ":two:",":three:",":four:",":five:",":six:",":seven:",":eight:"};

    public Board(int x, int y) {
        squares = new int[x][y];
    }

    public void generate(int probability) {
        if(probability < 0 || probability > 100) {
            throw new IllegalArgumentException();
        } else {
            Random rand = new Random();
            for(int x = 0; x < squares.length; x++) {
                for(int y = 0; y < squares[0].length; y++) {
                    if(rand.nextInt(100) < probability) {
                        squares[x][y] = -1;
                        increment(x,y);
                    }
                }
            }
        }
    }

    private void increment(int x, int y) {
        if(x < 0 || x >= squares.length || y < 0 || y >= squares[0].length) {
            throw new IllegalArgumentException();
        }
        int xStart = x-1;
        int yStart = y-1;
        int xFinish = x+1;
        int yFinish = y+1;
        if(x == 0) {
            xStart = 0;
            xFinish = 1;
            iterateOverYCoordinate(x, y, xStart, yStart, xFinish, yFinish);
        } else if (x == squares.length - 1) {
            xStart = x-1;
            xFinish = squares.length-1;
            iterateOverYCoordinate(x, y, xStart, yStart, xFinish, yFinish);
        } else {
            iterateOverYCoordinate(x, y, xStart, yStart, xFinish, yFinish);
        }


    }

    private void iterateOverYCoordinate(int x, int y, int xStart, int yStart, int xFinish, int yFinish) {
        if(y == 0) {
            yStart = 0;
            yFinish = 1;
            for(int x2 = xStart; x2 <= xFinish; x2++) {
                for(int y2 = yStart; y2 <= yFinish; y2++) {
                    if(squares[x2][y2] != -1) {
                        squares[x2][y2]++;
                    }
                }
            }
        } else if (y == squares[0].length- 1) {
            yStart = y-1;
            yFinish = squares[0].length -1;
            for(int x2 = xStart; x2 <= xFinish; x2++) {
                for(int y2 = yStart; y2 <= yFinish; y2++) {
                    if(squares[x2][y2] != -1) {
                        squares[x2][y2]++;
                    }
                }
            }
        } else {
            for(int x2 = xStart; x2 <= xFinish; x2++) {
                for(int y2 = yStart; y2 <= yFinish; y2++) {
                    if(squares[x2][y2] != -1) {
                        squares[x2][y2]++;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int x = 0; x < squares.length; x++) {
            for(int y = 0; y < squares[0].length; y++) {
                if(squares[x][y] > 0) {
                    sb.append(squares[x][y]);
                }
                else if(squares[x][y] == 0) {
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
        for(int x = 0; x < squares.length; x++) {
            for(int y = 0; y < squares[0].length; y++) {
                sb.append("||");
                if(squares[x][y] > 0) {
                    sb.append(strings[squares[x][y]]);
                }
                else if(squares[x][y] == 0) {
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