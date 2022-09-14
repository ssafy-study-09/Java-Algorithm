import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1263_시간관리_ssu {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Work> pq = new PriorityQueue<>();

        while(N-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.offer(new Work(t,e));
        }

        Work lastWork = pq.poll();
        int wakeUpTime = lastWork.e - lastWork.t;
        while(!pq.isEmpty()){
            Work currentWork = pq.poll();
            if(currentWork.e < wakeUpTime){
                wakeUpTime = currentWork.e;
            }
            wakeUpTime -= currentWork.t;
        }

        if(wakeUpTime<0) System.out.println(-1);
        else System.out.println(wakeUpTime);
    }

    static class Work implements Comparable<Work>{
        int t,e;

        public Work(int t,int e){
            this.t = t;
            this.e = e;
        }

        @Override
        public int compareTo(Work o) {
            return this.e == o.e ? -(this.t - o.t):-(this.e - o.e);
        }


    }
}
