import java.util.*;
import java.io.*;
public class S2_14889_스타트와링크 {

	static int n,r;
	static int[][] map;
	static boolean[] visited;
	static int result = (int)1e9;
	static int sumGap(boolean[] visited) {
		int sum1 = 0; int sum2 = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(visited[i] && visited[j]) {
					sum1 += map[i][j];
				}
			}
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!visited[i] && !visited[j]) {
					sum2 += map[i][j];
				}
			}
		}
		
		
		
		return Math.abs(sum1-sum2);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		r = n/2;
		map = new int[n][n];
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n;j++)
				map[i][j] = Integer.parseInt(st.nextToken());
			
		}
		visited = new boolean[n];
		//20 C 10  = 18만
		comb(0,0);
		System.out.println(result);
	}
	static void comb(int cnt,int start) {
		if(cnt == r) {
			int gap = sumGap(visited);
			if(result > gap)
				result = gap;
			
			return;
		}
		for(int i=start; i<n; i++) {
			visited[i] = true;
			comb(cnt+1,i+1);
			visited[i] = false;
			
		}
		
	}

}
