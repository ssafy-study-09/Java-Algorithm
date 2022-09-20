import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2252_줄세우기_ssu {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Node[] adjList = new Node[N+1];
		int[] inDegree = new int[N+1];
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			adjList[s] = new Node(e,adjList[s]);
			++inDegree[e];
		}
		
		System.out.println(TSbyBFS(inDegree,adjList));
		
	}
	
	public static String TSbyBFS(int[] inDegree,Node[] adjList) {
		Queue<Integer> queue = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		for(int i=1,size=inDegree.length;i<size;i++) {
			if(inDegree[i]==0)
			{
				queue.offer(i);
				sb.append(i).append(" ");
			}
		}
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			for(Node temp = adjList[current]; temp!=null; temp=temp.next) {
				if(--inDegree[temp.to]==0) {
					queue.offer(temp.to);
					sb.append(temp.to).append(" ");
				}
 			}
		}
		
		return sb.toString();
	}
	static class Node{
		int to;
		Node next;
		
		public Node(int to,Node next) {
			this.to = to;
			this.next = next;
		}
	}
}
