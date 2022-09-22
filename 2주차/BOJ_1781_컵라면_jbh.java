import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<xy> pq = new PriorityQueue<xy>();
		PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
		int now = n+1; int sum =0;
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pq.add(new xy(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			
		}
		while(now >1) {
			now -=1;
			while(!pq.isEmpty() && pq.peek().x == now) {
				xy xy = pq.poll();
				max.add(xy.y);
			}
			if(!max.isEmpty())
				sum += max.poll();
			
		}
		System.out.println(sum);
		
		
		
	}
	static class xy implements Comparable<xy>{
		int x;int y;
		public xy(int x,int y) {
			this.x=x;
			this.y=y;
		}
		@Override
		public int compareTo(xy o) {
			if(this.x==o.x)
				return o.y-this.y;
			return o.x-this.x;
		}
	}

}