import java.util.*;

public class Main {
	static class Item {
		int id, remain;
		Item(int a, int b) {
			id=a; remain=b;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int n = sc.nextInt();
		int t = sc.nextInt();
		int w = sc.nextInt();

		Deque<Item> q = new ArrayDeque<>();
		for (int i=0; i<n; ++i)
			q.add(new Item(sc.nextInt(), sc.nextInt()));

		int m = sc.nextInt();
		Map<Integer, Item> incoming = new HashMap<>();
		while (m-->0) {
			int id = sc.nextInt();
			int remain = sc.nextInt();
			int time = sc.nextInt();
			Item item = new Item(id, remain);
			incoming.put(time, item);
		}
		
		Item cur = null;
		int consume = 0;
		for (int time=0; time<w; ++time) {
			Item x = incoming.get(time);
			if (x!=null) q.add(x);
			
			if (consume==0) {
				if (cur!=null && cur.remain > 0)
					q.add(cur);
				cur = q.pop();
				consume = Math.min(t, cur.remain);
			}

			sb.append(cur.id).append('\n');
			--consume; --cur.remain;
		}
		
		System.out.println(sb);
	}
}