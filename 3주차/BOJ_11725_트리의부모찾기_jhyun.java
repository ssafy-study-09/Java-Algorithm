import java.io.*;
import java.util.*;
//적어도 M의 나무를 얻을 수 있는 절단기 높이의 최댓값을 구하시오
public class Main {
    static int N;
    static List<Integer> graph[];
    static boolean[] visit;
    static int[] parents;
    static void dfs(int cur){
        for(Integer next: graph[cur]){
            if(visit[next] == false){
                visit[next] = true;
                parents[next] = cur;
                dfs(next);
                visit[next] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int s, e;
        graph = new List[N + 1];
        visit = new boolean[N + 1];
        parents = new int[N + 1];
        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
            graph[e].add(s);
        }
        StringBuilder sb = new StringBuilder();
        visit[1] = true;
        dfs(1);
        for(int i = 2; i <= N; i++){
            sb.append(parents[i]).append("\n");
        }
        System.out.println(sb);
    }
}

