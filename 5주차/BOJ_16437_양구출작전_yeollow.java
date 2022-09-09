import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Animal[] animals;
    static List<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputs(bufferedReader);

            System.out.println(postOrder(1));
        }
    }

    private static long postOrder(int start) {
        long sum = 0;
        for (Integer next : adjList[start]) {
            sum += postOrder(next);
        }

        if (animals[start].getType().equals("S")) {
            return sum + animals[start].getAmount();
        }

        return (sum - animals[start].getAmount()) > 0 ? sum - animals[start].getAmount() : 0;
    }

    private static void inputs(BufferedReader bufferedReader) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        animals = new Animal[N + 1];
        adjList = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        animals[1] = new Animal("", 0);
        for (int i = 2; i <= N; i++) {
            StringTokenizer inputLine = new StringTokenizer(bufferedReader.readLine());
            String type = inputLine.nextToken();
            int amount = Integer.parseInt(inputLine.nextToken());
            int next = Integer.parseInt(inputLine.nextToken());

            animals[i] = new Animal(type, amount);
            adjList[next].add(i);
        }
    }

    static class Animal {
        private final String type;
        private final int amount;

        public Animal(String type, int amount) {
            this.type = type;
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public int getAmount() {
            return amount;
        }
    }

}
