import java.util.Arrays;
import java.util.PriorityQueue;

public class PGR_118669_등산코스정하기_ssu {
    public static void main(String[] args) throws Exception{

    }
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        Node[] adjList = new Node[n+1];

        Arrays.sort(paths,(e1,e2)->-(e1[2]-e2[2]));
        for(int[] path : paths){
            int from = path[0];
            int to = path[1];
            int weight = path[2];

            adjList[from] = new Node(to,weight,adjList[from]);
            adjList[to] = new Node(from,weight,adjList[to]);
        }


        int[] dist = new int[n+1]; // 출발지에서 해당 정점까지의 최소 intensity
        Arrays.fill(dist,(int)1e9);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int gate : gates){
            dist[gate] = 0;
            pq.offer(new Node(gate,dist[gate]));
        }

        while(!pq.isEmpty()){
            //1. 선택된 정점 중, intensity가 가장 작은 것 찾기
            Node minVertex = pq.poll();

            //2. 산봉우리이거나, 기존 intensity보다 큰 등산로라면 택하지 않음
            if(checkFinish(minVertex.vertex,summits) || minVertex.weight > dist[minVertex.vertex]) continue;

            //3. 해당 정점에서 갈 수 있는 다른 정점들 중, 기존 intensity보다 유리하다면 갱신하고 해당 정점을 큐에 넣음
            for(Node temp = adjList[minVertex.vertex]; temp!=null; temp = temp.next){
                if(dist[temp.vertex] > Math.max(dist[minVertex.vertex],temp.weight)){
                    dist[temp.vertex] = Math.max(dist[minVertex.vertex],temp.weight);
                    pq.offer(new Node(temp.vertex,dist[temp.vertex]));
                }
            }
        }

        int[] result = new int[]{0,(int)1e9};
        //4. 산봉우리 번호가 낮은 순부터 최소 intensity 찾기
        Arrays.sort(summits);
        for(int summit : summits){
            if(dist[summit] < result[1]){
                result[1] = dist[summit];
                result[0] = summit;
            }
        }
        return result;
    }

    static boolean checkFinish(int v,int[] summits){
        for(int summit : summits){
            if(v == summit) return true;
        }
        return false;
    }
    static class Node implements Comparable<Node>{
        int vertex,weight;
        Node next;

        public Node(int vertex,int weight){
            this.vertex = vertex;
            this.weight = weight;
        }
        public Node(int vertex,int weight,Node next){
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight,o.weight);
        }
    }
}
