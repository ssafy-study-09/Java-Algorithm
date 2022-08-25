import java.io.*;
import java.util.*;

public class Main {
  static int N, M;
  static int[] indegree;
  static List<Integer> graph[];
  static void topologySort(){
    Queue<Integer> q = new ArrayDeque<>();
    for(int i = 1; i <= N; i++){
      if(indegree[i] == 0){
        q.add(i);
      }
    }
    while(!q.isEmpty()){
      int cur = q.poll();
      System.out.print(cur + " ");
      for(Integer next: graph[cur]){
        indegree[next]--;
        if(indegree[next] == 0){
          q.add(next);
        }
      }
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.valueOf(st.nextToken());
    M = Integer.valueOf(st.nextToken());
    indegree = new int[N + 1];
    graph = new List[N + 1];
    for(int i = 0; i <= N; i++){
      graph[i] = new ArrayList<>();
    }
    int s, e;
    for(int i = 0; i < M; i++){
      st = new StringTokenizer(br.readLine());
      s = Integer.valueOf(st.nextToken());
      e = Integer.valueOf(st.nextToken());
      indegree[e]++;
      graph[s].add(e);
    }
    topologySort();
  }
}

