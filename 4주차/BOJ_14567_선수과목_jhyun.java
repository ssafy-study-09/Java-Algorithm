import java.util.*;
import java.io.*;
/*
모든 정점마다 dfs로 선수과목을 확인하면 O(NM)으로 TLE
접근: 어떻게 답을 구하는가?
- dfs로 조상을 탐색하며 카운트 -> 위상정렬로 indegree가 0이 될 때
* */
public class Main {
    static int N, M;
    static int[] indegree;
    static List<Integer> graph[];
    static int[] answer;
    static Queue<Integer> q = new ArrayDeque<>();
    static void topologySort(){
        while(!q.isEmpty()){
            int cur = q.poll();
            for(Integer next: graph[cur]){
                indegree[next]--;
                if(indegree[next] == 0){
                    q.add(next);
                    answer[next] = answer[cur] + 1;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int from, to;
        indegree = new int[N + 1];
        graph = new List[N + 1];
        answer = new int[N + 1];
        Arrays.fill(answer, 1);
        for(int i = 0; i <= N; i++)
            graph[i] = new ArrayList<>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            indegree[to]++;
            graph[from].add(to);
        }
        for(int i = 1; i <= N; i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }
        topologySort();
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++)
            sb.append(answer[i]).append(' ');
        System.out.println(sb);
    }
}
