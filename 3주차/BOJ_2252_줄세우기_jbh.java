import java.util.*;
import java.io.*;
public class G3_2252_줄세우기 {

	static int n,m;
	static ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
	static int[] indegree;
	static Queue<Integer> q = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		indegree = new int[n+1];
		for(int i=0; i<=n; i++)
			al.add(new ArrayList<Integer>());
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a =  Integer.parseInt(st.nextToken());
			int b =  Integer.parseInt(st.nextToken());
			indegree[a] +=1;
			al.get(b).add(a);
		}
		for(int i=1; i<=n; i++) {
			if(indegree[i] == 0)
				q.offer(i);
			
		}
		while(!q.isEmpty()) {
			int now = q.poll();
			sb.insert(0, now+" ");
			for(int i=0; i<al.get(now).size(); i++) {
				int connect = al.get(now).get(i);
				indegree[connect] -=1;
				if(indegree[connect] == 0)
					q.offer(connect);
				
			}
			
		}
		System.out.println(sb.toString());

	}

}
