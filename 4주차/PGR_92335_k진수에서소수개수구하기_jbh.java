import java.util.*;

class Solution {
    static StringBuilder sb = new StringBuilder();
    boolean isPrime(long target){
        if(target == 1)
            return false;
        for(long i=2; i*i<=target; i++){
            if(target % i == 0)
                return false;
        }  
        return true;
    }
    public int solution(int n, int k) {
        int answer = 0;
        String s = Integer.toString(n,k);
        int idx = 0;
        while(idx < s.length()){
            if(s.charAt(idx)-'0' == 0){
                if(sb.length() == 0){
                    idx +=1;
                    continue;
                }
                    
                long now = Long.parseLong(sb.toString());
                if(isPrime(now))
                    answer++;
                sb.setLength(0);
                idx +=1;
                continue;
            }
            sb.append(s.charAt(idx++)-'0');
        }
        if(sb.length() != 0){
             long now = Long.parseLong(sb.toString());
                if(isPrime(now))
                    answer++;
        }
       
        return answer;
    }
}