class Solution {
    static int h,w;
    static int[][] board;
    static int aY,aX, bY,bX;
    static int win;
    
    final static int[] dy = {-1,1,0,0};
    final static int[] dx = {0,0,-1,1};
    
    static boolean inRange(int y, int x) {
        return y>=0 && y<h && x>=0 && x<w;
    }
    
    static int play(int turn, int cnt) {
        int y = (turn==0 ? aY : bY);
        int x = (turn==0 ? aX : bX);
        if (board[y][x]==0) {
            win = turn^1;
            return cnt-1;
        }
        
        board[y][x] = 0;
        int posCnt=0, winCnt=0;
        int loseMaxCnt = cnt, winMinCnt = cnt;
        int tmpY=y, tmpX=x;
        for (int d=0; d<4; ++d) {
            int ny = dy[d]+y;
            int nx = dx[d]+x;
            if (inRange(ny, nx) && board[ny][nx]==1) {
                ++posCnt;
                y=ny; x=nx;
                int res = play(turn^1, cnt+1);
                if (win==turn && res<winMinCnt)
                    winMinCnt = res;
                if (win==(turn^1) && res>loseMaxCnt)
                    loseMaxCnt = res;
                if (win==turn) ++winCnt;
                y=tmpY; x=tmpX;
            }
        }
        board[y][x]=1;
        
        if (posCnt==0) {
            win = turn^1;
            return cnt-1;
        }
        return winCnt==posCnt ? winMinCnt : loseMaxCnt;
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        h = board.length;
        w = board[0].length;
        this.board = board;
        aY = aloc[0]; aX = aloc[1];
        bY = bloc[0]; bX = bloc[1];
        
        return play(0, 1);
    }
}