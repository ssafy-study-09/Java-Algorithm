import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2805_나무자르기_ssu {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] height = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		int lowest = 0;
		int highest = -1;
		for(int i=0;i<N;i++) {
			if(height[i]>highest) highest = height[i];
		}
		
		while(lowest<=highest) {
			int midH = (lowest+highest)/2;
			long sumHeight = 0;
			for(int i=0;i<N;i++) {
				if(height[i]<=midH) continue;
				sumHeight += height[i]-midH;
			}
			
			if(sumHeight>=M) {
				lowest = midH+1;
			}
			else if(sumHeight<M) {
				highest = midH-1;
			}
		}
		
		System.out.println(highest);
	}

}
