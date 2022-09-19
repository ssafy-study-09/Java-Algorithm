import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int T, S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1[1] == o2[1]){
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            T = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            pq.add(new int[]{S - T, S, T});
        }
        int size = pq.size();
        int ans = Integer.MAX_VALUE;
        int sum = 0, limit, dist;
        for(int i = 0; i < size; i++){
            int[] tmp = pq.poll();
            sum += tmp[2];
            limit = tmp[1];
            dist = tmp[0];
            if(dist < 0){
                System.out.println(-1);
                return;
            }
            if(sum > limit){
                System.out.println(-1);
                return;
            }
            ans = Math.min(ans, limit - sum);
        }
        System.out.println(ans);
    }
}
