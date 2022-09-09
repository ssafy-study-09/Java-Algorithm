import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_22234_가희와은행_ssu {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        Queue<Client> waiting = new ArrayDeque<>();
        while(N-->0){
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            waiting.offer(new Client(id,time));
        }

        PriorityQueue<VisitClient> visiting = new PriorityQueue<>();
        int M = Integer.parseInt(br.readLine());
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            int visit = Integer.parseInt(st.nextToken());
            visiting.offer(new VisitClient(id,time,visit));
        }

        int operatingTime = 0;
        int[] processIdArr = new int[W];
        while(operatingTime<W){
            int startTime = operatingTime;
            Client current = waiting.poll();

            if(current.remainTaskTime > T){
                operatingTime += T;
                current.processTask(T);
            }
            else{
                operatingTime += current.remainTaskTime;
                current.isFinish = true;
            }

            while(!visiting.isEmpty() && visiting.peek().visitTime <= operatingTime){
                VisitClient vc = visiting.poll();
                waiting.offer(new Client(vc.id,vc.remainTaskTime));
            }
            if(!current.isFinish) waiting.offer(current);

            for(int i=startTime;i<=operatingTime;i++){
                if(i>=W) break;
                processIdArr[i] = current.id;
            }
        }

        for(int i=0;i<W;i++){
            sb.append(processIdArr[i]).append("\n");
        }
        System.out.print(sb.toString());
    }

    static class Client{
        int id;
        int remainTaskTime;

        boolean isFinish;
        public Client(int id,int remainTaskTime){
            this.id = id;
            this.remainTaskTime = remainTaskTime;
            this.isFinish = false;
        }

        public void processTask(int T){
            this.remainTaskTime -= T;
        }

    }

    static class VisitClient extends Client implements Comparable<VisitClient>{
        int visitTime;

        public VisitClient(int id,int remainTaskTime,int visitTime){
            super(id,remainTaskTime);
            this.visitTime = visitTime;
        }

        @Override
        public int compareTo(VisitClient o) {
            return this.visitTime - o.visitTime;
        }
    }
}
