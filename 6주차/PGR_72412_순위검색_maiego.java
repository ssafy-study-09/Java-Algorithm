import java.util.*;

class Solution {
    static Map<String, List<Integer>> group = new HashMap<>();

    static void add(String k, int v) {
        List<Integer> arr = group.getOrDefault(k, new ArrayList<>());
        arr.add(v);
        group.put(k, arr);
    }

    public int[] solution(String[] info, String[] query) {
        for (String s: info) {
            int idx = s.lastIndexOf(" ");
            int score = Integer.parseInt(s.substring(idx+1));

            String[] splitted = s.substring(0,idx).split(" ");
            for (int subset=0; subset<(1<<4); ++subset) {
                StringBuilder k = new StringBuilder();
                for (int i=0; i<4; ++i) {
                    if ((subset&(1<<i)) > 0) {
                        k.append(splitted[i]).append(" ");
                    } else k.append("- ");
                }
                add(k.toString(), score);
            }
        }

        for (String k: group.keySet())
            Collections.sort(group.get(k));


        int[] answer = new int[query.length];
        int queryIdx = 0;
        for (String s: query) {
            s = s.replaceAll(" and ", " ");
            int idx = s.lastIndexOf((" "));
            int score = Integer.parseInt(s.substring(idx+1));
            String k = s.substring(0,idx+1);

            List<Integer> arr = group.get(k);
            if (arr==null) {
                answer[queryIdx++] = 0;
                continue;
            }

            idx = -1;
            int lo=0, hi=arr.size()-1;
            while (lo<=hi) {
                int mid = lo+hi>>1;
                if (arr.get(mid)<score) {
                    lo = mid+1;
                    idx = Math.max(idx, mid);
                }
                else hi = mid-1;
            }
            answer[queryIdx++] = arr.size()-1-idx;
        }
        return answer;
    }
}
