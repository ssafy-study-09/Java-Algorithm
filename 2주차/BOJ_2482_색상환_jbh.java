import java.util.*;

public class Main {

	final static int mod = 1000000003;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[][] dp = new int[n+1][n+1];
		if(k >=(n/2)+1) { //뒤로 다 0
			System.out.println(0);
			return;
		}
		
		for(int i=1; i<=n; i++) {
			dp[i][1] = i;
			dp[i][0] = 1; //값 1적게나옴
		}
		
		
		//4 1  4 2 4 3
		for(int i=3; i<=n; i++) {
			for(int j=2; j<=k; j++) { 
				dp[i][j] = (dp[i-2][j-1] + dp[i-1][j]) % mod;
			}
		}
		System.out.println((dp[n-3][k-1]+dp[n-1][k]) % mod) ;
	}

}