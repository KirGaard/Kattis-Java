package DisjointSets;

import java.util.*;

public class DisjointSetsShort {
    static int[] leader;
    public static void main(String[]a){
        Scanner scanner = new Scanner(System.in);
        int singletons = scanner.nextInt(), max = scanner.nextInt();

        leader = new int[singletons];

        for (int i = 0; i < singletons; i++) {
            leader[i] = i;
        }

        for (int i = 0; i < max; i++){
            int operation = scanner.nextInt(), s = scanner.nextInt(), t = scanner.nextInt();

            switch (operation){
                case 0:
                    System.out.println(leader[s] == leader[t] ? 1 : 0);
                    break;
                case 1:
                    u(s, t);
                    break;
                case 2:
                    leader[s] = leader[t];
                    break;
            }
        }
    }
    static void u(int s,int t){
        int sLeader = leader[s];
        int tLeader = leader[t];

        for (int j = 0; j < leader.length; j++) {
            if (leader[j] == sLeader){
                leader[j] = tLeader;
            }
        }
    }
}
