import java.util.*;
class Solution {
    static ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
    static HashMap<String,Integer> hm = new HashMap<>();
    static int[] all = {4,4,4,4};
	static int[] selected = new int[4];
	static int sum = 0; int target;
	static int[] arr;
    
    public int[] solution(String[] info, String[] query) {
        for(int i=0; i<=4445; i++)
            al.add(new ArrayList<Integer>());
        int[] answer = new int[query.length];
        hm.put("cpp",1); hm.put("java",2); hm.put("python",3);
        hm.put("backend",1); hm.put("frontend",2);
        hm.put("junior",1); hm.put("senior",2);
        hm.put("chicken",1); hm.put("pizza",2);
        hm.put("and",0);
        hm.put("-",4);
        //2112
        for(int i=0; i<info.length; i++){
            String[] sl = info[i].split(" ");
            sum = 0;arr = new int[4]; selected = new int[4];
            for(int j=0; j<4; j++){
                arr[j] +=hm.get(sl[j]);
            }
            target =Integer.parseInt(sl[4]);
            subset(0);
        }
       
        for(int i=0; i<=4445; i++)
            Collections.sort(al.get(i));
        
         for(int i=0; i<query.length; i++){
            String[] sl = query[i].split(" ");
            int[] array = new int[5];
            int idx = 0;
            for(int j=0; j<=6; j++){
              int now = hm.get(sl[j]);
                 if(now == 0)
                     continue;
                array[idx++] = now;
                
            }
            array[idx] = Integer.parseInt(sl[7]);
            sum =0;
            for(int j=0; j<=3; j++){
                sum *=10;
                sum += array[j];
            }
            target = array[4];
            int index = al.get(sum).size();
            int left = 0; int right = al.get(sum).size()-1;
            
             while(left<=right){
                int mid = (left+right)/2;
                if(al.get(sum).get(mid) >= target){
                    index = mid;
                    right = mid-1;
                } else
                    left = mid+1;
                
            }
            answer[i] = al.get(sum).size()-index;
             
         }
        
        
        return answer;
    }
    void subset(int n) {
		if(n == 4) {
			sum = 0;
            
			for(int i=0; i<4; i++) {
				sum *= 10;
				sum +=selected[i];
			}
			al.get(sum).add(target);
			return;
		}
		selected[n] = all[n];
		subset(n+1);
		selected[n] = arr[n];
		subset(n+1);   
        
		
	}
}