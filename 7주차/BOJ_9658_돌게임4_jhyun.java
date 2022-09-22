import java.io.*;
import java.util.*;
public class Main {
    static int N, SK = 0, CY = 1;
    static int[] dp;
    static int[] step = {1, 3, 4};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[1001];
        dp[1] = SK; dp[3] = SK; dp[4] = CY;
        dp[2] = CY;
        for(int i = 5; i <= N; i++){
            if(dp[i - 1] > 0 && dp[i - 3] > 0 && dp[i - 4] > 0){
                dp[i] = SK;
            }
            else{
                dp[i] = CY;
            }
        }
        if(dp[N] == CY)
            System.out.println("SK");
        else
            System.out.println("CY");
    }
}
