public class A9_B1_14_Practical5A {

    static int[][] Task1(String[] X, String[] Y){
        int n = X.length+1;
        int m = Y.length+1;
        int[][] L = new int[n][m];

        for(int i=0;i<n;i++){
            L[i][0]=0;
        }
        for(int j=0;j<m;j++){
            L[0][j]=0;
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(X[i-1].equalsIgnoreCase(Y[j-1])){
                    L[i][j]=(L[i-1][j-1])+1;
                }
                else{
                    L[i][j]= Integer.max(L[i-1][j], L[i][j-1]);
                }
            }
        }
        return L;
    }

    


    static void printLCS(String[] X, String[] Y, int[][] L){
        int i = X.length;
        int j = Y.length;
        String lcs = "";

        while(i > 0 && j > 0){
            if(X[i-1].equalsIgnoreCase(Y[j-1])){
                lcs = X[i-1] + lcs;  // prepend matched char
                i--; j--;
            }
            else if(L[i-1][j] > L[i][j-1]){
                i--;
            }
            else{
                j--;
            }
        }

        System.out.println("\nLongest Common Subsequence: " + lcs);
        System.out.println("Total Length of LCS: " + lcs.length());
    }

    public static void main(String args[]){
        String X="AGCCCTAAGGGCTACCTAGCTT";
        String Y="GACAGCCTACAAGCGTTAGCTTG";
        String[] y = new String[Y.length()];
        String[] x = new String[X.length()];

        for(int i=0;i<X.length();i++){
            x[i]=X.substring(i,i+1);
        }

        for(int i=0;i<Y.length();i++){
            y[i]=Y.substring(i,i+1);
        }

        int[][] table = Task1(x, y);
        printLCS(x, y, table); // 🔹 Added call to print LCS and its length
    }
}
