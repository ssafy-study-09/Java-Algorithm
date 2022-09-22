import java.util.*;

public class Main {
	static int n;
	static int[] arr;
	static boolean[][] beat;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int T = sc.nextInt();
		while (T-->0) {
			n = sc.nextInt();
			arr = new int[n+1];
			beat = new boolean[n+1][n+1];
			for (int i=1; i<=n; ++i)
				arr[i] = sc.nextInt();
			
			for (int i=1; i<=n; ++i)
				for (int j=i+1; j<=n; ++j) {
					beat[arr[i]][arr[j]] = true;
					beat[arr[j]][arr[i]] = false;
				}
			
			int m = sc.nextInt();
			while (m-->0) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				beat[a][b] = !beat[a][b];
				beat[b][a] = !beat[b][a];
			}
			
			int[] ans = new int[n];
			for (int i=1; i<=n; ++i) {
				int cnt = 0;
				for (int j=1; j<=n; ++j)
					if (beat[j][i]) ++cnt;
				ans[cnt] = i;
			}
			
			boolean ok=true;
			for (int x: ans)
				if (x==0)
					ok=false;

			if (ok) {
				for (int x: ans)
					sb.append(x).append(' ');
				sb.append('\n');
			} else sb.append("IMPOSSIBLE\n");
		}
		System.out.println(sb);
	}

}