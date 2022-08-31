import java.util.*;

class Solution {
        static boolean isPrime(long x) {
                if (x==1 || x==0) return false;
                for (long i=2; i*i<=x; ++i) {
                        if (x%i == 0) return false;
                }
                return true;
        }
        
        public int solution(int n, int k) {
                String s = Integer.toString(n,k);
                
                int cnt=0;
                String[] arr = s.split("0");
                for (String ss: arr) {
                        if (ss.isEmpty()) continue;
                        long x = Long.parseLong(ss);
                        if (isPrime(x)) ++cnt;
                }
                
                return cnt;
        }
}
