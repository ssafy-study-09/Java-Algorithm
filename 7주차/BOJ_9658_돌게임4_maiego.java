import java.util.*;

public class Main {
	static int n;
	static boolean[] dp, vis;
	
	static boolean f(int n) {
		if (n<1) return true;
		if (n==1) return false;
		if (vis[n]) return dp[n];
		vis[n] = true;
		return dp[n] = !f(n-1) || !f(n-3) || !f(n-4);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		dp = new boolean[n+1];
		vis = new boolean[n+1];
		
		System.out.println(f(n) ? "SK" : "CY");
	}

}