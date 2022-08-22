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
    static List<Integer>[] adjList;
    static int[] degrees;
    static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputs(bufferedReader);

            topologicalSort();
            System.out.println(answer);
        }
    }

    private static void topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();
        addZeroDegrees(queue);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Integer next : adjList[cur]) {
                if (--degrees[next] == 0) {
                    queue.add(next);
                }
            }
            answer.append(cur).append(" ");
        }
    }

    private static void addZeroDegrees(Queue<Integer> queue) {
        for (int i = 1; i <= N; i++) {
            if (degrees[i] == 0) {
                queue.add(i);
            }
        }
    }

    private static void inputs(BufferedReader bufferedReader) throws IOException {
        StringTokenizer inputLine = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(inputLine.nextToken());
        M = Integer.parseInt(inputLine.nextToken());

        degrees = new int[N + 1];
        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        answer = new StringBuilder();
        for (int i = 0; i < M; i++) {
            inputLine = new StringTokenizer(bufferedReader.readLine());

            int from = Integer.parseInt(inputLine.nextToken());
            int to = Integer.parseInt(inputLine.nextToken());
            adjList[from].add(to);
            degrees[to]++;
        }
    }
}