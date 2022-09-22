import java.util.*;
class Solution {
    static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    static boolean[] isSummit= new boolean[50001]; 
    static boolean[] isGate = new boolean[50001];
    static int[] answer = new int[2];
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<Node>());
        }
        for(int i=0; i<gates.length; i++){
            isGate[gates[i]] = true;
        }
        for(int i=0; i<summits.length; i++){
            isSummit[summits[i]] = true;
        }
        
        for (int[] path : paths) {
            int i = path[0]; int j = path[1]; int w = path[2];

            if(isGate[i] || isSummit[j]) {
                graph.get(i).add(new Node(j, w));
            } else if (isGate[j] || isSummit[i]) {
                graph.get(j).add(new Node(i, w));
            } else {
                graph.get(i).add(new Node(j, w));
                graph.get(j).add(new Node(i, w));
            }
            
            
        }
        dijk(n, gates, summits);
        
        
        return answer;
    }
     private static void dijk(int n, int[] gates, int[] summits) {
        int[] intensity = new int[n + 1];
        Queue<Node> q = new LinkedList<>();
        Arrays.fill(intensity, (int)1e9);

        for (int start : gates) {
            q.add(new Node(start, 0));
            intensity[start] = 0;
        }

        while (!q.isEmpty()) {
            Node now = q.poll();
            if(now.w < intensity[now.e]) 
                continue;

            for (int i = 0; i < graph.get(now.e).size(); i++) {
                Node next = graph.get(now.e).get(i);

                int dist = Math.max(intensity[now.e], next.w);
                if (intensity[next.e] > dist) {
                    intensity[next.e] = dist;
                    q.add(new Node(next.e, dist));
                }
            }
        }
        int num = (int)1e9;
        int min = (int)1e9;
       
        for (int summit : summits) {
            if (intensity[summit] < min || (intensity[summit] == min && summit<num)) {
                min = intensity[summit];
                num = summit;
            }
        }
        answer[0] = num;
        answer[1] = min;
    }
    
}
class Node {
    int e, w;
    public Node(int e, int w) {
        this.e = e;
        this.w = w;
    }
}