import java.io.*;
import java.util.*;
class Solution {
    static Map<Character, Integer> map = new HashMap<>();
    public String solution(String[] survey, int[] choices) {
        map.put('R', 0); map.put('T', 0);
        map.put('C', 0); map.put('F', 0);
        map.put('J', 0); map.put('M', 0);
        map.put('A', 0); map.put('N', 0);
        for(int i = 0; i < survey.length; i++){
            String s = survey[i];
            Character l = s.charAt(0);
            Character r = s.charAt(1);
            int choice = choices[i];
            int point = Math.abs(choice - 4);
            if(point == 0) continue;
            else if(choice > 4){
                map.put(r, map.get(r) + point);
            }
            else{
                map.put(l, map.get(l) + point);
            }
        }
        //점수가 더 높은 유형, 같으면 사전순
        StringBuilder sb = new StringBuilder();
        if(map.get('R') >= map.get('T')){
            sb.append('R');
        }
        else{
            sb.append('T');
        }
        if(map.get('C') >= map.get('F')){
            sb.append('C');
        }
        else{
            sb.append('F');
        }
        if(map.get('J') >= map.get('M')){
            sb.append('J');
        }
        else{
            sb.append('M');
        }
        if(map.get('A') >= map.get('N')){
            sb.append('A');
        }
        else{
            sb.append('N');
        }
        return sb.toString();
    }
}