import java.util.*;
import java.io.*;
/*
31%에서 틀림
* */
public class Main {
    static int N, Q, C;
    static int[] cap;
    static Deque<Integer> backward = new ArrayDeque<>(), forward = new ArrayDeque<>();
    static int curPage = -1;
    static int curCap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cap = new int[N + 1];
        curCap = C;
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            cap[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            if(order.equals("B") && !backward.isEmpty()){
                forward.addFirst(curPage);
                curPage = backward.pollLast();
            }
            else if(order.equals("F") && !forward.isEmpty()){
                backward.addLast(curPage);
                curPage = forward.pollFirst();
            }
            else if(order.equals("C")){
                Deque<Integer> dq = new ArrayDeque<>();
                if(backward.isEmpty()) continue;
                int cur = backward.pollLast();
                while(!backward.isEmpty()){
                    if(backward.getLast() == cur){
                        backward.pollLast();
                        curCap += cap[cur];
                    }
                    else{
                        dq.addFirst(cur);
                        cur = backward.pollLast();
                    }
                }
                if(backward.isEmpty()){
                    dq.addFirst(cur);
                }
                backward = dq;
            }
            else if(order.equals("A")){
                //앞으로 가기 모두 삭제, 용량 복원
                while(!forward.isEmpty()){
                    int cur = forward.pollLast();
                    curCap += cap[cur];
                }
                //접속하는 페이지가 처음이 아니면 현재 페이지를 뒤로가기에 저장
                if(curPage != -1){
                    backward.addLast(curPage);
                }
                curPage = Integer.parseInt(st.nextToken());
                curCap -= cap[curPage];
                // 페이지 용량 초과면,
                // 뒤로가기에서 오래된 순서로, 초과된만큼 없앰
                while(curCap < 0 && !backward.isEmpty()){
                    int cur = backward.pollFirst();
                    curCap += cap[cur];
                }
            }
        }
        System.out.println(curPage);
        if(backward.isEmpty()){
            System.out.println(-1);
        }
        else{
            while(!backward.isEmpty()){
                System.out.printf("%d ", backward.pollLast());
            }
            System.out.println();
        }
        if(forward.isEmpty()){
            System.out.println(-1);
        }
        else{
            while(!forward.isEmpty()){
                System.out.printf("%d ", forward.pollFirst());
            }
        }
    }
}
