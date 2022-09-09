package week5;

import java.util.*;
import java.io.*;
public class BOJ_1501_영어읽기_jbh {
	static int n,m;
	static HashMap<String,Integer> hm = new HashMap<>();
	static StringBuilder result = new StringBuilder();
	static void hmput(String s) {
		if(s.length()==0)
			return;
		StringBuilder sb = new StringBuilder();
		if(s.length()>=1)
			sb.append(s.charAt(0));
		if(s.length()>=3) {

			String sub = s.substring(1, s.length()-1);
			char[] ch = sub.toCharArray();
			Arrays.sort(ch);
			for(int i=0; i<ch.length; i++) {
				sb.append(ch[i]);
			}
		}
		if(s.length()>=2)
			sb.append(s.charAt(s.length()-1));
		
		hm.put(sb.toString(), hm.getOrDefault(sb.toString(), 0)+1);
		//System.out.println(sb.toString()+","+hm.get(sb.toString()));
	}
	static void print(String s) {
		if(s.length() == 0) {
			result.append(0+"\n");
			return;
		}
		String[] sl = s.split(" ");
		int temp  =1;
		for(int i=0; i<sl.length; i++) {
			StringBuilder sb = new StringBuilder();
			s = sl[i];
			if(s.length()==0) {
				result.append(0+"\n");
				return;
			}
			
			if(s.length()>=1)
				sb.append(s.charAt(0));
			if(s.length()>=3) {

				String sub = s.substring(1, s.length()-1);
				char[] ch = sub.toCharArray();
				Arrays.sort(ch);
				for(int j=0; j<ch.length; j++) {
					sb.append(ch[j]);
				}
			}
			if(s.length()>=2)
				sb.append(s.charAt(s.length()-1));
			temp *= hm.getOrDefault(sb.toString(), 0);
		}
		
		
		result.append(temp+"\n");
		
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			String s= br.readLine();
			hmput(s);
		}
		m = Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
			String s= br.readLine();
			print(s);
		}
		System.out.println(result.toString());
	}

}

