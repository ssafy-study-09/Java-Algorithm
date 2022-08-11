import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2531_회전초밥_ssu {
	static int N,d,k,c;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int[] dishes = new int [N];
		int[] sum = new int[d+1];
		for(int i=0;i<N;i++) {
			dishes[i] = Integer.parseInt(br.readLine());
		}
		
		int maxVal = 0;
		for(int i=0;i<k;i++) {
			sum[dishes[i]]++;
		}
		maxVal = dishCnt(sum);
		
		for(int i=0;i<N;i++) {
			sum[dishes[i]]--;
			if(i>=N-k) {
				sum[dishes[i%(N-k)]]++;	
			}
			else {
				sum[dishes[i+k]]++;				
			}
			maxVal = Math.max(maxVal, dishCnt(sum));
		}
		System.out.println(maxVal);
		
		
	}
	
	public static int dishCnt(int[] sum) {
		int tempSum=0;
		for(int i=1;i<=d;i++) {
			if(i==c && sum[i]==0) {
				tempSum++;
				continue;
			}
			if(sum[i]>0) tempSum++;
		}
		return tempSum;
	}

}
