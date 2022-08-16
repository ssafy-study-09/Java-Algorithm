import java.util.*;

public class Main {
	
	static class Item {
		String s;
		int cnt;
		Item(String a, int b) {
			s=a; cnt=b;
		}
	}
	
	static void swap(char[] arr, int i, int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String n = sc.next();
		int k = sc.nextInt();
		
		Deque<Item> q = new ArrayDeque<>();
		int ans = 0;
		q.add(new Item(n, 0));
		int[][] vis = new int[1000001][11];
		while (!q.isEmpty()) {
			Item item = q.pop();
			int x = Integer.parseInt(item.s);
			if (item.cnt==k) {
				ans = Math.max(ans, x);
				continue;
			}
			if (++vis[x][item.cnt]>1) continue;

			char[] s = item.s.toCharArray();
			int sz = s.length;
			for (int i=1; i<sz; ++i) {
				if (s[i]!='0') {
					swap(s, i,0);
					q.add(new Item(new String(s), item.cnt+1));
					swap(s, i,0);
				}
			}
			
			for (int i=1; i<sz; ++i) {
				for (int j=i+1; j<sz; ++j) {
					swap(s, i,j);
					q.add(new Item(new String(s), item.cnt+1));
					swap(s, i,j);
				}
			}
		}
		System.out.println(ans==0 ? -1 : ans);

	}

}
