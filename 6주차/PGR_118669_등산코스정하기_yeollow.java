import java.util.*;

public class Solution {
    private static List<Node>[] adjList;
    private static int[] intensities;

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        inits(n, paths, gates, summits);

        dijkstra(gates);
        return getMinIntensity(summits);
    }

    private static int[] getMinIntensity(int[] summits) {
        Arrays.sort(summits);

        int idx = Integer.MAX_VALUE;
        int intensity = Integer.MAX_VALUE;
        for (int summit : summits) {
            if (intensities[summit] < intensity) {
                idx = summit;
                intensity = intensities[summit];
            }
        }

        return new int[]{idx, intensity};
    }

    private static void dijkstra(int[] gates) {
        Queue<Node> queue = new LinkedList<>();

        for (int gate : gates) {
            queue.add(new Node(gate, 0));
            intensities[gate] = 0;
        }

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.getIntensity() < intensities[cur.getIdx()]) {
                continue;
            }

            for (Node next : adjList[cur.getIdx()]) {
                int minIntensity = Math.max(next.getIntensity(), intensities[cur.getIdx()]);
                if (intensities[next.getIdx()] > minIntensity) {
                    intensities[next.getIdx()] = minIntensity;
                    queue.add(new Node(next.getIdx(), minIntensity));
                }
            }
        }
    }

    private static void inits(int n, int[][] paths, int[] gates, int[] summits) {
        adjList = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int intensity = path[2];

            if (isContains(from, gates) || isContains(to, summits)) {
                adjList[from].add(new Node(to, intensity));
            } else if (isContains(to, gates) || isContains(from, summits)) {
                adjList[to].add(new Node(from, intensity));
            } else {
                adjList[from].add(new Node(to, intensity));
                adjList[to].add(new Node(from, intensity));
            }
        }

        intensities = new int[n + 1];
        Arrays.fill(intensities, Integer.MAX_VALUE);
    }

    private static boolean isContains(int idx, int[] nodes) {
        for (int node : nodes) {
            if (idx == node) {
                return true;
            }
        }

        return false;

//        return Arrays.stream(nodes).anyMatch(node -> node == idx);
    }

    static class Node{
        private final int idx;
        private final int intensity;

        public Node(int idx, int intensity) {
            this.idx = idx;
            this.intensity = intensity;
        }

        public int getIdx() {
            return idx;
        }

        public int getIntensity() {
            return intensity;
        }
    }
}