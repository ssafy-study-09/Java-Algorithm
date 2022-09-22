package week5;
import java.util.*;
import java.io.*;
public class BOJ_16437_양구출작전_jbh {
	static int n;
	static boolean[] isSheep;
	static int[] amount;
	static ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		isSheep = new boolean[n+1]; amount = new int[n+1];
		for(int i=0; i<=n+1; i++)
			al.add(new ArrayList<Integer>());
		for(int i=2; i<=n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			if(st.nextToken().equals("S"))
				isSheep[i] = true;
			amount[i] = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			al.get(next).add(i);
			
		}
		System.out.println(dfs(1));
	}
	static long dfs(int now) {
		long sum = 0;
		for(int i=0; i<al.get(now).size(); i++) {
			sum += dfs(al.get(now).get(i));
		}
		sum += isSheep[now] ? amount[now] : amount[now] * -1;
		return (sum >=0) ? sum : 0;
	}
	
}
