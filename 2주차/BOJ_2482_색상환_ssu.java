import java.util.Scanner;

public class Main {
    static final int moduler = 1000000003;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        int[][] dpTable = new int[N+1][N+1];
        for(int i=0;i<=N;i++){
            dpTable[i][0] = 1;
            dpTable[i][1] = i;
        }

        for(int i=2;i<=N;i++){
            for(int j=2;j<=K;j++){
                dpTable[i][j] = (dpTable[i-2][j-1]+ dpTable[i-1][j])%moduler;
            }
        }

        System.out.println((dpTable[N-1][K]+dpTable[N-3][K-1])%moduler);
    }
}