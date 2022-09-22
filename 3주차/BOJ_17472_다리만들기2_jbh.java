import java.util.*;
import java.io.*;
public class Main {

	static int n,m;
	static int[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[] parent;
	static int findParent(int x) {
		return x == parent[x] ? x : (parent[x] = findParent(parent[x]));
	}
	static void unionParent(int a,int b) {
		a = findParent(a); b = findParent(b);
		if(a<b)
			parent[b] = a;
		else
			parent[a] = b;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		PriorityQueue<edge> pq = new PriorityQueue<>();
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = -1 * Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j]  == -1) {
					map[i][j] = cnt;
					dfs(i,j,cnt);
					cnt++;
				}
				
			}
		}

		parent = new int[cnt];
		for(int i=1; i<=cnt-1; i++)
			parent[i] = i;
		
		
		for(int i=0; i<n*m; i++) {
			for(int j=0; j<n*m; j++) {
				int x1 = i/m; int y1 = i%m;
				int x2 = j/m; int y2 = j%m;
				if(map[x1][y1] != 0 && map[x2][y2] != 0
						&& map[x1][y1] != map[x2][y2]) {
					
					if(x1 == x2 && checkcol(x1,y1,x2,y2)) {
						pq.add(new edge(map[x1][y1],map[x2][y2],Math.abs(y1-y2)-1));
					}
					if(y1 == y2 && checkrow(x1,y1,x2,y2)) {
						pq.add(new edge(map[x1][y1],map[x2][y2],Math.abs(x1-x2)-1));
					}
					
				}
			}
		}
		
		
		int v= 0 ; int result = 0;
		while(!pq.isEmpty()) {
			edge e = pq.poll();
			int x = e.x; int y = e.y; int dist = e.dist;
			if(findParent(x) != findParent(y)) {
				unionParent(x,y);
				v +=1; result += dist;
			}
			
			if( v == cnt-2)
				break;
		}
		if(v < cnt-2)
			result = -1;
		System.out.println(result);
		
		
	}
	static boolean checkrow(int x1,int y1,int x2,int y2) {
		int max = Math.max(x1, x2);
		int min = Math.min(x1, x2);
		if(max - min-1 <=1 )
			return false;
		for(int i=min+1; i<max; i++) {
			if(map[i][y1] != 0)
				return false;
		}
		return true;
	}
	static boolean checkcol(int x1,int y1,int x2,int y2) {
		int max = Math.max(y1, y2);
		int min = Math.min(y1, y2);
		if(max - min-1 <=1 )
			return false;
		for(int i=min+1; i<max; i++) {
			if(map[x1][i] != 0)
				return false;
		}
		return true;
	}
	
	
	
	static void dfs(int x,int y,int cnt) {
		for(int i=0; i<4; i++) {
			int nx = x+dx[i]; int ny = y+dy[i];
			if(nx<0 || ny<0 || nx>=n || ny>=m)
				continue;
			if(map[nx][ny] == -1) {
				map[nx][ny] = cnt;
				dfs(nx,ny,cnt);
			}
		}
	}
	
	static class edge implements Comparable<edge>{
		int x;int y; int dist;
		public edge(int x,int y,int dist) {
			this.x=x;
			this.y=y;
			this.dist=dist;
		}
		@Override
		public int compareTo(edge o) {
			return this.dist - o.dist;
		}
		
	}

}