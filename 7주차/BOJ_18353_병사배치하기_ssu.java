import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18353_병사배치하기_ssu {
    static int N, dp[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[] soldiers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            soldiers[i] = Integer.parseInt(st.nextToken());
        }

        LIS(soldiers);
        int maxLength = -1;
        for(int i=0;i<N;i++){
            maxLength = Math.max(maxLength,dp[i]);
        }
        System.out.println(N-maxLength);
    }

    public static void initMemo(){
        dp = new int[N];
        dp[0] = 1;
    }

    public static void LIS(int[] arr){
        initMemo();

        for(int i=1;i<N;i++){
            int temp = 1;
            for(int j=0;j<i;j++){
                if(arr[j] > arr[i]){
                    temp = Math.max(temp,dp[j]+1);
                }
            }
            dp[i] = temp;
        }
    }


}