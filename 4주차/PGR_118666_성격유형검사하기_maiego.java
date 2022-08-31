class Solution {
        public String solution(String[] survey, int[] choices) {
                int[] scores = new int['Z'];
                int n = survey.length;
                
                for (int i=0; i<n; ++i) {
                        int x = choices[i]-4;
                        if (x<0) scores[survey[i].charAt(0)] -= x;
                        else scores[survey[i].charAt(1)] += x;
                }
                
                StringBuilder sb = new StringBuilder();
                String[] mbti = {"RT", "CF", "JM", "AN"};
                
                for (String type: mbti) {
                        sb.append(scores[type.charAt(0)]>=scores[type.charAt(1)] ? type.charAt(0) : type.charAt(1));
                }
                
                return sb.toString();
        }
}
