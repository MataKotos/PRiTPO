package lr4pt1;
import java.util.Scanner;

public class TugOfWar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        scanner.nextLine(); 
        scanner.nextLine();

        for (int t = 0; t < testCases; t++) {
            int n = scanner.nextInt();
            int[] weights = new int[n];
            int totalWeight = 0;

            for (int i = 0; i < n; i++) {
                weights[i] = scanner.nextInt();
                totalWeight += weights[i];
            }
            scanner.nextLine();

            int maxTeamSize = (n + 1) / 2;
            int targetWeight = totalWeight / 2;

            boolean[][] dp = new boolean[maxTeamSize + 1][targetWeight + 1];
            dp[0][0] = true;

            for (int weight : weights) {
                for (int i = maxTeamSize; i >= 1; i--) {
                    for (int j = targetWeight; j >= weight; j--) {
                        if (dp[i - 1][j - weight]) {
                            dp[i][j] = true;
                        }
                    }
                }
            }

            int bestWeight = 0;
            for (int j = targetWeight; j >= 0; j--) {
                for (int i = 1; i <= maxTeamSize; i++) {
                    if (dp[i][j]) {
                        bestWeight = j;
                        break;
                    }
                }
                if (bestWeight != 0) break;
            }

            int weight1 = bestWeight;
            int weight2 = totalWeight - weight1;

            if (weight1 < weight2) {
                System.out.println(weight1 + " " + weight2);
            } else {
                System.out.println(weight2 + " " + weight1);
            }

            if (t < testCases - 1) {
                System.out.println();
                scanner.nextLine();
            }
        }
    }
}