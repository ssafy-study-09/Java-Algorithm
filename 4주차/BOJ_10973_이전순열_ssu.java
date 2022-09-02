import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_10973_이전순열_ssu {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[N];
        for(int i=0;i<N;i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        if(PrevPermutation(numbers)){
            for(int i=0;i<N;i++) {
                sb.append(numbers[i]).append(" ");
            }
        }
        else{
            sb.append(-1);
        }
        System.out.println(sb.toString());
//        do{
//            for(int i=0;i<N;i++){
//                System.out.print(numbers[i]+" ");
//            }
//            System.out.println();
//        }while(PrevPermutation(numbers));
    }

    public static boolean PrevPermutation(int[] arr){
        int n = arr.length;

        int i = n-1;

        while(i>0 && arr[i-1]<arr[i]) i--;

        if(i==0) return false;

        int j = n-1;

        while(arr[i-1]<=arr[j]) j--;
        Swap(arr,i-1,j);

        int k = n-1;

        while(i<k) Swap(arr,i++,k--);
        return true;
    }

    public static void Swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
