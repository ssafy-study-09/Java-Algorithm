import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int fuel;

    static int[][] map;
    static Point taxi;
    static Map<Integer, Passenger> passengers;

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            inputs(bufferedReader);

            for (int i = 0; i < M; i++) {
                int idx = findPassenger();
                if (idx == -1) {
                    fuel -= (int) 1e9;

                    break;
                }

                Passenger passenger = passengers.get(idx);
                map[passenger.getFromX()][passenger.getFromY()] = 0;

                passenger.setFromDistance();
                fuel -= passenger.getFromDistance();
                if (fuel < 0) {
                    break;
                }
                passenger.setToDistance();
                setTaxi(passenger.getTo());
                fuel -= passenger.getToDistance();
                if (fuel < 0) {
                    break;
                }
                fuel += passenger.getToDistance() * 2;
            }

            System.out.println(fuel < 0 ? -1 : fuel);
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static int findPassenger() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(taxi.getX(), taxi.getY(), 0));
        boolean[][] visit = new boolean[N][N];
        visit[taxi.getX()][taxi.getY()] = true;
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>(Comparator.comparing(Point::getDistance)
            .thenComparing(Point::getX)
            .thenComparing(Point::getY));

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (map[cur.getX()][cur.getY()] >= 1) {
                priorityQueue.add(cur);
            }
            for (int i = 0; i < 4; i++) {
                int nextX = cur.getX() + dx[i];
                int nextY = cur.getY() + dy[i];

                if (isInMap(nextX, nextY) && !visit[nextX][nextY] && map[nextX][nextY] >= 0) {
                    queue.add(new Point(nextX, nextY, cur.getDistance() + 1));
                    visit[nextX][nextY] = true;
                }
            }
        }

        if (priorityQueue.isEmpty()) {
            return -1;
        }
        Point poll = priorityQueue.poll();
        return map[poll.getX()][poll.getY()];
    }

    private static void setTaxi(Point to) {
        taxi.setX(to.getX());
        taxi.setY(to.getY());
    }

    private static int bfs(Point from, Point to) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visit = new boolean[N][N];
        queue.add(new Point(from.getX(), from.getY(), 0));

        int ret = (int) 1e9;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (isArrived(cur, to)) {
                ret = cur.getDistance();

                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = cur.getX() + dx[i];
                int nextY = cur.getY() + dy[i];

                if (isInMap(nextX, nextY) && !visit[nextX][nextY] && map[nextX][nextY] >= 0) {
                    queue.add(new Point(nextX, nextY, cur.getDistance() + 1));
                    visit[nextX][nextY] = true;
                }
            }
        }

        return ret;
    }

    private static boolean isInMap(int nextX, int nextY) {
        return nextX >= 0 && nextY >= 0 && nextX < N && nextY < N;
    }

    private static boolean isArrived(Point cur, Point destination) {
        return cur.getX() == destination.getX() && cur.getY() == destination.getY();
    }

    private static void inputs(BufferedReader bufferedReader) throws IOException {
        StringTokenizer inputLine = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(inputLine.nextToken());
        M = Integer.parseInt(inputLine.nextToken());
        fuel = Integer.parseInt(inputLine.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            inputLine = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(inputLine.nextToken());
                if (map[i][j] == 1) {
                    map[i][j] = -1;
                }
            }
        }
        inputLine = new StringTokenizer(bufferedReader.readLine());
        int x = Integer.parseInt(inputLine.nextToken()) - 1;
        int y = Integer.parseInt(inputLine.nextToken()) - 1;

        taxi = new Point(x, y, 0);

        passengers = new HashMap<>();
        for (int i = 0; i < M; i++) {
            inputLine = new StringTokenizer(bufferedReader.readLine());
            int fromX = Integer.parseInt(inputLine.nextToken()) - 1;
            int fromY = Integer.parseInt(inputLine.nextToken()) - 1;
            Point from = new Point(fromX, fromY, 0);
            int toX = Integer.parseInt(inputLine.nextToken()) - 1;
            int toY = Integer.parseInt(inputLine.nextToken()) - 1;
            Point to = new Point(toX, toY, 0);

            map[fromX][fromY] = i + 1;
            passengers.put(i + 1, new Passenger(from, to));
        }
    }

    static class Passenger {
        private Point from;
        private Point to;

        public Passenger(Point from, Point to) {
            this.from = from;
            this.to = to;
        }

        public int getFromX() {
            return from.getX();
        }

        public int getFromY() {
            return from.getY();
        }

        public Point getFrom() {
            return from;
        }

        public Point getTo() {
            return to;
        }

        public int getFromDistance() {
            return from.getDistance();
        }

        public int getToDistance() {
            return to.getDistance();
        }

        public void setFromDistance() {
            from.setDistance(bfs(taxi, from));
        }

        public void setToDistance() {
            to.setDistance(bfs(from, to));
        }
    }

    static class Point {
        private int x;
        private int y;
        private int distance;

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getDistance() {
            return distance;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
}
