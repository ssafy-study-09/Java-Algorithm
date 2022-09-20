import java.util.*;
import java.io.*;
/*
모든 경우를 탐색 = 1000C4 -> TLE
N^2으로 두 파트로 나눠 합을 구한 후 다시 N^2으로 더함 -> 2 * N^2
1트: 왜 시간초과? => N^4으로 풀이한거였음..
* */
public class Main {
  static int TC, N, K;
  static int[][] arr = new int[4][1001];
  static int[] sumA;
  static int[] sumB;
  static int ans, diff;
  //K에 가까운 값, 일치한다면 K보다 작은 값을 고름
  static int binSearch(int sum){
    int s = 0, e = sumB.length - 1, mid, calc;
    while(s <= e){
      mid = (s + e) / 2;
      calc = K - (sumB[mid] + sum);
      if(diff > Math.abs(calc)){
        diff = Math.abs(calc);
        ans = sumB[mid] + sum;
      }
      else if(diff == Math.abs(calc) && calc > 0){
        ans = sumB[mid] + sum;
      }

      if(calc < 0){
        e = mid - 1;
      }
      else if(calc > 0){
        s = mid + 1;
      }
      else{
        ans = sumB[mid] + sum;
        return sumB[mid] + sum;
      }
    }
    return ans;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    TC = Integer.parseInt(st.nextToken());
    while(TC-- > 0){
      ans = 0;
      diff = Integer.MAX_VALUE;
      st = new StringTokenizer(br.readLine());
      K = Integer.parseInt(st.nextToken());
      N = Integer.parseInt(st.nextToken());
      sumA = new int[N * N];
      sumB = new int[N * N];
      for(int i = 0; i < 4; i++){
        st = new StringTokenizer(br.readLine());
        for(int j = 0; j < N; j++){
          arr[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      for(int i = 0; i < N; i++){
        for(int j = 0; j < N; j++){
          sumA[i * N + j] = arr[0][i] + arr[1][j];
          sumB[i * N + j] = arr[2][i] + arr[3][j];
        }
      }
      Arrays.sort(sumA);
      Arrays.sort(sumB);
      for(int i = 0; i < N * N; i++){
        binSearch(sumA[i]);
      }
      System.out.println(ans);
    }
  }
}
