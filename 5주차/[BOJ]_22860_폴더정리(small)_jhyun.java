import java.util.*;
import java.io.*;
//1차: 입력이 항상 상위에서 하위로 연결되어 있는 경우임이 보장되지 않아서 틀린듯
class Node{
    String name;
    int type;
    public Node(String name, int type) {
        this.name = name;
        this.type = type;
    }
}
public class Main{
    static int N, M, Q;
    static Set<String> kind = new HashSet<>();
    static int fileCount;
    static Map<String, List<Node>> map = new HashMap<>();
    static void find(String cur){
        if(!map.containsKey(cur)) return;
        for(Node node: map.get(cur)){
            if(node.type == 0){
                kind.add(node.name);
                fileCount++;
            }
        }
        for(Node node: map.get(cur)){
            if(node.type == 1){
                find(node.name);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map.put("main", new ArrayList<>());
        for(int i = 0; i < N + M; i++){
            st = new StringTokenizer(br.readLine());
            String parent = st.nextToken(), child = st.nextToken();
            int type = Integer.parseInt(st.nextToken());
            if(!map.containsKey(parent))
                map.put(parent, new ArrayList<>());
            map.get(parent).add(new Node(child, type));
        }
        Q = Integer.parseInt(br.readLine());
        for(int i = 0; i < Q; i++){
            kind.clear();
            fileCount = 0;
            String query = br.readLine();
            String[] paths = query.split("/");
            find(paths[paths.length - 1]);
            System.out.printf("%d %d\n", kind.size(), fileCount);
        }
    }
}