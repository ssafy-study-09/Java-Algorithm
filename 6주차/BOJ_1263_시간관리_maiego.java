import java.util.*;

public class Main {
    static class Item {
        int time, end;
        Item(int a, int b) {
            time=a; end=b;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Item[] arr = new Item[n];

        for (int i=0; i<n; ++i)
            arr[i] = new Item(sc.nextInt(), sc.nextInt());

        Arrays.sort(arr, (a,b)->b.end-a.end);

        int t = (int)1e9;
        for (int i=0; i<n; ++i)
            t = Math.min(t, arr[i].end) - arr[i].time;

        System.out.println(Math.max(-1, t));
    }

}