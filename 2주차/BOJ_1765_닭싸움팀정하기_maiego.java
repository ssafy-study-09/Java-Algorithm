import java.util.*;

public class Main {
	static int[] link;
	
	static int find(int x) {
		return x==link[x] ? x : (link[x]=find(link[x]));
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int q = sc.nextInt();
		link = new int[n+1];
		for (int i=1; i<=n; ++i)
			link[i]=i;
		List<Integer> enemy[] = new List[n+1];
		for (int i=1; i<=n; ++i)
			enemy[i] = new ArrayList<>();
		
		while (q-->0) {
			String s = sc.next();
			int a = sc.nextInt();
			int b = sc.nextInt();
			if (s.equals("E")) {
				enemy[a].add(b);
				enemy[b].add(a);
			} else {
				int x=find(a), y=find(b);
				link[x]=y;
			}
		}
		
		for (int i=1; i<=n; ++i) {
			for (int j=1; j<enemy[i].size(); ++j) {
				int x=find(enemy[i].get(j)), y=find(enemy[i].get(j-1));
				link[x]=y;
			}
		}
		
		int ans=0;
		for (int i=1; i<=n; ++i)
			if (link[i] == i) ++ans;
		
		System.out.println(ans);

	}

}
