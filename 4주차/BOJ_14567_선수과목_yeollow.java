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
    static int[] answer;

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputs(bufferedReader);

            topologicalSorting();
            for (int i = 1; i <= N; i++) {
                System.out.print(answer[i] + 1 + " ");
            }
        }
    }

    private static void topologicalSorting() {
        Queue<Class> queue = new LinkedList<>();
        addZeroDegree(queue);
        while (!queue.isEmpty()) {
            Class cur = queue.poll();

            for (Integer next : adjList[cur.getIdx()]) {
                if (--degrees[next] == 0) {
                    answer[next] = cur.getSemester() + 1;
                    queue.add(new Class(next, cur.getSemester() + 1));
                }
            }
        }
    }

    private static void addZeroDegree(Queue<Class> queue) {
        for (int i = 1; i <= N; i++) {
            if (degrees[i] == 0) {
                queue.add(new Class(i, 0));
            }
        }
    }

    private static void inputs(BufferedReader bufferedReader) throws IOException {
        StringTokenizer inputLine = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(inputLine.nextToken());
        M = Integer.parseInt(inputLine.nextToken());
        degrees = new int[N + 1];
        adjList = new List[N + 1];
        answer = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            inputLine = new StringTokenizer(bufferedReader.readLine());

            int A = Integer.parseInt(inputLine.nextToken());
            int B = Integer.parseInt(inputLine.nextToken());
            adjList[A].add(B);
            degrees[B]++;
        }
    }

    static class Class {

        private final int idx;
        private final int semester;

        public Class(int idx, int semester) {
            this.idx = idx;
            this.semester = semester;
        }

        public int getIdx() {
            return idx;
        }

        public int getSemester() {
            return semester;
        }
    }

}