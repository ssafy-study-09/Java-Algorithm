import java.util.*;

class Solution {
    
    static int maxa, maxc;
    static int starta, startc;
    static int[][] ps;
    
    static int[][] dp;
    static boolean[][] vis;
    
    static int f(int a, int c) {
        
        if (a<0 || c<0) return (int)1e6;
        if (a<=starta && c<=startc) return 0;
        if (vis[a][c]) return dp[a][c];
        
        int ret = (int)1e6;
        ret = Math.min(ret, 1+f(a-1,c));
        ret = Math.min(ret, 1+f(a,c-1));
        for (int[] p: ps) {
            int aa = a-p[2];
            int cc = c-p[3];
            if (aa<starta || cc<startc) continue;
            if (aa>=p[0] && cc>=p[1])
                ret = Math.min(ret, f(aa,cc)+p[4]);
        }
        
        vis[a][c] = true;
        return dp[a][c]=ret;
    }
    
    public int solution(int alp, int cop, int[][] problems) {
        
        dp = new int[1001][1001];
        vis = new boolean[1001][1001];
        
        starta = alp;
        startc = cop;
        ps = problems;
        
        
        for (int[] p: ps) {
            maxa = Math.max(maxa, p[0]);
            maxc = Math.max(maxc, p[1]);
        }
        
        int ans = (int)1e6;
        for (int a=maxa; a<4*maxa; ++a)
            for (int c=maxc; c<4*maxc; ++c)
                ans = Math.min(ans, f(a,c));
        return ans;
    }
}