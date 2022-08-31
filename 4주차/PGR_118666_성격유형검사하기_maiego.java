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
        if (scores['R']>=scores['T'])
            sb.append('R');
        else sb.append('T');
        if (scores['C']>=scores['F'])
            sb.append('C');
        else sb.append('F');
        if (scores['J']>=scores['M'])
            sb.append('J');
        else sb.append('M');
        if (scores['A']>=scores['N'])
            sb.append('A');
        else sb.append('N');
        return sb.toString();
    }
}
