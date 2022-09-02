import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int min;
    static int emptySpaces;

    static int[][] laboratory;
    static Virus[] viruses;
    static Virus[] active;

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputs(bufferedReader);

            if (emptySpaces == 0) {
                System.out.println(0);
                return;
            }
            selectVirusCombination(0, 0);
            System.out.println(min == Integer.MAX_VALUE ? -1 : min);
        }
    }

    private static void selectVirusCombination(int start, int depth) {
        if (depth == M) {
            spreadVirus(emptySpaces);

            return;
        }

        for (int i = start; i < viruses.length; i++) {
            active[depth] = viruses[i];
            selectVirusCombination(i + 1, depth + 1);
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static void spreadVirus(int size) {
        Queue<Virus> queue = new LinkedList<>();
        boolean[][] infected = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            Virus virus = active[i];
            infected[virus.getX()][virus.getY()] = true;
            queue.add(virus);
        }

        while (!queue.isEmpty()) {
            Virus cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = cur.getX() + dx[i];
                int nextY = cur.getY() + dy[i];

                if (isInLaboratory(nextX, nextY) && !infected[nextX][nextY]
                    && laboratory[nextX][nextY] != 1) {
                    if (laboratory[nextX][nextY] == 0 && --size == 0) {
                        min = Math.min(cur.getTime() + 1, min);

                        return;
                    }

                    infected[nextX][nextY] = true;
                    queue.add(new Virus(nextX, nextY, cur.getTime() + 1));
                }

            }
        }
    }

    private static boolean isInLaboratory(int nextX, int nextY) {
        return nextX >= 0 && nextY >= 0 && nextX < N && nextY < N;
    }

    private static void inputs(BufferedReader bufferedReader) throws IOException {
        StringTokenizer inputLine = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(inputLine.nextToken());
        M = Integer.parseInt(inputLine.nextToken());
        laboratory = new int[N][N];
        active = new Virus[M];

        List<Virus> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            inputLine = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                laboratory[i][j] = Integer.parseInt(inputLine.nextToken());

                if (laboratory[i][j] == 0) {
                    emptySpaces++;
                }
                if (laboratory[i][j] == 2) {
                    list.add(new Virus(i, j, 0));
                }
            }
        }

        viruses = list.toArray(Virus[]::new);
        min = Integer.MAX_VALUE;
    }

    static class Virus {

        private final int x;
        private final int y;
        private final int time;

        public Virus(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getTime() {
            return time;
        }
    }


}