import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();

		int[] arr = new int[n];
		for (int i=0; i<n; ++i)
			arr[i] = sc.nextInt();

		int[] left = new int[n];
		for (int i=0; i<n; ++i) {
			for (int j=0; j<i; ++j)
				if (arr[j]>arr[i])
					left[i] = Math.max(left[i], left[j]+1);
		}
		
		int ans = 0;
		for (int i=0; i<n; ++i)
			ans = Math.max(ans, left[i]);
		System.out.println(n-1-ans);
	}

}