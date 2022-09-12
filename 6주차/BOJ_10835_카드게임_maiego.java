import java.util.*;

public class Main {
    static int n;
    static int[] left, right;
    static int[][] dp;
    static boolean[][] vis;

    static int f(int a, int b) {
        if (a>=n || b>=n) return 0;
        if (vis[a][b]) return dp[a][b];

        vis[a][b]=true;
        if (left[a]>right[b]) return dp[a][b] = right[b]+f(a, b+1);
        return dp[a][b] = Math.max(f(a+1, b), f(a+1, b+1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        left = new int[n];
        right = new int[n];

        dp = new int[n][n];
        vis = new boolean[n][n];

        for (int i=0; i<n; ++i)
            left[i] = sc.nextInt();
        for (int i=0; i<n; ++i)
            right[i] = sc.nextInt();

        System.out.println(f(0, 0));
    }

}