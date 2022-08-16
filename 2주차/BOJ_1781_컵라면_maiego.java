import java.util.*;

public class Main {
	static int[] link;

	static class Item {
		int deadline, reward;
		Item (int a, int b) {
			deadline=a; reward=b;
		}
	}
	
	static int find(int x) {
		return x==link[x] ? x : (link[x]=find(link[x]));
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		Item[] arr = new Item[n];
		for (int i=0; i<n; ++i) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			arr[i] = new Item(a, b);
		}
		Arrays.sort(arr, (a,b)->b.reward-a.reward);
		
		link = new int[n+1];
		for (int i=1; i<=n; ++i)
			link[i] = i;

		int ans=0;
		for (Item item: arr) {
			int x = find(item.deadline);
			if (x==0) continue;
			ans += item.reward;
			link[x] = x-1;
		}

		System.out.println(ans);
	}

}
