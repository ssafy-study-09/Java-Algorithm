import java.io.*;
import java.util.*;

public class Main  {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int[][] arr = new int[4][n];
			int minGap = (int)1e9;
			
			
			for(int i=0; i<4; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++)
					arr[i][j] = Integer.parseInt(st.nextToken());				
			}
			int[] arr1 = new int[n*n];
			int[] arr2 = new int[n*n];
			int cnt = 0;
			for(int a=0; a<n; a++) {
				for(int b=0; b<n; b++) {
					arr1[cnt] = arr[0][a]+arr[1][b];
					arr2[cnt] = arr[2][a]+arr[3][b];
					cnt+=1;
				}
				
			}
			Arrays.sort(arr2);
			Loop1:
			for(int i=0; i< arr1.length; i++) {
				int num = arr1[i];
				int left = 0; int right = arr2.length-1;
				while(left<=right) {
					int mid = (left+right)/2;
					int now = arr2[mid] + num;
					
					if(now == k) {
						minGap = k;
						break Loop1;
					}
					else if(now < k) {
						left = mid+1;
						
					}else {
						right = mid-1;
					}
					if(Math.abs(now -k) < Math.abs(minGap-k)) {
						minGap = now;
					}
					else if(Math.abs(now-k) == Math.abs(minGap-k) && now < minGap)
						minGap = now;
				}
				
				
			}
			
			sb.append(minGap+"\n");
			
		}
		System.out.println(sb.toString());
	}

}