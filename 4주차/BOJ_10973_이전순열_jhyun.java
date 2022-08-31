import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        if(N == 1){
            System.out.println(-1);
            return;
        }
        int idx;
        for(idx = N - 1; idx > 0; idx--){
            if(arr[idx - 1] > arr[idx]){
                break;
            }
        }
        if(idx == 0){
            System.out.println(-1);
            return;
        }
        int j = N - 1;
        while(arr[idx - 1] < arr[j]){
            j--;
        }
        int temp = arr[idx - 1];
        arr[idx - 1] = arr[j];
        arr[j] = temp;

        List<Integer> list = new ArrayList<>();
        for(int i = idx; i < N; i++){
            list.add(arr[i]);
        }
        list.sort(Collections.reverseOrder());
        for(int k = 0; k < idx; k++){
            System.out.printf("%d ", arr[k]);
        }
        for(Integer item: list){
            System.out.printf("%d ", item);
        }
    }
}
