import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R;
    static int C;
    static int N;
    static int[] cols;
    static char[][] board;
    static Stack<Coordinate>[] cache;
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputs(bufferedReader);

            for (int col : cols) {
//                cache[col]의 board coordinate 위치가 O가 아닐때 까지
                while (!cache[col].isEmpty() && board[cache[col].peek().getX()][cache[col].peek().getY()] == 'O') {
                    cache[col].pop();
                }

                if (cache[col].isEmpty()) {
                    dropStone(0, col, col);
                } else {
                    Coordinate peek = cache[col].peek();
                    dropStone(peek.getX(), peek.getY(), col);
                }
            }

            printBoard();
        }
    }

    private static void dropStone(int x, int y, int col) {
        while (x < R - 1 && board[x + 1][y] != 'X') {
            if (board[x + 1][y] == 'O') {
                if (isInBoard(x + 1, y - 1) && isEmpty(x, y - 1)) {
                    x++;
                    y--;
                } else if (isInBoard(x + 1, y + 1) && isEmpty(x, y + 1)) {
                    x++;
                    y++;
                } else {
                    break;
                }
            } else {
                x++;
            }

            cache[col].push(new Coordinate(x, y));
        }

        board[x][y] = 'O';
    }

    private static boolean isEmpty(int x, int y) {
        return board[x][y] == '.' && board[x + 1][y] == '.';
    }

    private static boolean isInBoard(int x, int y) {
        return x >= 0 && y >= 0 && x < R && y < C;
    }

    private static void printBoard() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                answer.append(board[i][j]);
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }

    private static void inputs(BufferedReader bufferedReader) throws IOException {
        StringTokenizer inputLine = new StringTokenizer(bufferedReader.readLine());
        R = Integer.parseInt(inputLine.nextToken());
        C = Integer.parseInt(inputLine.nextToken());

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            char[] rows = bufferedReader.readLine().toCharArray();
            System.arraycopy(rows, 0, board[i], 0, C);
        }

        cache = new Stack[C];
        for (int i = 0; i < C; i++) {
            cache[i] = new Stack<>();
        }

        N = Integer.parseInt(bufferedReader.readLine());
        cols = new int[N];
        for (int i = 0; i < N; i++) {
            cols[i] = Integer.parseInt(bufferedReader.readLine()) - 1;
        }
    }

    static class Coordinate {
        private final int x;
        private final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
