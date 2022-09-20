import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11725_트리의부모찾기_ssu {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Node[] adjList = new Node[N+1];
		for(int i=1;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			adjList[s] = new Node(e,adjList[s]);
			adjList[e] = new Node(s,adjList[e]);
		}
		
		int[] parents = new int[N+1];
		
		BFS(adjList,parents);
		
		System.out.print(printParents(parents));
		
	}

	public static void BFS(Node[] adjList,int[] parents) {
		Queue<Integer> queue = new ArrayDeque<>();
		parents[1] = 1;
		queue.offer(1);
		while(!queue.isEmpty()) {
			int current = queue.poll();
			for(Node temp = adjList[current];temp!=null;temp=temp.next) {
				if(parents[temp.to]==0) {
					parents[temp.to] = current;
					queue.offer(temp.to);
				}
			}
		}
	}
	
	public static String printParents(int[] parents) {
		StringBuilder sb = new StringBuilder();
		for(int i=2,size=parents.length;i<size;i++) {
			sb.append(parents[i]).append("\n");
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
