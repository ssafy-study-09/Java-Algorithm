package week7;
import java.util.*;
import java.io.*;
public class BOJ_17136_색종이붙이기_jbh {

	static int[][] map = new int[10][10];
	static int[] cnt = {0,5,5,5,5,5};
	static int result = (int)1e9;
	static int n = 10;
	static void dfs(int x,int y,int count) {
		if(result <= count)
			return;
		if(x>=10) {
			result = Math.min(result, count);
			return;
		}
		
		if (map[x][y] == 1) {
            for (int i = 5; i >= 1; i--) {
                if (cnt[i] > 0 && isPossible(x, y, i)) {
                    changeMap(x, y, i, 0);
                    cnt[i] -=1;
                    if(y == 9) {
                    	dfs(x+1,0,count+1);
                    } else
                    	dfs(x,y+1,count+1);
                    changeMap(x, y, i, 1);
                    cnt[i]+=1;
                }
            }
        } 
		else {
        	if(y == 9) {
            	dfs(x+1,0,count);
            } else
            	dfs(x,y+1,count);
        }
	}
	static void changeMap(int x,int y,int l,int value) {
		for (int i = x; i < x + l; i++) {
            for (int j = y; j < y + l; j++) {
                map[i][j] = value;
            }
        }
	}
	
	static boolean isPossible(int x,int y,int l) {
		for (int i = x; i < x + l; i++) {
            for (int j = y; j < y + l; j++) {
                if (i < 0 || i >= 10 || j < 0 || j >= 10) {
                    return false;
                }
 
                if (map[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0,0,0);
		System.out.println( (result != (int)1e9) ? result : -1);
		
	}
	
}
