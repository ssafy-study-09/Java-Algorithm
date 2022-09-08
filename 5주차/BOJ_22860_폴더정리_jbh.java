package week5;

import java.util.*;
import java.io.*;
public class BOJ_22860_폴더정리_jbh {

   static int n,m;
   static int cnt = 2;
   static int r1,r2;
   static ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
   static boolean[] isFolder;
   static HashMap<Integer,String> his = new HashMap<>();
   static HashMap<String,Integer> hsi = new HashMap<>();
   static HashSet<String> hs = new HashSet<>();
   public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st = new StringTokenizer(br.readLine());
         StringBuilder sb = new StringBuilder();
         
         for(int i=0; i<=10000; i++)
            al.add(new ArrayList<Integer>());
         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());
         
         isFolder = new boolean[10000];
         his.put(1, "main");
         hsi.put("main", 1);
         
         
         for(int i=0; i<n+m; i++) {
            st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            String child = st.nextToken();
            int pnum = 0; int cnum = 0;
            int num = st.nextToken().charAt(0)-'0';
            //main이 시작이 아닐 때
            //Folder는 유일하다
       //     System.out.println(hsi.containsKey("FolderA"));
            if(hsi.containsKey(parent)) {
            	 pnum = hsi.get(parent);
            }
            else {
            	pnum = cnt;
            	his.put(cnt,parent);
            	hsi.put(parent,cnt);
            	cnt++;
            }
            
          
            
            if(hsi.containsKey(child)) {
                cnum = hsi.get(child);
            }
            else if(num == 1 ) {
            	cnum = cnt;
            	his.put(cnt,child);
            	hsi.put(child,cnt);
            	cnt++;
            }
            else {
               cnum = cnt;
               his.put(cnt, child);
               
               cnt++;
            }
//               cnum = cnt;
//               his.put(cnt, child);
//               hsi.put(child, cnt++);
//            
            
            if(num == 1)
            	isFolder[cnum] = true;
            al.get(pnum).add(cnum);
            
            
            
            
         }
      //   System.out.println(hsi);
      //   System.out.println(his);
         int q = Integer.parseInt(br.readLine());
         
         for(int i=0; i<q; i++) {
        	hs.clear(); r1= 0; r2 = 0;
            String[] sl =br.readLine().split("/");
            String s = sl[sl.length-1];
            int now = hsi.get(s);
       //     System.out.println(now);
            dfs(now);
            r1 = hs.size();
            sb.append(r1+" "+r2+"\n");
         }
        // System.out.println("--");
         System.out.println(sb.toString());
   }
   static void dfs(int now) {
	   for(int i=0; i<al.get(now).size(); i++) {
		   int next = al.get(now).get(i);
		   if(isFolder[next]) {
			   dfs(next);
		   } else {
			   hs.add(his.get(next));
			   r2++;
		   }
		   
	   }
	   
   }

}