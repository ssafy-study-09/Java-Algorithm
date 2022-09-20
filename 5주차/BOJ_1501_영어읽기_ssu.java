import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class BOJ_1501_영어읽기_ssu {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringBuilder result = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        HashMap<String,HashMap<String,Integer>> cmpwordMap = new HashMap<String, HashMap<String, Integer>>();
        HashSet<String> wordSet = new HashSet<>();

        while(N-->0){
            char[] origin = br.readLine().toCharArray();
            int size = origin.length;

            // 단어의 길이 2이하이면 해석되는 경우의 수는 유일함.
            if(size<3){
                wordSet.add(String.valueOf(origin));
            }
            else{
                sb.setLength(0);
                sb.append(origin[0]).append(origin[size-1]);

                char[] suborigin = new char[size-2];
                for(int i=0,sSize=suborigin.length;i<sSize;i++){
                    suborigin[i] = origin[i+1];
                }
                //부분문자열을 정렬하면, 같은것이 포함된 순열을 하나로 보기에 의해 해석가능한 경우의 수를 구할 수 있다.
                Arrays.sort(suborigin);

                String key = sb.toString();
                String subKey = String.valueOf(suborigin);

                if(cmpwordMap.get(key)==null){
                    HashMap<String,Integer> temp = new HashMap<>();
                    temp.put(subKey,1);
                    cmpwordMap.put(key,temp);
                }
                else{
                    HashMap<String,Integer> current = cmpwordMap.get(key);
                    current.put(subKey, current.getOrDefault(subKey,0)+1);
                }
            }
        }

        int M = Integer.parseInt(br.readLine());
        while(M-->0){
            int totalCnt = 1;
            String[] wordArr = br.readLine().split(" ");
            for(String s : wordArr){
                char[] chars = s.toCharArray();
                int size = chars.length;
                if(size<3){
                    int tempCnt = 0;
                    if(wordSet.contains(String.valueOf(chars))) tempCnt=1;
                    totalCnt*=tempCnt;
                }
                else{
                    sb.setLength(0);
                    sb.append(chars[0]).append(chars[size-1]);

                    char[] subchars = new char[size-2];
                    for(int i=0,sSize=subchars.length;i<sSize;i++){
                        subchars[i] = chars[i+1];
                    }
                    Arrays.sort(subchars);

                    String key = sb.toString();
                    String subKey = String.valueOf(subchars);

                    int tempCnt = cmpwordMap.get(key).get(subKey);
                    totalCnt *= tempCnt;
                }
            }

            result.append(totalCnt).append("\n");
        }
        System.out.print(result.toString());
    }
}