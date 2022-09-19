package week6;
import java.util.*;
import java.io.*;
public class BOJ_1263_시간관리_jbh {

   static int n;
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      n  = Integer.parseInt(br.readLine());
      
      PriorityQueue<xy> pq = new PriorityQueue<>();
      for(int i=0; i<n; i++) {
         StringTokenizer st = new StringTokenizer(br.readLine());
         int a= Integer.parseInt(st.nextToken());
         int b= Integer.parseInt(st.nextToken());
         pq.add(new xy(a,b));
      }
      int now = pq.peek().y;
      while(!pq.isEmpty() && now >=0) {
    	  xy xy = pq.poll();
    	  int time = xy.x; int deadline = xy.y;
    	  if(now > deadline)
    		  now = deadline;
    	  now -= time;
      }
      System.out.println((now >=0) ? now : -1);
   }
   static class xy implements Comparable<xy>{
      int x;int y;
      public xy(int x,int y) {
         this.x=x; this.y=y;
      }
	   @Override
	   public int compareTo(xy o) {
	      return o.y-this.y;
	   }
   }
}