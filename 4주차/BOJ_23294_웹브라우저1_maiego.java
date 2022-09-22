import java.util.*;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
		
	static Deque<Integer> prev = new ArrayDeque<>();
	static Deque<Integer> next = new ArrayDeque<>();
	
	static int n, q, maxC;
	static int cur, inuse;
	static int[] cap;
	
	static void process() {
		char cmd = sc.next().charAt(0);

		if (cmd=='B') {
			if (prev.isEmpty()) return;
			if (cur!=0) next.addLast(cur);
			cur = prev.pollLast();

		} else if (cmd=='F') {
			if (next.isEmpty()) return;
			if (cur!=0) prev.addLast(cur);
			cur = next.pollLast();

		} else if (cmd=='A') {
			int x = sc.nextInt();
			while (!next.isEmpty()) {
				int xx = next.pollLast();
				inuse -= cap[xx];
			}
			if (cur!=0) prev.addLast(cur);
			cur = x;
			inuse += cap[x];
			while (inuse>maxC) {
				int xx = prev.pollFirst();
				inuse -= cap[xx];
			}

		} else if (cmd=='C') {
			int prevX = 0;
			Deque<Integer> tmp = new ArrayDeque<>();
			while (!prev.isEmpty()) {
				int x = prev.pollLast();
				inuse -= cap[x];
				if (prevX == x) continue;
				inuse += cap[x];
				tmp.addFirst(x);
				prevX = x;
			}
			prev = tmp;
		}
	}

	public static void main(String[] args) {
		n = sc.nextInt();
		q = sc.nextInt();
		maxC = sc.nextInt();
		
		cap = new int[n+1];
		for (int i=1; i<=n; ++i)
			cap[i] = sc.nextInt();
		
		while (q-->0) {
			process();
		}
		sb.append(cur).append('\n');
		if (prev.isEmpty()) sb.append(-1);
		else {
			while (!prev.isEmpty()) {
				sb.append(prev.pollLast()).append(' ');
			}
		}
		sb.append('\n');
		if (next.isEmpty()) sb.append(-1);
		else {
			while (!next.isEmpty()) {
				sb.append(next.pollLast()).append(' ');
			}
		}
		System.out.println(sb);
	}

}
