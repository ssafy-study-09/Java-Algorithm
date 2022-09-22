import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int N;
    static int Q;
    static int maxCapacity;
    static int curCapacity;
    static Cache cache;
    static int[] pageCapacity;
    static int current;

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputs(bufferedReader);

            for (int i = 0; i < Q; i++) {
                StringTokenizer inputLine = new StringTokenizer(bufferedReader.readLine());
                char command = inputLine.nextToken().charAt(0);

                switch (command) {
                    case 'A':
                        int idx = Integer.parseInt(inputLine.nextToken());

                        curCapacity -= cache.deleteForwards();
                        if (cache.getPagesSize() > 0) {
                            cache.getBackwards().add(current);
                        }
                        cache.getPages().add(idx);
                        current = idx;
                        curCapacity += pageCapacity[idx];

                        while (curCapacity > maxCapacity && cache.getBackwardsSize() > 0) {
                            idx = cache.getBackwards().pollFirst();
                            curCapacity -= pageCapacity[idx];
                        }

                        break;

                    case 'B':
                        if (cache.getBackwardsSize() > 0) {
                            cache.getForwards().add(current);
                            current = cache.getBackwards().pollLast();
                        }

                        break;

                    case 'F':
                        if (cache.getForwardsSize() > 0) {
                            cache.getBackwards().add(current);
                            current = cache.getForwards().pollLast();
                        }
                        break;

                    case 'C':
                        cache.doDistinctBackwards();

                        break;
                }

            }

            printPagesInCache();
        }
    }

    private static void printPagesInCache() {
        System.out.println(current);
        cache.printBackwards();
        cache.printForwards();
    }

    private static void inputs(BufferedReader bufferedReader) throws IOException {
        StringTokenizer inputLine = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(inputLine.nextToken());
        Q = Integer.parseInt(inputLine.nextToken());
        maxCapacity = Integer.parseInt(inputLine.nextToken());

        pageCapacity = new int[N + 1];
        inputLine = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= N; i++) {
            pageCapacity[i] = Integer.parseInt(inputLine.nextToken());
        }

        cache = new Cache();
    }

    static class Cache {
        private Deque<Integer> backwards = new LinkedList<>();
        private Deque<Integer> forwards = new LinkedList<>();
        private Deque<Integer> pages = new LinkedList<>();

        private int getBackwardsSize() {
            return backwards.size();
        }

        private int getForwardsSize() {
            return forwards.size();
        }

        private int getPagesSize() {
            return pages.size();
        }

        private int getCurrentPage() {
            return pages.pollLast();
        }

        private int getCapacity() {
            return backwards.size() + forwards.size() + pages.size();
        }

        public Deque<Integer> getBackwards() {
            return backwards;
        }

        public Deque<Integer> getForwards() {
            return forwards;
        }

        public Deque<Integer> getPages() {
            return pages;
        }

        public void doDistinctBackwards() {
            int prev = 0;
            Deque<Integer> temp = new LinkedList<>();
            while (!backwards.isEmpty()) {
                int poll = backwards.pollLast();
                if (prev == poll) {
                    curCapacity -= pageCapacity[poll];

                    continue;
                }

                temp.addFirst(poll);
                prev = poll;
            }

            backwards = temp;
        }

        public void printBackwards() {
            if (backwards.isEmpty()) {
                System.out.println(-1);

                return;
            }

            while (!backwards.isEmpty()) {
                System.out.print(backwards.pollLast() + " ");
            }
            System.out.println();
        }

        public void printForwards() {
            if (forwards.isEmpty()) {
                System.out.println(-1);

                return;
            }

            while (!forwards.isEmpty()) {
                System.out.print(forwards.pollLast() + " ");
            }
            System.out.println();
        }

        public int deleteForwards() {
            int sum = 0;
            while (!forwards.isEmpty()) {
                Integer idx = forwards.poll();
                sum += pageCapacity[idx];
            }

            return sum;
        }
    }

}
