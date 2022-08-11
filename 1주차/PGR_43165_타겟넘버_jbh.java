class Solution {
    static int answer = 0;
    static int n;
    
    public void dfs(int[] numbers,int cnt, int sum,int target){
        if(cnt == n){
            if(sum == target)
                answer++;
            return;
        }
        dfs(numbers,cnt+1,sum+numbers[cnt],target);
         dfs(numbers,cnt+1,sum+(numbers[cnt] * -1),target);
    }
    public int solution(int[] numbers, int target) {
        n = numbers.length;
        dfs(numbers,0,0,target);
        
        
        return answer;
    }
}