import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3665_최종순위_ssu {
    static int T, N, M, inDegree[];
    static boolean adjMatrix[][];

    public static void main(String[] args) throws NumberFormatException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++){
            N = Integer.parseInt(br.readLine());
            inDegree = new int[N+1];
            adjMatrix = new boolean[N+1][N+1];
            StringTokenizer st =new StringTokenizer(br.readLine());
            for (int i=0;i<N;i++) {
                int now = Integer.parseInt(st.nextToken());
                inDegree[now] = i;
                for (int j=1;j<=N;j++) {
                    if (j!=now && !adjMatrix[j][now]) adjMatrix[now][j] = true;
                }
            }

            M = Integer.parseInt(br.readLine());
            for (int i=0;i<M;i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                swap(from, to);
            }

            sb.append(topologySort()).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void swap(int from, int to) {
        if (!adjMatrix[from][to]) {
            adjMatrix[from][to] = true;
            adjMatrix[to][from] = false;
            inDegree[from]--;
            inDegree[to]++;
        }
        else {
            adjMatrix[from][to] = false;
            adjMatrix[to][from] = true;
            inDegree[from]++;
            inDegree[to]--;
        }
    }

    public static String topologySort() {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i=1;i<=N;i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        for (int i=1;i<=N;i++) {
            if (queue.isEmpty()) return "IMPOSSIBLE";
            else if (queue.size()>1) return "?";
            int current = queue.poll();
            sb.append(current).append(" ");

            for (int j=1;j<=N;j++) {
                if (adjMatrix[current][j]) {
                    adjMatrix[current][j] = false;
                    if (--inDegree[j] == 0) queue.offer(j);
                }
            }
        }
        return sb.toString();
    }
}