import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Job[] jobs;
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputs(bufferedReader);

            int leastTime = jobs[0].getSi() - jobs[0].getTi();
            for (int i = 1; i < N; i++) {
                if (jobs[i].getSi() < leastTime) {
                    leastTime = jobs[i].getSi();
                }

                leastTime -= jobs[i].getTi();
            }

            System.out.println(leastTime < 0 ? -1 : leastTime);
        }
    }

    private static void inputs(BufferedReader bufferedReader) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        jobs = new Job[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer inputLine = new StringTokenizer(bufferedReader.readLine());
            int Ti = Integer.parseInt(inputLine.nextToken());
            int Si = Integer.parseInt(inputLine.nextToken());

            jobs[i] = new Job(Ti, Si);
        }

        Arrays.sort(jobs, Comparator.comparing(Job::getSi).reversed());
    }

    static class Job {
        private final int Ti;
        private final int Si;

        public Job(int ti, int si) {
            Ti = ti;
            Si = si;
        }

        public int getTi() {
            return Ti;
        }

        public int getSi() {
            return Si;
        }
    }

}