class Solution {
  static class Pair{
    boolean canWin;
    int turn;
    public Pair(boolean canWin, int turn){
      this.canWin = canWin;
      this.turn = turn;
    }
  }
  static int N, M;
  static final int[] dr = {1, 0, -1, 0};
  static final int[] dc = {0, 1, 0, -1};
  static boolean inRange(int r, int c){
    return 0 <= r && r < N && 0 <= c && c < M;
  }
  public static boolean isFinished(int[][] board, int r, int c){
    for(int i = 0; i < 4; i++){
      int nr = r + dr[i];
      int nc = c + dc[i];
      if(inRange(nr, nc) && board[nr][nc] == 1){
        return false;
      }
    }
    return true;
  }
  public static Pair solve(int[][] board, int r1, int c1, int r2, int c2){
    if(isFinished(board, r1, c1)){
      return new Pair(false, 0);
    }
    if(r1 == r2 && c1 == c2){
      return new Pair(true, 1);
    }

    boolean canWin = false;
    int minTurn = 1000000000;
    int maxTurn = 0;

    for(int i = 0; i < 4; i++){
      int nr = r1 + dr[i];
      int nc = c1 + dc[i];
      if(!inRange(nr, nc) || board[nr][nc] == 0){
        continue;
      }

      board[r1][c1] = 0;
      Pair res = solve(board, r2, c2, nr, nc);
      board[r1][c1] = 1;

      if(!res.canWin){
        canWin = true;
        minTurn = Math.min(minTurn, res.turn);
      }
      else if(!canWin){
        maxTurn = Math.max(maxTurn, res.turn);
      }
    }

    int turn = canWin ? minTurn : maxTurn;
    return new Pair(canWin, turn + 1);
  }
  public static int solution(int[][] board, int[] aloc, int[] bloc) {
    N = board.length; M = board[0].length;
    return solve(board, aloc[0], aloc[1], bloc[0], bloc[1]).turn;
  }
}