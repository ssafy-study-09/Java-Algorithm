import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_19238_스타트택시_ssu {
    static int N;

    static int[] dxs = {-1,+1,0,0};
    static int[] dys = {0,0,-1,+1};
    static int[][] map;
    static int[][] dist;
    static Taxi taxi;
    static List<Person> personArr;
    static PriorityQueue<Person> pq;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int initFuel = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        dist = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int tx = Integer.parseInt(st.nextToken())-1;
        int ty = Integer.parseInt(st.nextToken())-1;
        taxi = new Taxi(tx,ty,initFuel);
        setDistArr(taxi);

        personArr = new ArrayList<>();
        pq = new PriorityQueue<>();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken())-1;
            int sy = Integer.parseInt(st.nextToken())-1;
            int ex = Integer.parseInt(st.nextToken())-1;
            int ey = Integer.parseInt(st.nextToken())-1;
            Person p = new Person(sx,sy,ex,ey);
            p.setDistance(dist[p.s.x][p.s.y]);
            personArr.add(p);
            pq.add(personArr.get(i));
        }

        while(M>0 && taxi.fuel>0){
            Person target = pq.peek();
            if(!goToPassenger(target)) break;
            if(!moveTaxi(target)) break;
            getTaxiFee(target);
            M--;
            searchPassenger();
        }

        if(M==0) sb.append(taxi.fuel);
        else sb.append(-1);
        System.out.println(sb.toString());
    }

    public static boolean goToPassenger(Person p){
        // 승객을 태우러 갈 수 없는 위치이거나, 승객을 태우러 갈 수 있는 연료가 부족함.
        if(p.distance==-1 || taxi.fuel<p.distance) return false;
        taxi.p.x = p.s.x;
        taxi.p.y = p.s.y;
        taxi.fuel -= p.distance;
        return true;
    }

    public static boolean moveTaxi(Person p){
        // 출발점에서 거리 계산
        setDistArr(taxi);
        // 승객의 목적지까지 연료량 확인
        if(taxi.fuel<dist[p.e.x][p.e.y]) return false;
        return true;
    }

    public static void getTaxiFee(Person p){
        // 택시 이동 및 요금 계산(연료충전)
        int needFuel = dist[p.e.x][p.e.y];
        taxi.p.x = p.e.x;
        taxi.p.y = p.e.y;
        taxi.fuel -= needFuel;
        taxi.fuel += needFuel*2;
        p.isEnd = true;
    }

    public static void searchPassenger(){
        pq.clear();
        setDistArr(taxi);
        // 다음 승객 우선순위 계산
        for(Person person : personArr){
            if(!person.isEnd){
                person.setDistance(dist[person.s.x][person.s.y]);
                pq.add(person);
            }
        }
    }
    public static void resetDistArr(){
        for(int i=0;i<N;i++){
            Arrays.fill(dist[i],-1);
        }
    }
    public static void setDistArr(Taxi taxi){
        resetDistArr();
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(taxi.p);
        dist[taxi.p.x][taxi.p.y]=0;
        while(!queue.isEmpty()){
            Point current = queue.poll();
            for(int i=0;i<4;i++){
                int newX = current.x + dxs[i];
                int newY = current.y + dys[i];
                if(canGo(newX,newY)){
                    queue.offer(new Point(newX,newY));
                    dist[newX][newY] = dist[current.x][current.y]+1;
                }
            }
        }
    }

    public static boolean inRange(int x,int y){
        return x>=0 && x<N && y>=0 && y<N;
    }

    public static boolean canGo(int x,int y){
        return inRange(x,y) && map[x][y]!=1 && dist[x][y]==-1;
    }
    static class Point{
        int x,y;

        public Point(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    static class Taxi{
        Point p;
        int fuel;
        public Taxi(int x,int y,int fuel){
            this.p = new Point(x,y);
            this.fuel = fuel;
        }
    }


    static class Person implements Comparable<Person>{
        Point s,e;
        int distance;
        boolean isEnd;
        public Person(int sx,int sy,int ex,int ey){
            this.s = new Point(sx,sy);
            this.e = new Point(ex,ey);
            this.isEnd = false;
        }

        public void setDistance(int distance){
            this.distance = distance;
        }
        @Override
        public int compareTo(Person o) {
            return this.distance == o.distance ? this.s.x == o.s.x ? this.s.y - o.s.y : this.s.x-o.s.x: this.distance - o.distance;
        }
    }
}
