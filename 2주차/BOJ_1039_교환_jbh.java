import java.util.*;
public class Main{

	static int n,k,m, max = -1;
	static int l;
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(n);
		int count = 0;
		boolean[][] visited = new boolean[1000001][11];
		visited[n][0] = true;
		while(!q.isEmpty()) {
			if(count == k) {
				max = Math.max(max, q.poll());
				continue;
			}
            count +=1;
			int size = q.size();
			for(int t=0; t<size; t++) {
				int now  = q.poll();
				
				
				for(int i=0; i<l-1; i++) {
					for(int j=i+1; j<l; j++) {
						StringBuilder sb = new StringBuilder(Integer.toString(now));
						//swap 조건 체크하고
						if(i == 0 && sb.charAt(j) == '0')
							continue;
						int tmp1 = sb.charAt(i)-'0'; int tmp2= sb.charAt(j)-'0';
						//swap해서  q에 넣음 
						sb.insert(i, tmp2);
						sb.deleteCharAt(i+1);
						sb.insert(j, tmp1);
						sb.deleteCharAt(j+1);
						int a = Integer.parseInt(sb.toString());
						if(!visited[a][count]) {
							visited[a][count] = true;
							q.offer(a);
						}
					}
				}
			}
			
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		l = Integer.toString(n).length();
		bfs();
		System.out.println(max);
		
		
	}

}