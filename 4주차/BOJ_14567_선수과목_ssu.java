import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14567_선수과목_ssu {
    static Node[] adjList;
    static int[] inDegree;
    static int[] hakgi;
    static int N,M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inDegree = new int[N+1];
        adjList = new Node[N+1];
        hakgi = new int[N+1];
        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adjList[A] = new Node(B,adjList[A]);
            ++inDegree[B];
        }

        TopologySort();
        for(int i=1;i<=N;i++) {
            sb.append(hakgi[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

    public static void TopologySort() {
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=1;i<=N;i++) {
            if(inDegree[i]==0) {
                queue.offer(i);
                hakgi[i] = 1;
            }
        }

//		System.out.println(Arrays.toString(hakgi));
        while(!queue.isEmpty()) {
            int current = queue.poll();
            for(Node temp = adjList[current]; temp!=null; temp = temp.next) {
                if(--inDegree[temp.to]==0) {
                    queue.offer(temp.to);
                    hakgi[temp.to] = hakgi[current]+1;
                }
            }
        }
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
