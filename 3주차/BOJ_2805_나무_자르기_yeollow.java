import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int[] heights;

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputs(bufferedReader);

            System.out.println(binarySearch());
        }
    }

    private static int binarySearch() {
        int left = 0;
        int right = getMaxTreeHeight();
        while (left <= right) {
            int mid = (left + right) / 2;
            long sum = getCuttingTreesHeight(mid);

            if (sum >= M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    private static int getMaxTreeHeight() {
        return Arrays.stream(heights).max().getAsInt();
    }

    private static long getCuttingTreesHeight(int mid) {
        long answer = 0;
        for (int i = 0; i < N; i++) {
            int diff = heights[i] - mid;
            if (diff > 0) {
                answer += diff;
            }
        }

        return answer;
    }

    private static void inputs(BufferedReader bufferedReader) throws IOException {
        StringTokenizer inputLine = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(inputLine.nextToken());
        M = Integer.parseInt(inputLine.nextToken());

        heights = new int[N];
        inputLine = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(inputLine.nextToken());
        }
    }
}