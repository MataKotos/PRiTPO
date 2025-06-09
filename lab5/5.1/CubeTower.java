package lr5pt1;
import java.util.*;

public class CubeTower {
	    static class Cube {
	        int id;
	        int[] faces = new int[6];

	        public Cube(int id, int front, int back, int left, int right, int top, int bottom) {
	            this.id = id;
	            faces[0] = front;   // front
	            faces[1] = back;    // back
	            faces[2] = left;    // left
	            faces[3] = right;   // right
	            faces[4] = top;     // top
	            faces[5] = bottom;  // bottom
	        }
	    }

	    static class TowerBlock {
	        int height;
	        int prevIndex;
	        int faceIndex;

	        public TowerBlock(int height, int prevIndex, int faceIndex) {
	            this.height = height;
	            this.prevIndex = prevIndex;
	            this.faceIndex = faceIndex;
	        }
	    }

	    static String[] faceNames = {"front", "back", "left", "right", "top", "bottom"};

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        int caseNum = 1;

	        while (true) {
	            if (!scanner.hasNextInt()) break;
	            int n = scanner.nextInt();
	            if (n == 0) break;

	            Cube[] cubes = new Cube[n];
	            for (int i = 0; i < n; i++) {
	                int front = scanner.nextInt();
	                int back = scanner.nextInt();
	                int left = scanner.nextInt();
	                int right = scanner.nextInt();
	                int top = scanner.nextInt();
	                int bottom = scanner.nextInt();
	                cubes[i] = new Cube(i + 1, front, back, left, right, top, bottom);
	            }

	            // dp[i][j] — максимальная высота башни, если взять i-й куб и j-ю грань вверху
	            TowerBlock[][] dp = new TowerBlock[n][6];
	            for (int i = 0; i < n; i++)
	                Arrays.fill(dp[i], new TowerBlock(1, -1, -1));

	            int maxH = 0, maxI = 0, maxF = 0;

	            for (int i = 0; i < n; i++) {
	                for (int fi = 0; fi < 6; fi++) {
	                    int currentTopColor = cubes[i].faces[fi];
	                    for (int j = 0; j < i; j++) {
	                        for (int fj = 0; fj < 6; fj++) {
	                            int bottomColor = cubes[j].faces[opposite(fj)];
	                            if (bottomColor == currentTopColor && dp[j][fj].height + 1 > dp[i][fi].height) {
	                                dp[i][fi] = new TowerBlock(dp[j][fj].height + 1, j, fj);
	                            }
	                        }
	                    }

	                    if (dp[i][fi].height > maxH) {
	                        maxH = dp[i][fi].height;
	                        maxI = i;
	                        maxF = fi;
	                    }
	                }
	            }

	            List<String> result = new ArrayList<>();
	            int idx = maxI, face = maxF;
	            while (idx != -1 && face != -1) {
	                result.add(cubes[idx].id + " " + faceNames[face]);
	                TowerBlock block = dp[idx][face];
	                idx = block.prevIndex;
	                face = block.faceIndex;
	            }

	            Collections.reverse(result);
	            System.out.println("Case #" + caseNum);
	            System.out.println(result.size());
	            for (String s : result) {
	                System.out.println(s);
	            }
	            System.out.println();

	            caseNum++;
	        }

	        scanner.close();
	    }

	    static int opposite(int face) {
	        return switch (face) {
	            case 0 -> 1; // front ↔ back
	            case 1 -> 0;
	            case 2 -> 3; // left ↔ right
	            case 3 -> 2;
	            case 4 -> 5; // top ↔ bottom
	            case 5 -> 4;
	            default -> -1;
	        };
	    }
	}
