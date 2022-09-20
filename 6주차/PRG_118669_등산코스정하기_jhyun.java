import java.util.*;
import java.util.stream.Collectors;

class Solution {
    static List<int[]> graph[];
    static Set<Integer> setGates = new HashSet<>(), setSummit = new HashSet<>();
    static Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
        if(a[0] == b[0])
            return a[1] - b[1];
        return a[0] - b[0];
    });
    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        for(Integer gate: gates)
            setGates.add(gate);
        for(Integer summit: summits)
            setSummit.add(summit);
        graph = new List[n + 1];
        for(int i = 0; i <= n; i++)
            graph[i] = new ArrayList<>();
        for(int i = 0; i < paths.length; i++){
            int from = paths[i][0], to = paths[i][1], cost = paths[i][2];
            if(setGates.contains(from) || setSummit.contains(to))
                graph[from].add(new int[]{to, cost});
            else if(setGates.contains(to) || setSummit.contains(from))
                graph[to].add(new int[]{from, cost});
            else{
                graph[to].add(new int[]{from, cost});
                graph[from].add(new int[]{to, cost});
            }
        }
        int[] visit = new int[n + 1];
        Arrays.fill(visit, Integer.MAX_VALUE);
        for(Integer gate: gates){
            visit[gate] = 0;
            pq.add(new int[]{0, gate});
        }
        while(!pq.isEmpty()){
            int[] tmp = pq.poll();
            int cost = tmp[0];
            int cur = tmp[1];
            if(visit[cur] < cost) continue;
            for(int[] node: graph[cur]){
                int next = node[0];
                int nextCost = Math.max(visit[cur], node[1]);
                if(visit[next] > nextCost){
                    visit[next] = nextCost;
                    pq.add(new int[]{nextCost, next});
                }
            }
        }
        int ansDist = Integer.MAX_VALUE, ansSummit=0;
        Arrays.sort(summits);
        for(Integer summit: summits){
            if(ansDist > visit[summit]){
                ansDist = visit[summit];
                ansSummit = summit;
            }
        }
        int[] answer = new int[]{ansSummit, ansDist};
        return answer;
    }
}
