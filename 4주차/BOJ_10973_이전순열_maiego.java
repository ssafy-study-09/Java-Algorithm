import java.util.*;

public class Main {
	
	static int n;
	static int[] arr;
	
	static boolean f(int[] arr) {
		int i=n-1;
		while (i>=1 && arr[i-1]<=arr[i]) --i;
		if (i==0) return false;
		
		int j=n-1;
		while (arr[i-1]<=arr[j]) --j;
		swap(arr, i-1, j);
		
		j=n-1;
		while (i<j) swap(arr, i++, j--);
		
		return true;
	}
	
	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		arr = new int[n];
		for (int i=0; i<n; ++i)
			arr[i] = sc.nextInt();
		
		boolean res = f(arr);
		
		if (!res) System.out.println(-1);
		else {
			for (int x: arr) {
				System.out.print(x + " ");
			}
		}
	}

}
