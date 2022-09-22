import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] team;
    static int[] list;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputs(bufferedReader);

            combination(0, 0);
            System.out.println(min);

        }
    }

    private static void combination(int start, int depth) {
        if (depth == N / 2) {
            compareMinDiff();

            return;
        }

        for (int i = start; i < N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                combination(i + 1, depth + 1);
                visit[i] = false;
            }
        }
    }

    private static void compareMinDiff() {
        int start = 0;
        int link = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visit[i] && visit[j]) {
                    start += team[i][j] + team[j][i];
                }

                if (!visit[i] && !visit[j]) {
                    link += team[i][j] + team[j][i];
                }
            }
        }

        min = Math.min(Math.abs(start - link), min);
    }

    static int min = Integer.MAX_VALUE;


    private static void inputs(BufferedReader bufferedReader) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        team = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer inputLine = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                team[i][j] = Integer.parseInt(inputLine.nextToken());
            }
        }

        list = new int[N];
        for (int i = 0; i < N; i++) {
            list[i] = i;
        }
        visit = new boolean[N];
    }
}