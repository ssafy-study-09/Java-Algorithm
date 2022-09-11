// 효율성 실패
// score빼고 네가지 조건중 하나 고른걸로 set을 만들고 score순 정렬을 하면
// O(쿼리 수 * log 사람수) 가 될 거 같음

import java.util.*;
import java.util.regex.Pattern;
    
class Solution {
    static int n;
    
    static class Item {
        String s;
        int score;
        Item(String a, int b) {
            s=a; score=b;
        }
    }
    
    public int[] solution(String[] info, String[] query) {
        n = info.length;
        Item[] arr = new Item[n];
        for (int i=0; i<n; ++i) {
            int idx = info[i].lastIndexOf(" ");
            String s = info[i].substring(0,idx);
            int score = Integer.parseInt(info[i].substring(idx+1));
            arr[i] = new Item(s, score);
        }
        Arrays.sort(arr, (a,b)->b.score-a.score);
        
        int[] answer = new int[query.length];
        int queryIdx = 0;
        for (String q: query) {
            StringBuilder regPat = new StringBuilder();
            String[] input = q.split(" ");
            if (!input[0].equals("-"))
                regPat.append(input[0]);
            else regPat.append("(cpp|java|python)");
            regPat.append(" ");
            
            if (!input[2].equals("-"))
                regPat.append(input[2]);
            else regPat.append("(backend|frontend)");
            regPat.append(" ");
            
            if (!input[4].equals("-"))
                regPat.append(input[4]);
            else regPat.append("(junior|senior)");
            regPat.append(" ");
            
            if (!input[6].equals("-"))
                regPat.append(input[6]);
            else regPat.append("(chicken|pizza)");
            
            int score = 0;
            if (!input[7].equals("-"))
                score = Integer.parseInt(input[7]);
            int cnt=0;
            for (int i=0; i<n; ++i) {
                if (arr[i].score<score) break;
                if (Pattern.matches(regPat.toString(), arr[i].s))
                    ++cnt;
            }
            answer[queryIdx++] = cnt;
        }
        return answer;
    }
}