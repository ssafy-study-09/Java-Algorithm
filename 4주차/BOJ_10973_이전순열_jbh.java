package week4;
import java.util.*;
import java.io.*;
public class BOJ_10973_이전순열_jbh {

	static int N, arr[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		if(NextPermutation(arr)) {
			for (int a : arr) {
				System.out.print(a+" ");
			}
		} else {
			System.out.println(-1);
		}
		
		
	}
	
	public static boolean NextPermutation(int[] arr) {
		int n = arr.length;
		
		int i = n-1;
		
		while(i>0 && arr[i-1]>=arr[i]) i--;
		
		if(i==0) return false;
		
		int j = n-1;
		
		while(arr[i-1]>=arr[j]) j--;
		
		Swap(arr,i-1,j);
		
		int k = n-1;
		
		while(i<k) Swap(arr,i++,k--);
		return true;
		
	}

	public static void Swap(int[] arr,int i,int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
