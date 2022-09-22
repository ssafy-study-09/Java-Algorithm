import java.util.*;

public class Main {
	static int n,k;
	static int ans, minDiff;
	
	static void upt(int sum) {
		int diff = Math.abs(sum-k);
		if (diff<minDiff) {
			minDiff = diff;
			ans = sum;
		} else if (diff==minDiff && sum<ans)
			ans = sum;
	}

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		while (T-->0) {
            k = sc.nextInt();
            n = sc.nextInt();
            
			int[][] input = new int[4][n];
			for (int i=0; i<4; ++i)
				for (int j=0; j<n; ++j)
					input[i][j] = sc.nextInt();
			
			int[] list = new int[n*n];
			int idx=0;
			for (int i=0; i<n; ++i)
				for (int j=0; j<n; ++j)
					list[idx++] = input[0][i]+input[1][j];
			Arrays.sort(list);
			
			minDiff = (int)1e9;
			for (int i=0; i<n; ++i)
				for (int j=0; j<n; ++j) {
					int sum = input[2][i] + input[3][j];

					int lo=0, hi=n*n-1;
					while (lo<=hi) {
						int mid = lo+hi>>1;
					    if (list[mid]<k-sum) lo=mid+1;
					    else hi=mid-1;
					    upt(sum+list[mid]);
					}
				}
			sb.append(ans).append('\n');
		}
		System.out.println(sb);

	}

}
