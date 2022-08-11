import java.util.*;
import java.io.*;
public class Main {

	static int n,d,k,c,result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n  = Integer.parseInt(st.nextToken()); //접시 수 
		d  = Integer.parseInt(st.nextToken()); //초밥 수
		k  = Integer.parseInt(st.nextToken()); //연속  k<=n
		c  = Integer.parseInt(st.nextToken()); //쿠폰 번호  c<=d
		int[] arr = new int[n];
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(br.readLine());
	
		HashMap<Integer,Integer> hm = new HashMap<>();
		
		int start  = 0; int end = k-1;
		for(int i=start; i<=end; i++) {
			hm.put(arr[i], hm.getOrDefault(arr[i], 0)+1);
		}
		hm.put(c, hm.getOrDefault(c, 0)+1);
		result = hm.size();
		int cnt2 = 0;
		while(start<n) {
			int cnt = hm.get(arr[start]);
			if(cnt == 1)
				hm.remove(arr[start]);
			else
				hm.put(arr[start], cnt-1);
			start +=1; end +=1;
			if(end >=n)
				end -=n;
			hm.put(arr[end], hm.getOrDefault(arr[end], 0)+1);
			result = Math.max(result, hm.size());
		}
		System.out.println(result);
		
		
		
	}

}