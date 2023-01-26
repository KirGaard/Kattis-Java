package DisjointSets;

import edu.princeton.cs.algs4.*;

import java.util.Arrays;

public class DisjointSets {

    static int[] leader;

    public static void main(String[] args){

        int singletons = StdIn.readInt();
        DisjointSets set = new DisjointSets(singletons);

        int max = StdIn.readInt();
        for (int i = 0; i < max; i++){
            int operation = StdIn.readInt();
            int s = StdIn.readInt();
            int t = StdIn.readInt();

            switch (operation){
                case 0:
                    query(s, t, set);
                    break;
                case 1:
                    set.union(s, t);
                    break;
                case 2:
                    set.move(s, t);
                    break;
            }
        }
    }

    public DisjointSets(int n){
        leader = new int[n];
        for (int i = 0; i < n; i++) {
            leader[i] = i;
        }
    }

    int find(int s){
        return leader[s];
    }

    void union(int s, int t){
        int sLeader = leader[s];
        int tLeader = leader[t];
        for (int i = 0; i < leader.length; i++) {
            if (leader[i] == sLeader){
                leader[i] = tLeader;
            }
        }
    }

    void move(int s, int t){
        int root = find(t);
        leader[s] = root;
    }

    static void query(int s, int t, DisjointSets set){
        StdOut.println(set.find(s) == set.find(t) ? 1 : 0);
    }
}
