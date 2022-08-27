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
    static List<Node>[] adjList;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputs(bufferedReader);

            if (N == 1) {
                System.out.println(0);
                return;
            }
            visit = new boolean[N + 1];
            int x = getFarthestNode(1).getVertex();
            visit = new boolean[N + 1];
            int weight = getFarthestNode(x).getWeight();

            System.out.println(weight);
        }
    }

    private static Node getFarthestNode(int start) {
        int vertex = 0;
        int weightSum = 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, 0));
        visit[start] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (Node node : adjList[cur.getVertex()]) {
                if (!visit[node.getVertex()]) {
                    int sum = cur.getWeight() + node.getWeight();
                    if (sum > weightSum) {
                        weightSum = sum;
                        vertex = node.getVertex();
                    }
                    queue.add(new Node(node.getVertex(), sum));
                    visit[node.getVertex()] = true;
                }
            }
        }

        return new Node(vertex, weightSum);
    }

    private static void inputs(BufferedReader bufferedReader) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer inputLine = new StringTokenizer(bufferedReader.readLine());
            int parent = Integer.parseInt(inputLine.nextToken());
            int child = Integer.parseInt(inputLine.nextToken());
            int weight = Integer.parseInt(inputLine.nextToken());

            adjList[parent].add(new Node(child, weight));
            adjList[child].add(new Node(parent, weight));
        }
    }

    static class Node {
        private final int vertex;
        private final int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public int getVertex() {
            return vertex;
        }

        public int getWeight() {
            return weight;
        }
    }
}