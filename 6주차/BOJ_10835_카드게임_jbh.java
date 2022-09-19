package week6;
import java.util.*;
import java.io.*;
public class BOJ_10835_카드게임_jbh {
	static int n;
	static int[] l,r;
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		l = new int[n]; r = new int[n];
		dp = new int[n+1][n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			l[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) 
			r[i] = Integer.parseInt(st.nextToken());
		
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++)
				dp[i][j] = -1;
		}
		System.out.println(dp(0,0));
		
	}
	static int  dp(int left,int right) {
		if(left ==n || right == n || dp[left][right] != -1)
			return dp[left][right];
		int temp = Math.max(dp(left+1,right), dp(left+1,right+1));
		return dp[left][right] = (l[left] > r[right]) ?
				Math.max(temp, dp(left,right+1)+r[right]) : temp;
		
	}
}
