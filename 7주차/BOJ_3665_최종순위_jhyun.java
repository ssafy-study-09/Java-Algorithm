import java.io.*;
import java.util.*;
public class Main {
    static int N, M, TC;
    static int[] arr;
    static Set<Integer> graph[];
    static int[] indegree, rank;
    static boolean isImpossible;
    static Queue<Integer> q;
    static void topologySort(){
        int cnt = 1;
        for(int i = 1; i <= N; i++){
            if(indegree[i] == 0){
                q.add(i);
                rank[i] = cnt;
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            for(Integer next: graph[cur]){
                indegree[next]--;
                if(indegree[next] == 0){
                    q.add(next);
                    rank[next] = ++cnt;
                }
            }
        }
    }
    static void recur(int r, StringBuilder sb){
        if(r > N){
            System.out.println(sb);
            isImpossible = false;
            return;
        }
        for(int i = 1; i <= N; i++){
            if(r == rank[i]){
                sb.append(i).append(' ');
                recur(r + 1, sb);
                sb.delete(sb.length() - 2, sb.length());
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        TC = Integer.parseInt(st.nextToken());
        while(TC-- > 0){
            N = Integer.parseInt(br.readLine());
            graph = new Set[N + 1];
            indegree = new int[N + 1];
            rank = new int[N + 1];
            isImpossible = true;
            for(int i = 0; i < graph.length; i++)
                graph[i] = new HashSet<>();
            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(st.nextToken());

            //그래프 초기화
            for(int i = arr.length - 1; i >= 0; i--){
                for(int j = i + 1; j < arr.length; j++){
                    graph[arr[i]].add(arr[j]);
                    indegree[arr[j]] += 1;
                }
            }
            M = Integer.parseInt(br.readLine());
            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int s, e;
                s = Integer.parseInt(st.nextToken());
                e = Integer.parseInt(st.nextToken());
                if(graph[s].contains(e)){
                    graph[s].removeIf(o -> o == e);
                    graph[e].add(s);
                    indegree[s]++;
                    indegree[e]--;
                }
                else if(graph[e].contains(s)){
                    graph[e].removeIf(o -> o == s);
                    graph[s].add(e);
                    indegree[s]--;
                    indegree[e]++;
                }
            }
            q = new ArrayDeque<>();
            topologySort();
            recur(1, new StringBuilder());
            if(isImpossible)
                System.out.println("IMPOSSIBLE");
        }
    }
}
