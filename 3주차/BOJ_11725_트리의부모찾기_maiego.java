import java.util.*;

public class Main {
	static int n;
	static List<Integer> adj[];
	static int[] ans;
	
	static void dfs(int u, int p) {
		for (int v: adj[u]) {
			if (v==p) continue;
			ans[v]=u;
			dfs(v,u);
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		n = sc.nextInt();
		
		ans = new int[n+1];
		adj = new List[n+1];
		for (int i=1; i<=n; ++i)
			adj[i] = new ArrayList<>();
		
		for (int i=0; i<n-1; ++i) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			adj[a].add(b);
			adj[b].add(a);
		}
		dfs(1,-1);
		
		for (int i=2; i<=n; ++i)
			sb.append(ans[i]).append('\n');
		System.out.println(sb);
	}

}