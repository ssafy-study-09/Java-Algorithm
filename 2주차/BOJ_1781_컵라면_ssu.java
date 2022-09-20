import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Quest[] questarr = new Quest[N];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int dL = Integer.parseInt(st.nextToken());
            int rW = Integer.parseInt(st.nextToken());
            questarr[i] = new Quest(dL,rW);
        }

        System.out.println(getMaxmiumReward(questarr));
    }

    public static int getMaxmiumReward(Quest[] questarr){
        int result = 0;
        Arrays.sort(questarr);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0,size=questarr.length;i<size;i++){
            if(questarr[i].deadLine > pq.size()){
                pq.offer(questarr[i].reward);
            }
            else if(questarr[i].deadLine == pq.size()){
                if(pq.peek()< questarr[i].reward){
                    pq.poll();
                    pq.offer(questarr[i].reward);
                }
            }
        }

        while(!pq.isEmpty()){
            result += pq.poll();
        }
        return result;
    }
    static class Quest implements Comparable<Quest> {
        int deadLine;
        int reward;

        public Quest(int deadLine,int reward){
            this.deadLine = deadLine;
            this.reward = reward;
        }

        @Override
        public int compareTo(Quest o) {
            return this.deadLine!=o.deadLine ? this.deadLine-o.deadLine : -(this.reward - o.reward);
        }
    }
}