public class A9_B1_14_practical2B {
    static final int INF = Integer.MAX_VALUE; 

  
    public static int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.round(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
    }

    public static void primMST(int cost[][], int n, String[] cities) {
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

////////////////////////////////////////////////////////////////////////////////////
            for (int v = 0; v < n; v++) {
                if (near[v] != -1 && cost[v][j] < cost[v][near[v]]) {
                    near[v] = j;
                }
            }
        }


        System.out.println("Connections in MST:");
        for (int i = 0; i < n - 1; i++) {
            int u = t[i][0];
            int v = t[i][1];
            System.out.println(cities[u] + " - " + cities[v] + " : " + cost[u][v]);
        }
        System.out.println("Minimum leased line cost = " + mincost);
    }

    public static void main(String[] args) {
        String[] cities = {"C1", "C2", "C3", "C4", "C5"};
        int[][] coords = {
            {2, 3},  
            {5, 4},  
            {1, 7},  
            {6, 1},  
            {8, 5}   
        };

        int n = cities.length;
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) cost[i][j] = INF;
                else cost[i][j] = distance(coords[i][0], coords[i][1],
                                           coords[j][0], coords[j][1]);
            }
        }

        primMST(cost, n, cities);
    }
}
