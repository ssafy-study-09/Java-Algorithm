import java.util.*;
import java.io.*;
/*
바이러스를 놓는 경우의 수 10C5
BFS 시간복잡도 250
* */
public class Main {
    static int N, M;
    static int[][] board;
    static int[][] dist;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int emptyCnt = 0, answer = Integer.MAX_VALUE;
    static boolean inRange(int r, int c){
        return 0 <= r && r < N && 0 <= c && c < N;
    }
    static int bfs(){
        int nr, nc, r, c, time = 0, empty = emptyCnt;
        Queue<int[]> q = new ArrayDeque<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(board[i][j] == 3){
                    q.add(new int[]{i, j});
                }
            }
        }
        while(!q.isEmpty()){
            int[] tmp = q.poll();
            r = tmp[0];
            c = tmp[1];
            for(int i = 0; i < dr.length; i++){
                nr = r + dr[i];
                nc = c + dc[i];
                if(!inRange(nr, nc)) continue;
                if(board[nr][nc] == 0 && dist[nr][nc] == 0){
                    dist[nr][nc] = dist[r][c] + 1;
                    q.add(new int[]{nr, nc});
                    time = Math.max(dist[nr][nc], time);
                    empty--;
                }
                else if(board[nr][nc] == 2 && dist[nr][nc] == 0){
                    dist[nr][nc] = dist[r][c] + 1;
                    q.add(new int[]{nr, nc});
                }
            }
        }
        if(empty > 0) return Integer.MAX_VALUE;
        return time;
    }
    static void setVirus(int row, int col, int cnt){
        if(cnt == 0){
            dist = new int[N][N];
            answer = Math.min(answer, bfs());
            return;
        }
        int c = col;
        for(int r = row; r < N; r++){
            for(; c < N; c++){
                if(board[r][c] == 2){
                    board[r][c] = 3;
                    setVirus(r, c, cnt - 1);
                    board[r][c] = 2;
                }
            }
            c = 0;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0){
                    emptyCnt++;
                }
            }
        }
        setVirus(0, 0, M);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
