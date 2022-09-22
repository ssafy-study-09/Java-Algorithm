import java.util.*;

public class Solution {
	static class Item {
		int v, w;
		Item(int a, int b) {
			v=a; w=b;
		}
	}

	static List<Item> adj[];
	static boolean[] isSummit, isGate;

	int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		adj = new List[n+1];
		for (int i=1; i<=n; ++i)
			adj[i] = new ArrayList<>();
		
		for (int i=0; i<paths.length; ++i) {
			adj[paths[i][0]].add(new Item(paths[i][1], paths[i][2]));
			adj[paths[i][1]].add(new Item(paths[i][0], paths[i][2]));
		}
		
		isSummit = new boolean[n+1];
		for (int x: summits)
			isSummit[x]=true;
		
        isGate = new boolean[n+1];
        for (int x: gates)
            isGate[x]=true;
		
        PriorityQueue<Item> ans = new PriorityQueue<>((a,b)->a.w==b.w ? a.v-b.v : a.w-b.w);
        for (int x: gates) {
        	PriorityQueue<Item> q = new PriorityQueue<>((a,b)->a.w==b.w ? a.v-b.v : a.w-b.w);
        	int[] vis = new int[n+1];
        	Arrays.fill(vis, (int)1e9);
        	q.add(new Item(x, 0));
        	while (!q.isEmpty()) {
      			Item item = q.poll();
      			int u=item.v, w=item.w;
      			if (isSummit[u]) {
      				ans.add(item);
      				continue;
      			}
      			if (!ans.isEmpty() && w>ans.peek().w)
      				break;
      			
      			for (Item e: adj[u]) {
      				int newW = Math.max(w, e.w);
      				if (isGate[e.v]) continue;
      				if (vis[e.v]<=newW) continue;
      				vis[e.v]=newW;
      				q.add(new Item(e.v, Math.max(w, e.w)));
      			}
      		}
        }
		return new int[] {ans.peek().v, ans.peek().w};
	}
	
}
