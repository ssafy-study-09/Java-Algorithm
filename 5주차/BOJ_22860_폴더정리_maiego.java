import java.util.*;

public class Main {
	static Map<String,Integer> name2id;
	static String[] id2name;
	static int[] kindCnt, fileCnt;
	static Set<String>[] fileSet;
	
	static List<Integer> adj[];
	
	static void dfs(int u) {
		for (int v: adj[u]) {
			dfs(v);
			for (String s: fileSet[v])
				fileSet[u].add(s);
			fileCnt[u] += fileCnt[v];
		}
		kindCnt[u] = fileSet[u].size();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int dirN = sc.nextInt();
		int fileN = sc.nextInt();

		id2name = new String[dirN+1];
		id2name[0] = "main";
		name2id = new HashMap<>();
		name2id.put("main", 0);
		
		kindCnt = new int[dirN+1];
		fileCnt = new int[dirN+1];
		fileSet = new TreeSet[dirN+1];
		for (int i=0; i<=dirN; ++i)
			fileSet[i] = new TreeSet<>();
		
		int cur=0;
		
		adj = new List[dirN+1];
		for (int i=0; i<=dirN; ++i)
			adj[i] = new ArrayList<>();

		for (int i=0; i<dirN+fileN; ++i) {
			String p = sc.next();
			String s = sc.next();
			if (name2id.get(p)==null) {
				name2id.put(p, ++cur);
				id2name[cur]=p;
			}
			int pid = name2id.get(p);
			if (sc.nextInt()==1) {
				if (name2id.get(s)==null) {
					name2id.put(s, ++cur);
					id2name[cur]=s;
				}
				adj[pid].add(name2id.get(s));
			} else {
				fileSet[pid].add(s);
				fileCnt[pid]++;
			}
		}
		
		dfs(0);
		
		int q = sc.nextInt();
		while (q-->0) {
			String s = sc.next();
			String[] path = s.split("/");
			s = path[path.length-1];
			
			int idx = name2id.get(s);
			sb.append(String.format("%d %d\n", kindCnt[idx], fileCnt[idx]));
		}
		
		System.out.println(sb);

	}

}