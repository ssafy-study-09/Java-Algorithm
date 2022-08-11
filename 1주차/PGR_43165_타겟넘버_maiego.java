class Solution {
    static int ans;
    static int[] arr;
    static int k;
    static int sz;
    
    static void f(int idx, int sum) {
        if (idx==sz) {
            if (sum==k) ++ans;
            return;
        }
        f(idx+1, sum+arr[idx]);
        f(idx+1, sum-arr[idx]);
    }
    
    public int solution(int[] numbers, int target) {
        k = target;
        arr = numbers;
        sz = numbers.length;
        f(0, 0);
        return ans;
    }
}