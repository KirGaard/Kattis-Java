package DisjointSets;

import java.util.Scanner;

public class WeightedQuickUnionWithPathCompressionDS {

    private int[] parent;
    private int[] size;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt();
        var set = new WeightedQuickUnionWithPathCompressionDS(size);

        int loopLength = sc.nextInt();
        for (int i = 0; i < loopLength; i++) {
            int operation = sc.nextInt();
            int s = sc.nextInt();
            int t = sc.nextInt();

            switch (operation){
                case 0:
                    set.query(s, t);
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

    public WeightedQuickUnionWithPathCompressionDS(int size){
        parent = new int[size];
        this.size = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            this.size[i] = 1;
        }
    }

    private int find(int s){
        int root = s;

        // We iterate up through the tree stopping when we find the root
        while (root != parent[root]){
            root = parent[root];
        }

        // Using path compression we set every item's parent in the branch to the root
        while(s != root){
            int newS = parent[s];
            parent[s] = root;
            s = newS;
        }

        return root;
    }

    private void move(int s, int t){
        int sSize = size[s];

        if (sSize == 1){
            // If the item s doesn't have any children, we just move the parent point to t's root
            parent[s] = find(t);
        }else{
            // We must move the item s's children into it's parent

            int count = 0;
            int i = 0;

            // Both of these in this case are fairly unoptimized - O(n) in the worst case
            if (parent[s] == s){
                // s is the root, we must assign a new root then
                int newRoot = -1;

                while (count != sSize){
                    if (parent[i] == s){
                        if (newRoot == -1){
                            newRoot = i;
                            parent[i] = i;
                        }else{
                            parent[i] = newRoot;
                        }
                        count++;
                    }
                    i++;
                }

            }else{
                while (count != sSize){
                    if (parent[i] == s){
                        parent[i] = parent[s];
                        count++;
                    }
                    i++;
                }
            }
        }
    }

    private void union(int s, int t){
        int rootS = find(s);
        int rootT = find(t);

        if (rootS == rootT) return;

        if (size[rootS] < size[rootT]){
            parent[rootS] = parent[rootT];
            size[rootT] += size[rootS];
        }else{
            parent[rootT] = parent[rootS];
            size[rootS] += size[rootT];
        }
    }

    private void query(int s, int t){
        System.out.println(find(s) == find(t) ? 1 : 0);
    }

}
