import java.io.*;
import java.util.*;
class Solution {
    public static HashMap<String, Integer> m = new HashMap<>();
    public static void findNum(String str){
        String[] nums = str.split("0");
        for(int i = 0; i < nums.length; i++){
            if(nums[i].length() == 0) continue;
            m.put(nums[i], m.getOrDefault(nums[i], 0) + 1);
        }
    }
    public static boolean isPrime(String str){
        long num = Long.parseLong(str);
        if(num == 1) return false;
        else if(num == 2) return true;
        for(long i = 3; i <= (int)Math.sqrt(num); i += 2){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
    public static int countPrime(){
        int cnt = 0;
        for (String key : m.keySet()){
            if(isPrime(key)){
                cnt += m.get(key);
            }
        }
        return cnt;
    }
    public static String convert(int n, int k){
        StringBuilder sb = new StringBuilder();
        while(n != 0){
            if(n % k == 0){
                sb.append(0);
            }
            else{
                sb.append(n % k);
            }
            n /= k;
        }
        return sb.reverse().toString();
    }
    public int solution(int n, int k) {
        String digit = convert(n, k);
        findNum(digit);
        int answer = countPrime();
        return answer;
    }
}