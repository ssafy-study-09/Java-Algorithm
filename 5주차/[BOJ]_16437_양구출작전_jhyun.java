import java.util.*;
import java.io.*;
public class Main{
    static int N;
    static int[] animals;
    static long[] sheep, wolf;
    static List<Integer> graph[];
    static long dfs(int cur){
        long w = wolf[cur], s = sheep[cur];
        long totalSheep = s;
        for(Integer next: graph[cur]){
            totalSheep += dfs(next);
        }
        return totalSheep < w ? 0 : totalSheep - w;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        animals = new int[N + 1];
        graph = new List[N + 1];
        sheep = new long[N + 1];
        wolf = new long[N + 1];
        for(int i = 0; i < graph.length; i++)
            graph[i] = new ArrayList<>();
        for(int i = 2; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            int cnt = Integer.parseInt(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());
            graph[parent].add(i);
            if(order.equals("S")){
                sheep[i] = cnt;
            }
            else{
                wolf[i] = cnt;
            }
        }
        long answer = dfs(1);
        System.out.println(answer);
    }
}
