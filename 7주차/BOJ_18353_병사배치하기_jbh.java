package week7;
import java.util.*;
import java.io.*;

public class BOJ_18353_병사배치하기_jbh {
	static int n;
	static int[] arr,dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n =  Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[n]; dp = new int[n];
		for(int i=n-1; i>=0; i--)
			arr[i] = Integer.parseInt(st.nextToken());
		int length = 0;
		for(int i=0; i<n; i++) {
			int index = bs(arr[i],length,length+1);
			if(index >=0)
				dp[index] = arr[i];
			else
				dp[length++] = arr[i];
		}
		System.out.println(n-length);
	
	}
	static int bs(int now,int right,int l) {
		int idx=0; int left = 0;
		while(left<=right) {
			int mid = (left+right)/2;
			if(now <= dp[mid]) {
				idx = mid;
				right = mid-1;
			}  else
				left = mid+1;	
			
		}
		
		
		return (left != l)? idx:-1;
		
	}
}
