import java.io.*;
import java.util.*;
public class Main {
  static int N, M, from, to;
  static List<Integer> friends[], enemy[];
  static int[] parents;
  static boolean[] visit;
  static int answer = 0;
  static void dfs(int pre, int cur, int depth){
    if(depth == 2){
      friends[cur].add(pre);
      friends[pre].add(cur);
      return;
    }
    visit[cur] = true;
    for(Integer next: enemy[cur]){
      if(visit[next] == false){
        dfs(pre, next, depth + 1);
      }
    }
    visit[cur] = false;
  }
  static int getParent(int x){
    if(parents[x] < 0){
      return x;
    }
    parents[x] = getParent(parents[x]);
    return parents[x];
  }
  static void union(int a, int b){
    a = getParent(a);
    b = getParent(b);
    if(a < b){
      parents[b] = a;
    }
    else{
      parents[a] = b;
    }
  }
  static boolean makeTeam(int cur){
    if(visit[cur]) return false;
    visit[cur] = true;
    for(Integer next: friends[cur]){
      if(getParent(cur) != getParent(next)){
        union(cur, next);
      }
      makeTeam(next);
    }
    return true;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(br.readLine());
    friends = new List[N + 1];
    enemy = new List[N + 1];
    parents = new int[N + 1];
    visit = new boolean[N + 1];
    for(int i = 0; i <= N; i++){
      friends[i] = new ArrayList<>();
      enemy[i] = new ArrayList<>();
      parents[i] = -1;
    }
    for(int i = 0; i < M; i++){
      st = new StringTokenizer(br.readLine());
      String order = st.nextToken();
      from = Integer.parseInt(st.nextToken());
      to = Integer.parseInt(st.nextToken());
      if(order.equals("F")){
        friends[from].add(to);
        friends[to].add(from);
      }
      else{
        enemy[from].add(to);
        enemy[to].add(from);
      }
    }
    //원수의 원수 관계에서 친구 찾기
    for(int i = 1; i <= N; i++){
      dfs(i, i, 0);
    }
    visit = new boolean[N + 1];
    //친구 관계에서 유니온
    for(int i = 1; i <= N; i++){
      if(makeTeam(i)){
        answer++;
      }
    }
    System.out.println(answer);
  }
}
