import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int[][] students= new int[4][n];
            for(int i=0,size=students.length;i<size;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++){
                    students[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[] comb1 = new int[n*n];
            int[] comb2 = new int[n*n];
            int idx=0;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    comb1[idx] = students[0][i] + students[1][j];
                    comb2[idx++] = students[2][i] + students[3][j];
                }
            }

            Arrays.sort(comb1);
            Arrays.sort(comb2);

            int lP = 0;
            int rP = idx-1;

            int diff = (int)1e9;
            int result = (int)1e9;

            while(lP<idx && rP>=0){
                int current = Math.abs(comb1[lP]+comb2[rP]-k);
                if(current<diff){
                    diff = current;
                    result = comb1[lP] + comb2[rP];
                }
                else if(current==diff){
                    if(result > comb1[lP] + comb2[rP]){
                        result = comb1[lP] + comb2[rP];
                    }
                }

                if(comb1[lP] + comb2[rP]>k){
                    --rP;
                }
                else if(comb1[lP] + comb2[rP] <k){
                    lP++;
                }
                else{
                    break;
                }
            }

            if(lP<idx && rP>=0){
                if(Math.abs(comb1[lP]+comb2[rP]-k)>diff){
                    diff = Math.abs(comb1[lP]+comb2[rP]-k);
                    result = comb1[lP] + comb2[rP];
                }
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
}
