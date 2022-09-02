import java.util.*;
import java.io.*;
public class Main {
	static int cnt;
    static int n,q,c;    
    static StringBuilder sb = new StringBuilder();
    static Stack<Integer> forward= new Stack<>();       
    static Deque<Integer> backward= new ArrayDeque<>();  
    static int now = -1;
    static int[] cash; 
    static int fcash,bcash,csum;
   public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           StringTokenizer st = new StringTokenizer(br.readLine());
           n = Integer.parseInt(st.nextToken());   
           q = Integer.parseInt(st.nextToken());   
           c = Integer.parseInt(st.nextToken());
           cash = new int[n+1];
           st = new StringTokenizer(br.readLine());
          
           for (int i = 1; i <= n; i++) {
               cash[i] = Integer.parseInt(st.nextToken());
           }
           for(int i=0; i<q; i++) {
               st = new StringTokenizer(br.readLine());
              char ch = st.nextToken().charAt(0);
              int num = 0;
              if(ch =='A')
                 num = Integer.parseInt(st.nextToken());
              play(ch,num);
           }
           
           System.out.println(now);
           if(backward.isEmpty()) {
        	   System.out.println(-1);
           } else {
        	   while(!backward.isEmpty())
        		   System.out.print(backward.pollLast()+" ");
        	   System.out.println();
           }
           if(forward.isEmpty()) {
        	   System.out.println(-1);
           } else {
        	   while(!forward.isEmpty())
        		   System.out.print(forward.pop()+" ");
        	   System.out.println();
           }
           
   }
   static void playB() {
      if(backward.isEmpty())
         return;
      int visiting = now;
      forward.add(visiting);
      fcash += cash[visiting];
      now = backward.pollLast();
      bcash -= cash[now];
   }
   static void playF() {
      if(forward.isEmpty())
         return;
      int visiting = now;
      backward.add(visiting);
      bcash += cash[visiting];
      now = forward.pop();
      fcash -= cash[now];
   }
   static void playA(int num) {
	   forward.clear();
	   fcash = 0;
	   
	   int visiting = now;
	   if(now != -1) {
		   backward.add(visiting);
		   bcash += cash[visiting];
	   }
	   now = num;
	   csum = cash[now] + fcash + bcash;
	
	   while(csum  > c && !backward.isEmpty()) {
		   
		   int last = backward.pollFirst();
		   bcash -= cash[last];
		   csum -= cash[last];
	   }
   }
   static void playC() {
	   Stack<Integer> stack = new Stack<>();
	   int first = 0;
	   if(!backward.isEmpty()) {
		   first = backward.pollLast();
		   stack.add(first);
	   } else
		   return;
	    boolean same = false;
	   while(!backward.isEmpty()) {
		   int pop = backward.pollLast();
		   if(pop == stack.peek()) {
			   bcash -= cash[pop];
			   continue;
		   } else {
			   stack.add(pop);
		   }
	   }
	   while(!stack.isEmpty())
		   backward.add(stack.pop());
	   
   }
   static void play(char ch,int num) {
	  cnt++;
	   
      if(ch =='B')
         playB();
      else if(ch=='F')
         playF();
      else if(ch =='A')
    	  playA(num);
      else {
    	  playC();
      }
    	  
    
   }   
}