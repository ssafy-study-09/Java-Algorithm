import java.util.*;
import java.io.*;

public class Main {
  static int N, D, K, C, answer = 0;
  static int[] arr;
  static Map<Integer, Integer> kind = new HashMap<>();
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    D = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    arr = new int[N];
    int s = 0, e = 0;
    for(int i = 0; i < N; i++){
      arr[i] = Integer.parseInt(br.readLine());
    }
    //쿠폰 초밥
    kind.put(C, 1);
    for(; e < K - 1; e++){
      kind.put(arr[e], kind.getOrDefault(arr[e], 0) + 1);
    }
    do{
      kind.put(arr[e], kind.getOrDefault(arr[e], 0) + 1);
      answer = Math.max(kind.size(), answer);
      int left = kind.get(arr[s]);
      int right = kind.get(arr[e]);
      if(left - 1 == 0){
        kind.remove(arr[s]);
      }
      else{
        kind.put(arr[s], left - 1);
      }
      s = (s + 1) % N;
      e = (e + 1) % N;
    }while(s != 0);
    System.out.println(answer);
  }
}
