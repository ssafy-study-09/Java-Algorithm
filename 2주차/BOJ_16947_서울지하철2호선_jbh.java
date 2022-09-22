import java.util.*;
import java.io.*;
public class Main {

   static int n;
   static boolean[] isCycle,temp;
   static boolean fc = false;
   static ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
   static StringBuilder sb = new StringBuilder();
   static void findCycle(int start,int now,int cnt) {
      if(fc)
         return;
      for(int i=0; i<al.get(now).size(); i++) {
         int next = al.get(now).get(i);
         if(next == start && cnt >2) {
            fc = true;
            for(int j=1; j<=n; j++)
               isCycle[j] = temp[j];
            
            return;
         }
         if(!temp[next]) {
            temp[next] = true;
            findCycle(start,next,cnt+1);
            temp[next] = false;
         }
      }
   }
   static int bfs(int start) {
	   boolean[] visited = new boolean[n+1];
	   visited[start] = true;
	   Queue<Integer> q = new LinkedList<>();
	   q.add(start);
	   int cnt = -1;
	   Loop1:
	   while(!q.isEmpty()) {
		   int size = q.size();
		   cnt+=1;
		   for(int i=0; i<size; i++) {
			   int now = q.poll();
			   if(isCycle[now]) {
				   break Loop1;
			   }
			   for(int j=0; j<al.get(now).size(); j++) {
				   int next = al.get(now).get(j);
				   if(!visited[next]) {
					   visited[next] =true;
					   q.add(next);
				   }
				   
			   }
			   
			   
		   }
		   
	   }
	   return cnt;
   }
   public static void main(String[] args) throws  IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      n = Integer.parseInt(br.readLine());
      isCycle = new boolean[n+1];
      for(int i=0; i<=n+1; i++)
         al.add(new ArrayList<Integer>());
      
      
      for(int i=0; i<n; i++) {
         StringTokenizer st = new StringTokenizer(br.readLine());
         int a  = Integer.parseInt(st.nextToken());
         int b  = Integer.parseInt(st.nextToken());
         al.get(a).add(b);
         al.get(b).add(a);
      }
      for(int i=1; i<=n; i++) {
         if(fc)
            break;
         temp = new boolean[n+1];
         temp[i] = true;
         findCycle(i,i,1);
      }
      for(int i=1; i<=n; i++) {
    	  if(isCycle[i]) {
    		  sb.append(0+" ");
    		  continue;
    	  }
    	  sb.append(bfs(i)+" ");  
    	  
      }
      System.out.println(sb.toString());
      

   }

}