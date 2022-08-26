import java.io.*;
import java.util.*;

public class Main {
	static final int INIT = (int)1e9;
	static int n;
	static int[] parent;
	static ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
	
	
	static void dfs(int now) {
		for(int i=0; i<al.get(now).size(); i++) {
			int next = al.get(now).get(i);
			if(parent[next] == INIT) {
				parent[next] = now;
				dfs(next);
			}
		}
		
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		parent = new int[n+1];
		Arrays.fill(parent, INIT);
		parent[1] = 1;
		for(int i=0; i<=n; i++)
			al.add(new ArrayList<Integer>());
		for(int i=1; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a= Integer.parseInt(st.nextToken());
			int b =Integer.parseInt(st.nextToken());
			al.get(a).add(b);		
			al.get(b).add(a);
			
		}
		dfs(1);
		
		
		for(int i=2; i<=n; i++)
			System.out.println(parent[i]);

	}

}
