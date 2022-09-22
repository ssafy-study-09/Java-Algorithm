import java.util.*;

public class Main {
	static List<Integer> adj[];
	static int[] onstk, vis;
	static Deque<Integer> stack = new ArrayDeque<>();
	
	static void dfs(int u, int p) {
		onstk[u] = 1;
		stack.push(u);
		for (int v: adj[u]) {
			if (vis[v]>0 || v==p) continue;
			if (onstk[v]==1) {
				while (stack.peek()!=v)
					vis[stack.pop()]=2;
				vis[v]=2;
				return;
			} else dfs(v, u);
		}
		onstk[u] = 0;
		if (vis[u]==2) return;
		stack.pop();
		vis[u] = 1;
	}
	
	static int dfs2(int u, int p) {
		if (vis[u]==2) return 0;
		int ret = (int)1e9;
		for (int v: adj[u]) {
			if (v==p) continue;
			ret = Math.min(ret, dfs2(v, u));
		}
		return 1+ret;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int n = sc.nextInt();
		onstk = new int[n+1];
		vis = new int[n+1];
		adj = new List[n+1];
		for (int i=1; i<=n; ++i)
			adj[i] = new ArrayList<>();
		
		for (int i=0; i<n; ++i) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			adj[a].add(b);
			adj[b].add(a);
		}
		
		dfs(1, -1);
		
		for (int i=1; i<=n; ++i)
			sb.append(dfs2(i, -1)).append(' ');
		System.out.println(sb);

	}

}
