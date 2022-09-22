import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Character, Integer> types;

    public String solution(String[] surveys, int[] choices) {
        init();
        int len = surveys.length;
        for (int i = 0; i < len; i++) {
            checkType(surveys[i], choices[i]);
        }

        String answer = getAnswer();

        return answer;
    }

    private void init() {
        types = new HashMap<>();
        types.put('R', 0);
        types.put('T', 0);
        types.put('C', 0);
        types.put('F', 0);
        types.put('J', 0);
        types.put('M', 0);
        types.put('A', 0);
        types.put('N', 0);
    }

    private String getAnswer() {
        StringBuilder builder = new StringBuilder();
        if (types.get('R') >= types.get('T')) {
            builder.append("R");
        } else {
            builder.append("T");
        }

        if (types.get('C') >= types.get('F')) {
            builder.append("C");
        } else {
            builder.append("F");
        }

        if (types.get('J') >= types.get('M')) {
            builder.append("J");
        } else {
            builder.append("M");
        }

        if (types.get('A') >= types.get('N')) {
            builder.append("A");
        } else {
            builder.append("N");
        }
        return String.valueOf(builder);

    }

    private void checkType(String survey, int choice) {
        char agree = survey.charAt(0);
        char disAgree = survey.charAt(1);

        switch (choice) {
            case 1:
                types.put(agree, types.getOrDefault(agree, 0) + 3);
                break;
            case 2:
                types.put(agree, types.getOrDefault(agree, 0) + 2);
                break;
            case 3:
                types.put(agree, types.getOrDefault(agree, 0) + 1);
                break;
            case 4:
                break;
            case 5:
                types.put(disAgree, types.getOrDefault(disAgree, 0) + 1);
                break;
            case 6:
                types.put(disAgree, types.getOrDefault(disAgree, 0) + 2);
                break;
            case 7:
                types.put(disAgree, types.getOrDefault(disAgree, 0) + 3);
                break;
        }
    }
}
