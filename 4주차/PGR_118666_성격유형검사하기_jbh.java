import java.util.*;
class Solution {
    static HashMap<String,Integer> hm = new HashMap<>();
    static int[] v= new int[4];
    static StringBuilder sb = new StringBuilder();
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        hm.put("RT",0); hm.put("TR",0); hm.put("FC",0); 
        hm.put("CF",0); hm.put("MJ",0); hm.put("JM",0);
        hm.put("AN",0); hm.put("NA",0);
        
        for(int i=0; i<survey.length; i++){
            String s = survey[i]; int num = choices[i]-4;
            hm.put(s,hm.get(s)+num);
            
        }
        v[0] = hm.get("RT") - hm.get("TR");
        v[1] = hm.get("CF") - hm.get("FC");
        v[2] = hm.get("JM") - hm.get("MJ");
        v[3] = hm.get("AN") - hm.get("NA");
        String s1 = (v[0]  > 0) ? "T" : "R";
        String s2 = (v[1]  > 0) ? "F" : "C";
        String s3 = (v[2]  > 0) ? "M" : "J";
        String s4 = (v[3]  > 0) ? "N" : "A";
       
        sb.append(s1+s2+s3+s4);
        return sb.toString();
    }
}