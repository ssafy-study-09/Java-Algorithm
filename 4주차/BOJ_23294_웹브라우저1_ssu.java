import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_23294_웹브라우저1_ssu {
    public static void main(String[] args) throws Exception {
        Stack<Integer> Forward = new Stack<>();
        Deque<Integer> Backward = new ArrayDeque<>();
        Deque<Integer> Temp;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] size = new int[N+1];
        int used = 0;
        for(int i=1;i<=N;i++) {
            size[i] = Integer.parseInt(st.nextToken());
        }
        int currentId=-1;
        boolean isFirst = true;
        while(Q-->0) {
            String input = br.readLine();
            switch(input.charAt(0)) {
                case 'B':
                    if(!Backward.isEmpty()) {
                        Forward.push(currentId);
                        currentId = Backward.pollLast();
                    }
                    break;
                case 'F':
                    if(!Forward.isEmpty()) {
                        Backward.addLast(currentId);
                        currentId = Forward.pop();
                    }
                    break;
                case 'A':
                    st = new StringTokenizer(input);
                    st.nextToken();
                    int targetId = Integer.parseInt(st.nextToken());
                    while(!Forward.isEmpty()) {
                        used -= size[Forward.pop()];
                    }

                    if(!isFirst) {
                        Backward.addLast(currentId);
                    }
                    else {
                        isFirst=false;
                    }

                    currentId = targetId;
                    used += size[currentId];
                    if(used>C) {
                        while(used>C) {
                            used -= size[Backward.pop()];
                        }
                    }
                    break;
                case 'C':
                    Temp = new ArrayDeque<>();
                    while(!Backward.isEmpty()) {
                        Temp.addLast(Backward.pop());
                    }

                    int compress = -1;
                    while(!Temp.isEmpty()) {
                        if(compress == Temp.peek()) {
                            used -= size[Temp.pop()];
                        }
                        else {
                            compress = Temp.peek();
                            Backward.addLast(compress);
                            used += size[compress];
                        }
                    }
                    break;
            }
        }
        System.out.println(currentId);

        if(Backward.size()==0) {
            System.out.println(-1);
        }
        else {
            while(!Backward.isEmpty()) {
                System.out.print(Backward.pollLast()+" ");
            }
            System.out.print("\n");
        }

        if(Forward.size()==0) {
            System.out.println(-1);
        }
        else {
            while(!Forward.isEmpty()) {
                System.out.print(Forward.pop()+" ");
            }
            System.out.print("\n");
        }

    }
}
