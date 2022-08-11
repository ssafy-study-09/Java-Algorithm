import java.util.*;
import java.io.*;
public class Main{

	static int n,d,k,c,result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n  = Integer.parseInt(st.nextToken());
		d  = Integer.parseInt(st.nextToken());
		k  = Integer.parseInt(st.nextToken());
		c  = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		for(int i=0; i<n; i++) {
			HashSet<Integer> set = new HashSet<>();
			int cnt = 0; int j = i;
			
			while(true) {
				cnt +=1;
				if(j == n)
					j = 0;
				set.add(arr[j++]);
				if(cnt == k)
					break;
			}
			set.add(c);
			result = Math.max(result, set.size());
			
			
		}
		System.out.println(result);
		
		
		
	}

}