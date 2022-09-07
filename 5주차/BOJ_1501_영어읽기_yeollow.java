import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static Map<String, Integer> words;

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputs(bufferedReader);

            for (int i = 0; i < M; i++) {
                System.out.println(getParseCount(bufferedReader.readLine()));
            }
        }
    }

    private static int getParseCount(String input) {
        int answer = 1;
        StringTokenizer inputLine = new StringTokenizer(input);
        while (inputLine.hasMoreTokens()) {
            int count = 0;
            String key = makeWord(inputLine.nextToken());
            count = words.getOrDefault(key, 0);

            answer *= count;
        }

        return answer;
    }

    private static void inputs(BufferedReader bufferedReader) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        words = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String key = makeWord(bufferedReader.readLine());
            words.put(key, words.getOrDefault(key, 0) + 1);
        }

        M = Integer.parseInt(bufferedReader.readLine());
    }

    private static String makeWord(String input) {
        StringBuilder builder = new StringBuilder();
        builder.append(input.charAt(0));
        if (input.length() >= 2) {
            builder.append(input.charAt(input.length() - 1));
            if (input.length() >= 3) {
                char[] chars = input.substring(1, input.length() - 1).toCharArray();
                Arrays.sort(chars);
                for (char ch : chars) {
                    builder.append(ch);
                }
            }
        }

        return String.valueOf(builder);
    }
}
