//N=20, 완탐해도 2^20
class Solution {
  static int answer = 0, TARGET;
  static void recur(int idx, int[] numbers, int sum){
    //모든 수를 사용해봤으면 종료
    if(idx == numbers.length){
      answer += TARGET == sum ? 1 : 0;
      return;
    }
    recur(idx + 1, numbers, sum + numbers[idx]);
    recur(idx + 1, numbers, sum - numbers[idx]);
  }
  public int solution(int[] numbers, int target) {
    TARGET = target;
    recur(0, numbers, 0);
    return answer;
  }
}