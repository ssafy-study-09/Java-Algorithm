import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17142_연구소3_ssu {
    static final int DIRSIZE = 4;
    static int N,M;
    static int[] dxs = {-1,+1,0,0};
    static int[] dys = {0,0,-1,+1};

    static int[][] steps;
    static int[][] map;
    static Queue<Point> queue;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int minTime = 2500;
        // N 50, M 10
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ArrayList<Point> virus = new ArrayList<>();
        ArrayList<Integer> possibleTime = new ArrayList<>();
        map = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                //바이러스가 놓일 수 있는 위치를 기록
                if(map[i][j]==2){
                    virus.add(new Point(i,j));
                }

            }
        }

        int possibleCnt = virus.size();
        int[] index = new int[possibleCnt];
        for(int i=1;i<=M;i++){
            index[possibleCnt-i] = 1;
        }

        do{
            //M개의 바이러스는 BFS 시작 준비
            queue = new ArrayDeque<>();
            steps = new int[N][N];
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    steps[i][j] = -1;
                }
            }
            for(int i=0;i<possibleCnt;i++){
                switch(index[i]) {
                    case 0:
                        Point target = virus.get(i);
                        map[target.x][target.y] = 3;
                        break;
                    case 1:
                        Point start = virus.get(i);
                        queue.offer(start);
                        steps[start.x][start.y] = 0;
                        break;
                }
            }

            bfs();
            int countTime = countTime();
            //단순히 최솟값으로 갱신해버리면, 전체 탐색이 가능한 경우가 있더라도 무시될 수 있다.
            if(countTime>=0) possibleTime.add(countTime);
            minTime = Math.min(minTime,countTime);
            //기존 비활성 바이러스 복귀
            for(int i=0;i<possibleCnt;i++){
                if(index[i]==0){
                    Point target = virus.get(i);
                    map[target.x][target.y] = 2;
                }
            }
        }while(NextPermutation(index));
        System.out.println(possibleTime.size()>0? possibleTime.stream().min((a,b)->a-b).get().toString() : minTime);
    }

    public static int countTime(){
        int maxTime = -1;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                // 비활성 바이러스를 제외한 곳들에 대해서만 퍼진 시각을 기록해야함.
                if(map[i][j]==0 || map[i][j]==2){
                    if(steps[i][j]==-1){
                        return -1;
                    }
                    maxTime = Math.max(maxTime,steps[i][j]);
                }
            }
        }
        return maxTime;
    }

    public static void bfs(){
        while(!queue.isEmpty()){
            Point current = queue.poll();
            int x = current.x;
            int y = current.y;
            for(int i=0;i<DIRSIZE;i++){
                int newX = x + dxs[i];
                int newY = y + dys[i];
                if(canGo(newX,newY)){
                    switch(map[newX][newY]){
                        case 0:
                            if(steps[newX][newY]==-1 || steps[newX][newY] > steps[x][y]+1)
                            {
                                queue.offer(new Point(newX,newY));
                                steps[newX][newY] = steps[x][y]+1;
                            }
                            break;
                        case 3:
                            if(steps[newX][newY]==-1){
                                queue.offer(new Point(newX,newY));
                                steps[newX][newY] = steps[x][y]+1;
                            }
                            break;
                    }
                }
            }
        }
    }
    public static boolean NextPermutation(int[] arr){
        int n = arr.length;

        int i = n-1;

        while(i>0 && arr[i-1]>=arr[i]) i--;

        if(i==0) return false;

        int j = n-1;
        while(arr[i-1]>=arr[j]) j--;
        Swap(arr,i-1,j);

        int k = n-1;

        while(i<k) Swap(arr,i++,k--);
        return true;

    }

    public static void Swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static boolean inRange(int x,int y){
        return x>=0 && x<N && y>=0 && y<N;
    }

    public static boolean canGo(int x,int y){
        return inRange(x,y) && map[x][y]!=1;
    }
    static class Point{
        int x;
        int y;
        public Point(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
}
