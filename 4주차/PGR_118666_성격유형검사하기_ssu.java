import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class PGR_118666_성격유형검사하기_ssu {
    public String solution(String[] survey, int[] choices) {
        HashMap<Character,Integer> personalMap = new HashMap<>();
        String[] index = {"RT","CF","JM","AN"};
        StringBuilder sb = new StringBuilder();
        final int MID = 4;
        int size = survey.length;

        for(int i=0;i<size;i++){
            int current = choices[i];
            char neg = survey[i].charAt(0);
            char pos = survey[i].charAt(1);
            if(current<MID){
                personalMap.put(neg,personalMap.getOrDefault(neg,0)+MID-current);
            }
            else if(current>MID){
                personalMap.put(pos,personalMap.getOrDefault(pos,0)+current-MID);
            }
        }

        List<PriorityQueue<Personality>> pq = new ArrayList<>();
        for(int i=0;i<4;i++){
            pq.add(new PriorityQueue<>());
            String s = index[i];
            for(int j=0;j<2;j++){
                char c = s.charAt(j);
                pq.get(i).add(new Personality(c,personalMap.getOrDefault(c,0)));
            }
        }

        for(int i=0;i<4;i++){
            sb.append(pq.get(i).poll().personal);
        }

        return sb.toString();
    }

    static class Personality implements Comparable<Personality>{
        char personal;
        int count;

        public Personality(char personal,int count){
            this.personal = personal;
            this.count = count;
        }


        @Override
        public int compareTo(Personality o) {
            return this.count == o.count ? this.personal - o.personal : -(this.count - o.count);
        }
    }
}
