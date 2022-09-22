package week7;
import java.util.*;
public class BOJ_9658_µπ∞‘¿”4_jbh2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int[] dp = new int[1001];
		dp[1] = 1; dp[3]=1;
		for(int i=4; i<=num; i++) {
			if(dp[i-1] == 0 && dp[i-3] == 0 && dp[i-4] ==0)
				dp[i] = 1;
		}
		System.out.println((dp[num] == 0) ? "SK":"CY");	
	}
}
