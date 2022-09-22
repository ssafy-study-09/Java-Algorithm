package submmit;
import java.util.*;
import java.io.*;

public class Main {

	static int n,result =0;
	static int max,maxIdx;
	static boolean[] visited;
	static ArrayList<ArrayList<xy>> al = new ArrayList<ArrayList<xy>>();
	
	static void dfs(int now,int sum) {
		if(max < sum) {
			max = sum;
			maxIdx = now;
		}
		for(int i=0; i<al.get(now).size(); i++) {
			int next = al.get(now).get(i).y;
			if(!visited[next]) {
				visited[next] = true;
				dfs(next,sum+al.get(now).get(i).cost);
			}
		}
		
		
	}
	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		visited = new boolean[n+1];
		
		for(int i=0; i<=n; i++)
			al.add(new ArrayList<xy>());
	
		for(int i=1; i<=n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			al.get(a).add(new xy(b,c));
			al.get(b).add(new xy(a,c));
			
		}
		
		visited[1] = true;
		dfs(1,0);
		
		Arrays.fill(visited, false);
		visited[maxIdx] = true;
		dfs(maxIdx,0);
		
		System.out.println(max);
		

	}
	
	static class xy{
		int y;int cost;
		public xy(int y,int cost) {
			this.y=y;
			this.cost=cost;
		}
	}

}
