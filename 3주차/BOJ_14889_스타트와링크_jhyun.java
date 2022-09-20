import java.io.*;
import java.util.*;
//배열의 i, j 쌍의 원소의 합이 최대가 되도록 하는 절반의 조합을 구하시오
public class Main {
    static int N;
    static int[][] arr;
    static boolean[] visit;
    static int answer = Integer.MAX_VALUE;
    static void choice(int idx, int pick){
        if(idx == N){
            if(pick == N / 2){
                int sumA = 0, sumB = 0;
                for(int i = 0; i < N; i++){
                    for(int j = i + 1; j < N; j++){
                        if(visit[i] && visit[j]){
                            sumA += arr[i][j] + arr[j][i];
                        }
                        else if(!visit[i] && !visit[j]){
                            sumB += arr[i][j] + arr[j][i];
                        }
                    }
                }
                answer = Math.min(answer, Math.abs(sumA - sumB));
            }
            return;
        }
        visit[idx] = true;
        choice(idx + 1, pick + 1);
        visit[idx] = false;
        choice(idx + 1, pick);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visit = new boolean[N];
        arr = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        choice(0, 0);
        System.out.println(answer);
    }
}

