
public class A9_B1_14_practical2A {
    static final int INF = Integer.MAX_VALUE; 

    public static void primMST(int cost[][], int n) {
        int[] near = new int[n];       
        int[][] t = new int[n - 1][2]; 
        int mincost = 0;

        int k = -1, l = -1, min = INF;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (cost[i][j] < min) {
                    min = cost[i][j];
                    k = i;
                    l = j;
                }
            }
        }

        mincost = cost[k][l];
        t[0][0] = k;
        t[0][1] = l;

        for (int i = 0; i < n; i++) {
            if (cost[i][k] < cost[i][l])
                near[i] = k;
            else
                near[i] = l;
        }

        near[k] = near[l] = -1;

        for (int i = 1; i < n - 1; i++) {
            int j = -1;
            min = INF;
            for (int v = 0; v < n; v++) {
                if (near[v] != -1 && cost[v][near[v]] < min) {
                    min = cost[v][near[v]];
                    j = v;
                }
            }

            t[i][0] = j;
            t[i][1] = near[j];
            mincost += cost[j][near[j]];
            near[j] = -1;


            for (int v = 0; v < n; v++) {
                if (near[v] != -1 && cost[v][j] < cost[v][near[v]]) {
                    near[v] = j;
                }
            }
        }

        System.out.println("Edges in MST:");
        for (int i = 0; i < n - 1; i++) {
            int u = t[i][0];
            int v = t[i][1];
            System.out.println("F" + (u + 1) + " - F" + (v + 1) + " : " + cost[u][v]);
        }
        System.out.println("Minimum ink required = " + mincost);
    }

    public static void main(String[] args) {
        int INF = Integer.MAX_VALUE;
        int[][] cost = {
            {INF, 2, INF, 6, INF},
            {2, INF, 3, 8, 5},
            {INF, 3, INF, INF, 7},
            {6, 8, INF, INF, 9},
            {INF, 5, 7, 9, INF}
        };

        int n = cost.length;
        primMST(cost, n);
    }
}
