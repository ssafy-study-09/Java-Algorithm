import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크_ssu {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] stats = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				stats[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] index = new int[N];
		int cnt=0;
		int minDiff = (int)1e9;
		// 4개 짜리 중에 2개를 선택
		// 0 0 0 0
		// 0 0 1 1
		// 0 1 0 1 -> 1 기록 위치의 숫자를 선택 -> 다음 조합
		
		while(++cnt<=N/2) index[N-cnt]=1;
		do
		{
			int[] start = new int[N/2];
			int[] link = new int[N/2];
			int sidx = 0;
			int lidx = 0;
			for(int i=0;i<N;i++) {
				if(index[i]==1) {
					start[sidx++] = i;
				}
				else {
					link[lidx++] = i;
				}
			}
			
			minDiff = Math.min(minDiff, Math.abs(sumStats(start,stats)-sumStats(link,stats)));
		}while(NextPermutation(index));
		
		System.out.println(minDiff);
	}
	
	public static int sumStats(int[] arr,int[][] stats) {
		int sumStat = 0;
		int teamSize = arr.length;
		for(int i=0;i<teamSize;i++) {
			for(int j=0;j<teamSize;j++) {
				if(i==j) continue;
				sumStat += stats[arr[i]][arr[j]];
			}
		}
		
		return sumStat;
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
