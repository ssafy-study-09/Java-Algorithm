class Solution {
    static boolean[] isPlus;
    static int totalCnt;
    public int solution(int[] numbers, int target) {
        totalCnt=0;
        int size = numbers.length;
        isPlus = new boolean[size];
        subset(0,size,target,numbers);
        return totalCnt;
    }
    
    public static void subset(int cnt,int limit,int target,int[] numbers){
        if(cnt==limit){
            int sum=0;
            for(int i=0;i<limit;i++){
                sum += (isPlus[i] ? numbers[i] : -numbers[i]);
            }
            if(sum==target)    totalCnt++;
            return;
        }
        
        isPlus[cnt]=true;
        subset(cnt+1,limit,target,numbers);
        isPlus[cnt]=false;
        subset(cnt+1,limit,target,numbers);
    }
}