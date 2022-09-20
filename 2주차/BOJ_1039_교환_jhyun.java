import java.util.*;
import java.io.*;
/*
모든 경우를 탐색하는 경우의 수 6^10 = 6천만
재귀로 탐색한다면 스택 제한(128)에 걸릴 수 있음
* */
public class Main {
  static final int INF = 1_000_001;
  static int N, K, MAX = 1;
  static Queue<int[]> q = new LinkedList<>();
  static boolean[][] visit;
  static int bfs(){
    visit = new boolean[INF][K + 1];
    q.add(new int[]{N, 0});
    visit[N][0] = true;
    int answer = -1;
    while(!q.isEmpty()){
      int[] tmp = q.poll();
      int cur = tmp[0];
      int swap = tmp[1];
      if(swap == K){
        answer = Math.max(answer, cur);
      }
      for(int i = MAX; i > 0; i /= 10){
        if(i > cur) continue;
        for(int j = i / 10; j > 0; j /= 10){
          int d1 = cur / i % 10, d2 = cur / j % 10;
          int remain = cur - (d1 * i + d2 * j),
            next = d1 * j + d2 * i + remain;
          if(next < MAX) continue;
          if(swap < K && visit[next][swap + 1] == false){
            q.add(new int[]{next, swap + 1});
            visit[next][swap + 1] = true;
          }
        }
      }
    }
    return answer;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    int num = N;
    while(num >= 10){
      MAX *= 10;
      num /= 10;
    }
    System.out.println(bfs());
  }
}
