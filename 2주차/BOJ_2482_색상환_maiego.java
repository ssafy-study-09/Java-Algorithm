import java.util.*;

public class Main {
	final static int mod = (int)1e9+3;
	static int n,k;
	static int[][][] cache = new int[1001][1001][2];
	static boolean[][][] vis = new boolean[1001][1001][2];

	static int f(int idx, int cnt, int select0) {
		if (cnt==k) return 1;
		if (idx>=n) return 0;
		if (vis[idx][cnt][select0])
			return cache[idx][cnt][select0];
		
		long ret = f(idx+1, cnt, select0);
		if (idx!=n-1 || select0==0) {
			ret += f(idx+2, cnt+1, select0);
			ret %=mod;
		}
		
		vis[idx][cnt][select0] = true;
		return cache[idx][cnt][select0] = (int)ret;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		k = sc.nextInt();

		long ans = f(2,1,1) + f(1,0,0);
		ans %= mod;
		System.out.println((int)ans);

	}

}
