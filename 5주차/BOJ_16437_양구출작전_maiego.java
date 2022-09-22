import java.util.*;

public class Main {
	static int[] sheep, wolf;
	static List<Integer> adj[];
	
	static long dfs(int u) {
		long ret = 0;
		for (int v: adj[u])
			ret += dfs(v);
		return sheep[u] + Math.max(0, ret-wolf[u]);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sheep = new int[n+1];
		wolf = new int[n+1];
		
		adj = new List[n+1];
		for (int i=1; i<=n; ++i)
			adj[i] = new ArrayList<>();

		for (int i=2; i<=n; ++i) {
			if (sc.next().equals("S"))
				sheep[i] = sc.nextInt();
			else wolf[i] = sc.nextInt();
			int j = sc.nextInt();
			adj[j].add(i);
		}
		System.out.println(dfs(1));

	}

}