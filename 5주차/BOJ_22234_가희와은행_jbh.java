package week5;
import java.util.*;
import java.io.*;
public class BOJ_22234_°¡Èñ¿ÍÀºÇà_jbh {
	static int n,t,w,m;
	static PriorityQueue<xyd> pq = new PriorityQueue<>();
	static StringBuilder sb = new StringBuilder();
	static int time = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n =  Integer.parseInt(st.nextToken());
		t =  Integer.parseInt(st.nextToken());
		w =  Integer.parseInt(st.nextToken());
		Queue<xy> q = new LinkedList<>();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			q.add(new xy(a,b));
		}
		m = Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.add(new xyd(a,b,c));
		}
		Loop1 :
		while(!q.isEmpty()) {
			xy now = q.poll();
			int np = now.x; int nt = now.y;
		//	System.out.println(np+","+nt);
			if(nt <=t) {
				for(int i=0; i<nt; i++) {
					time +=1;
					sb.append(np+"\n");
					if(time >= w)
						break Loop1;
				}
					
				while(!pq.isEmpty() && pq.peek().d <=time) {
					xyd xyd = pq.poll();
					q.add(new xy(xyd.x,xyd.y));
				}
			} else {
				for(int i=0; i<t; i++) {
					time +=1;
					sb.append(np+"\n");
					if(time >= w)
						break Loop1;
				}
					
				while(!pq.isEmpty() && pq.peek().d <=time) {
					xyd xyd = pq.poll();
					q.add(new xy(xyd.x,xyd.y));
				}
				q.add(new xy(np,nt-t));
			}
		}
		
		
		System.out.println(sb.toString());
	}
	
	static class xy {
		int x;
		int y;

		public xy(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static class xyd implements Comparable<xyd>{
		int x;
		int y;
		int d;
		public xyd(int x, int y,int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		@Override
		public int compareTo(xyd o) {
			return this.d - o.d;
		}
	}
}
