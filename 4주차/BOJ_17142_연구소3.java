import java.util.*;

public class Main {
	static int n, k;
	static int[][] board;
	static int ans = (int)1e9;
	static List<Item> virus = new ArrayList<Item>();
	static int sz, blankCnt;
	static int[] list;
	
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	static class Item {
		int y,x;
		Item(int a, int b) {
			y=a; x=b;
		}
	}
	
	static class Item2 {
		int y,x, time;
		Item2(int a, int b, int c) {
			y=a; x=b; time=c;
		}
	}
	
	static boolean inRange(int y, int x) {
		return x>=0 && y>=0 && x<n && y<n;
	}
	
	static void uptAns() {
		int[][] tmp = new int[n][n];
		for (int i=0; i<n; ++i)
			for (int j=0; j<n; ++j)
				tmp[i][j] = board[i][j];

		int done=0;
		boolean[][] vis = new boolean[n][n];
		Deque<Item2> q = new ArrayDeque<>();
		for (int i: list) {
			Item v = virus.get(i);
			q.add(new Item2(v.y, v.x, 0));
		}
		while (!q.isEmpty()) {
			Item2 item = q.poll();
			if (ans <= item.time) return;
			int y=item.y, x=item.x;
			if (tmp[y][x]==0) ++done;
			tmp[y][x] = 3;
			
			if (done == blankCnt) {
				ans = Math.min(ans, item.time);
				return;
			}
			
			for (int d=0; d<4; ++d) {
				int ny = dy[d]+item.y;
				int nx = dx[d]+item.x;
				if (inRange(ny,nx) && !vis[ny][nx] && tmp[ny][nx]!=1) {
					vis[ny][nx] = true;
					q.add(new Item2(ny, nx, item.time+1));
				}
			}
		}
	}
	
	static void f(int idx, int cnt) {
		if (cnt==k) {
			uptAns();
			return;
		}

		for (int i=idx; i<sz; ++i) {
			list[cnt] = i;
			f(i+1, cnt+1);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		n = sc.nextInt();
		k = sc.nextInt();
		
		list = new int[k];
		board = new int[n][n];
		for (int i=0; i<n; ++i)
			for (int j=0; j<n; ++j) {
				board[i][j] = sc.nextInt();
				if (board[i][j]==2)
					virus.add(new Item(i,j));
				else if (board[i][j]==0) ++blankCnt;
			}
		
		sz = virus.size();
		
		f(0,0);
		
		System.out.println(ans<(int)1e9 ? ans : -1);

	}

}
