import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    static int N;
    static int T;
    static int W;
    static int M;

    static Queue<Client> clients;
    static TreeMap<Integer, Client> clientsPool;

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputs(bufferedReader);

            StringBuilder answer = getBankingJob();
            System.out.print(answer);
        }
    }

    private static StringBuilder getBankingJob() {
        StringBuilder answer = new StringBuilder();
        int currentTime = 0;
        while (currentTime < W) {
            Client currentClient = clients.poll();

            if (currentClient.getTime() > T) {
                for (int i = 0; i < T; i++, currentTime++) {
                    if (currentTime >= W) {
                        return answer;
                    }

                    answer.append(currentClient.getId() + "\n");
                }
            } else {
                for (int i = 0; i < currentClient.getTime(); i++, currentTime++) {
                    if (currentTime >= W) {
                        return answer;
                    }

                    answer.append(currentClient.getId() + "\n");
                }
            }

            while (!clientsPool.isEmpty() && clientsPool.firstKey() <= currentTime) {
                clients.add(clientsPool.pollFirstEntry().getValue());
            }

            if (currentClient.getTime() > T) {
                currentClient.setTime(T);
                clients.add(currentClient);
            }
        }
        return answer;
    }

    private static void inputs(BufferedReader bufferedReader) throws IOException {
        StringTokenizer inputLine = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(inputLine.nextToken());
        T = Integer.parseInt(inputLine.nextToken());
        W = Integer.parseInt(inputLine.nextToken());

        clients = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            inputLine = new StringTokenizer(bufferedReader.readLine());

            int id = Integer.parseInt(inputLine.nextToken());
            int time = Integer.parseInt(inputLine.nextToken());
            clients.add(new Client(id, time));
        }

        clientsPool = new TreeMap<>();
        M = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < M; i++) {
            inputLine = new StringTokenizer(bufferedReader.readLine());
            int id = Integer.parseInt(inputLine.nextToken());
            int time = Integer.parseInt(inputLine.nextToken());
            int inputTime = Integer.parseInt(inputLine.nextToken());

            clientsPool.put(inputTime, new Client(id, time));
        }

    }

    static class Client {

        private final int id;
        private int time;

        public Client(int id, int time) {
            this.id = id;
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time -= time;
        }
    }

}
