import java.util.*;
import java.io.*;
public class Main {
  static int N;
  static boolean hasCycle = false;
  static boolean[] visit, cycle;
  static int[] pre, dist;
  static Set<Integer> graph[];
  static void findCycle(int cur){
    visit[cur] = true;
    for(Integer next: graph[cur]){
      if(hasCycle) return;
      if(visit[next]){
        if(next != pre[cur]){
          cycle[cur] = true;
          hasCycle = true;
          while(cur != next){
            cycle[pre[cur]] = true;
            cur = pre[cur];
          }
          return;
        }
      }
      else{
        pre[next] = cur;
        findCycle(next);
      }
    }
  }
  static void bfs(){
    Queue<Integer> q = new LinkedList<>();
    for(int i = 1; i <= N; i++){
      if(cycle[i]){
        visit[i] = true;
        q.add(i);
      }
    }
    while(!q.isEmpty()){
      int cur = q.poll();
      for(Integer next: graph[cur]){
        if(visit[next]) continue;
        visit[next] = true;
        dist[next] = dist[cur] + 1;
        q.add(next);
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    int s, e;
    graph = new Set[N + 1];
    visit = new boolean[N + 1];
    cycle = new boolean[N + 1];
    pre = new int[N + 1];
    dist = new int[N + 1];
    for(int i = 0; i <= N; i++){
      graph[i] = new HashSet<>();
    }
    for(int i = 0; i < N; i++){
      st = new StringTokenizer(br.readLine());
      s = Integer.parseInt(st.nextToken());
      e = Integer.parseInt(st.nextToken());
      graph[s].add(e);
      graph[e].add(s);
    }
    findCycle(1);
    visit = new boolean[N + 1];
    bfs();
    for(int i = 1; i <= N; i++){
      System.out.printf("%d ", dist[i]);
    }
  }
}