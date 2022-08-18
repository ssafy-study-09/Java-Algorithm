import java.util.*;
import java.io.*;
public class Main {

	static int n,m;
	static int[] friend;
	//enemy;
	static ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
	
	static int findParent(int x) {
		
			if(x == friend[x])
				return x;
			return friend[x] = findParent(friend[x]);
		
	
	}
	
	static void unionParent(int a,int b) {
			a = findParent(a);
			b = findParent(b);
			if(a<b)
				friend[b] = a;
			else
				friend[a] = b;
			
	}
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		friend = new int[n+1];
		
		HashSet<Integer> set = new HashSet<>();
		
		for(int i=1; i<=n; i++) {
			friend[i] = i;
			
		}
		for(int i=0; i<=n+1; i++)
			al.add(new ArrayList<Integer>());
		
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(s.equals("E")) {
				al.get(a).add(b);
				al.get(b).add(a);
			}
			else
				unionParent(a,b);
			
		}

		
		
		
		for(int i=1; i<=n; i++) {
			int size = al.get(i).size();
			if(size>=2) {
				for(int a=0; a<size-1; a++) {
					for(int b= a+1; b<size; b++) {
						int t1 = al.get(i).get(a);
						int t2 = al.get(i).get(b);
						if(findParent(t1) != findParent(t2))
							unionParent(t1,t2);
					}
				}
			}
		}
		
		for(int i=1; i<=n; i++) {
			findParent(i);
		}

		for(int i=1; i<=n; i++) {
			set.add(findParent(i));
		}
		System.out.println(set.size());
		
		
	}

}