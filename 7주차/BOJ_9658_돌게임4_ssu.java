import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9658_돌게임4_ssu {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[1001];
        //dp[i] : 돌이 i개일 때 승리하는 사람의 번호 ( 0 : 상근, 1 : 창영 )
        //상근과 창영이는 최대한 이기려고 플레이를 한다.
        dp[1] = 1;
        dp[2] = 0;
        dp[3] = 1;

        for(int i=4; i<=n; i++) {
            //창영이가 이길 수 있는 방법이 하나라도 있다면 상근이는 질 수 밖에 없다.
            if(dp[i-1]==1 || dp[i-3]==1 || dp[i-4]==1 ) {
                dp[i] =0;
            }
            else {
                dp[i] =1;
            }

        }

        if(dp[n] == 0) {
            System.out.println("SK");
        }else {
            System.out.println("CY");
        }

    }
}