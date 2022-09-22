package week7;
import java.util.*;
import java.io.*;
public class BOJ_3665_최종순위_jbh {

	static int n,m;
	static boolean[][] front;
	static int[] rank;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=tc; t++) {
			n = Integer.parseInt(br.readLine());
			rank = new int[n+1];
			front = new boolean[n+1][n+1];
			int[][] cntIndegree = new int [n+1][2];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++)
				rank[i] = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<n-1; i++) {
				for(int j=i+1; j<n; j++) {
					front[rank[i]][rank[j]] = true;
				}
			}
			m = Integer.parseInt(br.readLine());
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); int b = Integer.parseInt(st.nextToken());
				front[a][b] = !front[a][b];   front[b][a] =!front[b][a];
				
			}
			int total = 0; boolean pass = true;
			for(int i=1; i<=n; i++) {
				int sum = 0;
				for(int j=1; j<=n; j++) {
					if(front[i][j])
						sum++;
				}
				if(pass  && cntIndegree[sum][0] != 0) {
					pass = false;
					
				}
				cntIndegree[sum][0] +=1;
				cntIndegree[sum][1] = i;
				total +=sum;
			}
			
			if(pass) {
				for(int i=n-1; i>=0; i--)
				sb.append(cntIndegree[i][1]+" ");
				sb.append("\n");
				continue;
			} else {
				sb.append("IMPOSSIBLE\n");
				continue;
				
			}
				
		}
		System.out.println(sb.toString());

	}

}
