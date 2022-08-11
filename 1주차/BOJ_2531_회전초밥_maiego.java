import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int maxN = sc.nextInt();
		int takeN = sc.nextInt();
		int coupon = sc.nextInt();
		
		int[] arr = new int[2*n];
		for (int i=0; i<n; ++i)
			arr[i+n] = arr[i] = sc.nextInt();
		
		int ans = 0;
		int kindN = 1;
		int[] cnt = new int[maxN+1]; cnt[coupon]++;
		for (int i=0; i<2*n; ++i) {
			if (++cnt[arr[i]]==1) ++kindN;
			if (i>=takeN)
				if (--cnt[arr[i-takeN]]==0) --kindN;
			if (i>=takeN-1)
			    ans = Math.max(ans, kindN);
		}
		System.out.println(ans);

	}

}
