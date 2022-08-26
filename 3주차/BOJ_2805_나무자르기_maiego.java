import java.util.*;

public class Main { 
    static int n,k;
    static int[] arr;

    static boolean pos(int x) {
        long acc=0;
        for (int i=0; i<n; ++i)
            acc += Math.max(arr[i]-x, 0);
        return acc>=k;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();
        arr = new int[n];
        for (int i=0; i<n; ++i)
            arr[i] = sc.nextInt();

        int lo=0, hi=(int)1e9+1;
        while (lo+1<hi) {
            int mid = lo+hi>>1;
            if (pos(mid)) lo=mid;
            else hi=mid;
        }
        System.out.println(lo);
    }
    
}