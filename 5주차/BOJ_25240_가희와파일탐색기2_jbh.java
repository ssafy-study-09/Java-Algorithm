package week5;
import java.util.*;
import java.io.*;
public class BOJ_25240_가희와파일탐색기2_jbh {

	static int u,f;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		HashSet<String> isExist = new HashSet<String>();
		u = Integer.parseInt(st.nextToken());
		f = Integer.parseInt(st.nextToken());
		HashMap<String,HashSet<String>> group = new HashMap<String,HashSet<String>>();
		HashMap<String,List<String>> file = new HashMap<String,List<String>>();
		
		for(int i=0; i<u; i++) {
			HashSet<String> s = new HashSet<>();
			st = new StringTokenizer(br.readLine(),", ");
			String name = st.nextToken();
			while(st.hasMoreTokens()) {
				s.add(st.nextToken());
			}
			s.add(name);
			group.put(name, s);
		}
		for(int i=0; i<f; i++) {
			List<String> ls = new ArrayList<String>();
			st = new StringTokenizer(br.readLine());
			String gname = st.nextToken();
			for(int a=0; a<3; a++)
				ls.add(st.nextToken());
			file.put(gname, ls);
		}
		int q = Integer.parseInt(br.readLine());
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			String username = st.nextToken();
			String filename = st.nextToken();
			char target = st.nextToken().charAt(0);
			int permit = 0;
			HashSet<String> ugp = group.get(username);
			List<String> file_info = file.get(filename);
			if(username.equals(file_info.get(1))) {
				permit = file_info.get(0).charAt(0)-'0';
			} else if(ugp.contains(file_info.get(2))) {
				permit = file_info.get(0).charAt(1)-'0';
			} else
				permit = file_info.get(0).charAt(2)-'0';
			if(target =='X') {
				if(permit >0 && permit % 2 == 1)
					sb.append(1+"\n");
				else
					sb.append(0+"\n");
				
			}
			//r 읽기 w 수정 x 실행
		}
		
	}

}
