import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16437_양구출작전_ssu {
    static Node[] adjList;
    static int[] inDegree;
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        adjList = new Node[N+1];
        inDegree = new int[N+1];

        adjList[1] = new Node('S',0,0);

        for(int i=2;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            char type = st.nextToken().charAt(0);
            long typeCnt = Long.parseLong(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[i] = new Node(type,typeCnt,to);
            inDegree[to]++;
        }

        topologySort();

        System.out.print(adjList[1].moveCnt);
    }

    public static void topologySort(){
        Queue<Integer> queue = new ArrayDeque<>();

        for(int i=2;i<=N;i++){
            //말단 노드 부터 거꾸로 올라가기
            if(inDegree[i]==0){
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){
            int current = queue.poll();
            //1번 섬은 종착지
            if(current==1)
                return;
            Node curIsland = adjList[current];
            int next = curIsland.to;

            //현재 섬에 양이 살고 있으면 모두 이동
            if(curIsland.type == 'S'){
                adjList[next].moveCnt += curIsland.moveCnt;
            }
            else{
                //현재 섬에 늑대가 살고 있으면, 늑대의 수만큼 빼고도 양이 남아있는 만큼만 이동
                if(curIsland.typeCnt < curIsland.moveCnt){
                    adjList[next].moveCnt += (curIsland.moveCnt- curIsland.typeCnt);
                }
            }

            //이동할 섬으로 이동가능한 모든 경우를 처리했음
            if(--inDegree[next]==0){
                queue.offer(next);
            }
        }

    }

    static class Node{
        char type;
        long typeCnt;
        long moveCnt;
        int to;

        public Node(char type,long typeCnt,int to){
            this.type = type;
            this.typeCnt = typeCnt;
            this.to = to;
            if(this.type == 'S'){
                this.moveCnt = this.typeCnt;
            }
        }
    }
}