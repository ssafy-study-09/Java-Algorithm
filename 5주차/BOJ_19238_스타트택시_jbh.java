package week5;


import java.util.*;
import java.io.*;

public class BOJ_19238_Ω∫≈∏∆Æ≈√Ω√_jbh {
	static int n,m,fuel;
	static int[][] map;
	static int sx,sy;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static HashMap<Integer,Integer> hm = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken())-1; sy = Integer.parseInt(st.nextToken())-1;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken())-1;
			map[a][b] = 2;
			hm.put(a*n+b, c*n+d);
		}
		while(m > 0) {
		//	System.out.println(fuel);
//			if(findClient() && moveDestination())
//				m--;
			if(findClient() && moveDestination()) {
					m--;
			}
			else
				break;
		}
		int result = -1;
		if(m == 0)
			result = fuel;
		else
			result = -1;
		System.out.println(result);
	}
	
	static boolean findClient() {
		Queue<xy> q = new LinkedList<>();
		boolean[][] visited = new boolean[n][n];
		q.offer(new xy(sx,sy));
		visited[sx][sy] =true;
		int dist = 0;
		PriorityQueue<xy> pq = new PriorityQueue<>();
		while(!q.isEmpty()) {
			int qsize = q.size();
			if(dist > fuel)
				return false;
			for(int i=0; i<qsize; i++) {
				xy now = q.poll();
				int x = now.x; int y = now.y;
				if(map[x][y] == 2)
					pq.add(now);
				for(int j=0; j<4; j++) {
					int nx = x+dx[j]; int ny = y+dy[j];
					if(nx<0 || ny<0 || nx>=n || ny>=n)
						continue;
					if(map[nx][ny] != 1 && !visited[nx][ny]) {
						visited[nx][ny] = true;
						q.offer(new xy(nx,ny));
					}
				}
			}
			
			if(!pq.isEmpty()) {
		//		System.out.println("pqsize :" + pq.size());
				break;
			}
			dist +=1;
		}
		if(!pq.isEmpty()) {
			xy now = pq.poll();
			map[now.x][now.y] = 0;
	//		System.out.println("º’¥‘ m : " + now.x+","+now.y);
			sx = now.x; sy = now.y;
			fuel -= dist;
	//		System.out.println("dist :" + dist);
			return true;
		} else
			return false;
		
	}
	static boolean moveDestination() {
		int tplace = hm.get(sx*n+sy);
		int tx = tplace/n; int ty = tplace%n;
		Queue<xy> q  = new LinkedList<>();
		boolean[][] visited = new boolean[n][n];
		q.add(new xy(sx,sy));
		visited[sx][sy] = true;
		int dist = 0;
		while(!q.isEmpty()) {
			int qsize = q.size();
			if(fuel < dist)
				return false;
			
			for(int i=0; i<qsize; i++) {
				xy now = q.poll();
				int x = now.x; int y = now.y;
				if(x==tx && y == ty) {
					fuel += dist;
					sx = x; sy =y;
	//				System.out.println("µµ¬¯ : " +x+","+y);
					return true;
				}
				for(int j=0; j<4; j++) {
					int nx = x+dx[j]; int ny =y+dy[j];
					if(nx<0 || ny<0 || nx>=n || ny>=n)
						continue;
					if(map[nx][ny] != 1 && !visited[nx][ny]) {
						visited[nx][ny] = true;
						q.add(new xy(nx,ny));
						
					}
				}
			}
			dist +=1;
		}
		
		
		return false;
	}
	
	static class xy implements Comparable<xy>{
		int x;
		int y;

		public xy(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(xy o) {
			if(this.x == o.x)
				return this.y-o.y;
			return this.x-o.y;
		}
	}
}
