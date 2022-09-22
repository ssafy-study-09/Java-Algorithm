import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        int[] d = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            d[i] = 1;
        }
        // 감소하는 최대길이
        for(int i=1; i<=n; i++){
            for(int j=1; j<i; j++){
                if(arr[j]> arr[i]){
                    d[i] = Math.max(d[j]+1, d[i]);
                }
            }
        }

        int max = 0;
        for(int i=1; i<=n; i++){
            max = Math.max(max, d[i]);
        }

        System.out.println(n-max);

    }
}
