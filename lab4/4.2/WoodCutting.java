package lr4pt2;
import java.util.Arrays;
import java.util.Scanner;

public class WoodCutting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            int A = scanner.nextInt();
            if (A == 0) break;
            
            int B = scanner.nextInt();
            int[] cuts = new int[B + 2];
            cuts[0] = 0;
            for (int i = 1; i <= B; i++) {
                cuts[i] = scanner.nextInt();
            }
            cuts[B + 1] = A;
            
            int n = cuts.length;
            int[][] dp = new int[n][n];
            
            for (int length = 2; length < n; length++) {
                for (int i = 0; i + length < n; i++) {
                    int j = i + length;
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i + 1; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + cuts[j] - cuts[i]);
                    }
                }
            }
            
            System.out.printf("The minimum cutting price is %d.\n", dp[0][n - 1]);
        }
        
        scanner.close();
    }
}
