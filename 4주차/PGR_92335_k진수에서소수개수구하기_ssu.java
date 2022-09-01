import java.util.StringTokenizer;

public class PGR_92335_k진수에서소수개수구하기_ssu {
    public int solution(int n,int k){
        String convertStr = getKNotation(n,k);
        StringTokenizer st = new StringTokenizer(convertStr,"0");
        int result = 0;
        while(st.hasMoreTokens()){
            if(isPrimeNumber(Long.parseLong(st.nextToken()))) result++;
        }
        return result;
    }

    public static String getKNotation(int n,int k){
        if(n<k){
            return String.valueOf(n);
        }
        return String.valueOf(getKNotation(n/k,k))+String.valueOf(n%k);
    }

    public boolean isPrimeNumber(long n){
        if(n==1) return false;
        for(long i=2;i<=(long)Math.sqrt(n);i++){
            if(n%i==0) return false;
        }
        return true;
    }
}
