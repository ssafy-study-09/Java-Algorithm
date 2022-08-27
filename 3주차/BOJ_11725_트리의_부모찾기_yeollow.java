import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] adjList;
    static boolean[] visit;
    static int[] answer;
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputs(bufferedReader);

            bfs(1);
//            dfs(1);

            printAnswer();
        }
    }

    private static void printAnswer() {
        for (int i = 2; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }

    private static void dfs(int node) {
        visit[node] = true;

        for (Integer next : adjList[node]) {
            if (!visit[next]) {
                answer[next] = node;
                dfs(next);
            }
        }
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Integer next : adjList[cur]) {
                if (!visit[next]) {
                    answer[next] = cur;
                    visit[next]  = true;
                    queue.offer(next);
                }
            }
        }
    }

    private static void inputs(BufferedReader bufferedReader) throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());
        answer = new int[N + 1];
        visit = new boolean[N + 1];
        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer inputLine = new StringTokenizer(bufferedReader.readLine());

            int from = Integer.parseInt(inputLine.nextToken());
            int to = Integer.parseInt(inputLine.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }
    }
}