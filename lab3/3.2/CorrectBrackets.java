package lr3pt2;
import java.util.*;

public class CorrectBrackets {
    static final int MAX_N = 301;
    static final int MAX_D = 151;
    static long[][][] dp = new long[MAX_N][MAX_D][MAX_D];
    static boolean[][][] computed = new boolean[MAX_N][MAX_D][MAX_D];

    static long count(int n, int depth, int maxDepth) {
        if (n == 0) {
            return depth == 0 ? 1 : 0;
        }
        if (depth < 0 || depth > maxDepth) {
            return 0;
        }
        if (computed[n][depth][maxDepth]) {
            return dp[n][depth][maxDepth];
        }

        long res = 0;
        if (n >= 1) {
            res += count(n - 1, depth + 1, maxDepth);
        }
        if (n >= 1 && depth > 0) {
            res += count(n - 1, depth - 1, maxDepth);
        }

        computed[n][depth][maxDepth] = true;
        return dp[n][depth][maxDepth] = res;
    }

    static long countExactDepth(int n, int d) {
        if (n % 2 != 0 || d <= 0 || n < 2) return 0;
        return count(n, 0, d) - count(n, 0, d - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            if (parts.length != 2) continue;

            int n = Integer.parseInt(parts[0]);
            int d = Integer.parseInt(parts[1]);

            System.out.println(countExactDepth(n, d));
        }
    }
}
