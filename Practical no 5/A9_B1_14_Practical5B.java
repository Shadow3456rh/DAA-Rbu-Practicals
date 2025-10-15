public class A9_B1_14_Practical5B {

    static int[][] Task2Table(String S) {  
        int n = S.length();
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (S.charAt(i - 1) == S.charAt(j - 1) && i != j)
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp;
    }

    static void printLRS(String S, int[][] dp) {
        int i = S.length(), j = S.length();
        String lrs = "";

        while (i > 0 && j > 0) {
            if (S.charAt(i - 1) == S.charAt(j - 1) && i != j) {
                lrs = S.charAt(i - 1) + lrs;
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        System.out.println("Longest Repeated Subsequence: " + lrs);
        System.out.println("LRS Length: " + lrs.length());
    }

    public static void main(String args[]) {
        String S = "AABCBDC";
        int[][] table = Task2Table(S);
        printLRS(S, table);
    }
}
