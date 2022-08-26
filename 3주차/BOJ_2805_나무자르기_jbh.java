import java.io.;
import java.util.;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] height = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; in; i++)
			height[i] = Integer.parseInt(st.nextToken());
		int result = 0;
		int start = 0; int end = (int)2e9;
		while(start=end) {
			int mid = (start+end)2;
			long sum = 0;
			for(int i=0; in; i++) {
				if(height[i]  mid)
					sum += height[i] -mid;
			}
			if(sum  m)
				end = mid-1;
			else {
				result = mid;
				start = mid+1;
			}
		}
		System.out.println(result);
	}

}