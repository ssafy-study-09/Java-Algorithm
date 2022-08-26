import java.io.*;
import java.util.*;
//적어도 M의 나무를 얻을 수 있는 절단기 높이의 최댓값을 구하시오
public class Main {
    static int N, M, answer = 0;
    static int[] arr;
    static long isPossible(int height){
        long sum = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] - height > 0){
                sum += arr[i] - height;
            }
        }
        return sum;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int s = 0, e = 20_0000_0000, mid = 0;
        while(s <= e){
            mid = (s + e) / 2;
            if(isPossible(mid) >= M){
                s = mid + 1;
                answer = Math.max(answer, mid);
            }
            else if(isPossible(mid) < M){
                e = mid - 1;
            }
        }
        System.out.println(answer);
    }
}

