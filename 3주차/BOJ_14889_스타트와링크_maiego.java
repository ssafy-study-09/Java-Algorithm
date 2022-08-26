import java.util.*;

public class Main {
    static int n;
    static int[][] scores;

    static int f(int x) {
        int ret = 0;
        for (int i=0; i<n; ++i)
            for (int j=0; j<n; ++j)
                if ((x&1<<i) >0 && (x&1<<j) >0)
                    ret += scores[i][j];
        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        scores = new int[n][n];
        for (int i=0; i<n; ++i)
            for (int j=0; j<n; ++j)
                scores[i][j] = sc.nextInt();

        int ans = (int)1e9;
        for (int i=0; i<(1<<n); ++i) {
            if (Integer.bitCount(i)!=n/2) continue;
            ans = Math.min(ans, Math.abs(f(i) - f(~i)));
        }
        System.out.println(ans);
    }
    
}