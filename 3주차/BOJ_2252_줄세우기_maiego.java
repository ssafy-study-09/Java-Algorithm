import java.util.*;

public class Main {
	static int n;
	static List<Integer> adj[];
	static boolean[] vis;
	static List<Integer> list = new ArrayList<>();
	
	static void dfs(int u) {
		vis[u] = true;
		for (int v: adj[u]) {
			if (vis[v]) continue;
			dfs(v);
		}
		list.add(u);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		vis = new boolean[n+1];
		adj = new List[n+1];
		for (int i=1; i<=n; ++i)
			adj[i] = new ArrayList<>();

		int m = sc.nextInt();
		while (m-->0) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			adj[a].add(b);
		}
		for (int i=1; i<=n; ++i)
			if (!vis[i]) dfs(i);
		
		Collections.reverse(list);
		for (int x: list)
			System.out.print(x + " ");
	}

}