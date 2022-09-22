import java.util.*;

public class Main {
	static int n;
	
	static char[][] board;
	static int[][] ans;
	static int[][][] vis;
	
	static class Item {
		int y,x, dir, cnt;
		
		Item(int a, int b, int c, int d) {
			y=a; x=b; dir=c; cnt=d;
		}
	}
	
	static boolean inRange(int y, int x) {
		return y>=0 && x>=0 && y<n && x<n && board[y][x]!='*';
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		board = new char[n][];
		for (int i=0; i<n; ++i)
			board[i] = sc.next().toCharArray();
		
		int startY=-1, startX=-1;
		int endY=0, endX=0;
		for (int i=0; i<n; ++i) {
			for (int j=0; j<n; ++j) {
				if (board[i][j]=='#') {
					if (startY==-1) {
						startY=i;
						startX=j;
					} else {
						endY=i;
						endX=j;
					}
				}
			}
		}
		
		ans = new int[n][n];
		for (int i=0; i<n; ++i)
			for (int j=0; j<n; ++j)
				ans[i][j] = 10000;
		
		Deque<Item> q = new ArrayDeque<>();
		q.add(new Item(startY, startX, 0, 0));
		q.add(new Item(startY, startX, 1, 0));
		
		vis = new int[n][n][2];
		for (int i=0; i<n; ++i)
			for (int j=0; j<n; ++j) {
				vis[i][j][0] = 10000;
				vis[i][j][1] = 10000;
			}

		while (!q.isEmpty()) {
			Item item = q.poll();
			int y = item.y;
			int x = item.x;
			int dir = item.dir;
			int cnt = item.cnt;
//			System.out.println(y + ", " + x + ", " + dir + ", " + cnt);
			
			if (vis[y][x][dir]<cnt) continue;
			
			if (dir==0) {
				if (inRange(y+1, x) && cnt<vis[y+1][x][0]) {
					q.add(new Item(y+1,x, 0, cnt));
					vis[y+1][x][0] = cnt;
				}
				if (inRange(y-1, x) && cnt<vis[y-1][x][0]) {
					q.add(new Item(y-1,x, 0, cnt));
					vis[y-1][x][0] = cnt;
				}
			} else {
				if (inRange(y, x+1) && cnt<vis[y][x+1][1]) {
					q.add(new Item(y,x+1, 1, cnt));
					vis[y][x+1][1] = cnt;
				}
				if (inRange(y, x-1) && cnt<vis[y][x-1][1]) {
					q.add(new Item(y,x-1, 1, cnt));
					vis[y][x-1][1] = cnt;
				}
			}
			if (board[y][x]=='!') {
				if (cnt+1<vis[y][x][dir^1]) {
					q.add(new Item(y,x, dir^1, cnt+1));
					vis[y][x][dir^1] = cnt+1;
				}
			}
			
		}
		System.out.println(Math.min(vis[endY][endX][0], vis[endY][endX][1]));
	}
}