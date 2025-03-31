package lr2pt2;
import java.util.*;

public class OladiiDlyaKotyat {


	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        List<String> allLines = new ArrayList<>();

	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine().trim();
	            if (line.isEmpty()) break;
	            allLines.add(line);
	        }
	        scanner.close();

	        for (String line : allLines) {
	            int[] stack = parseOladii(line);
	            List<Integer> perevoroty = pancakeSort(stack);
	            System.out.println(line + formatPerevoroty(perevoroty));
	        }
	    }

	    private static int[] parseOladii(String line) {
	        String[] nums = line.split("\\s+");
	        int[] oladii = new int[nums.length];
	        for (int i = 0; i < nums.length; i++) {
	            oladii[i] = Integer.parseInt(nums[i]);
	        }
	        return oladii;
	    }

	    private static List<Integer> pancakeSort(int[] oladii) {
	        int n = oladii.length;
	        int[] sortedOladii = Arrays.copyOf(oladii, n);
	        List<Integer> perevoroty = new ArrayList<>();

	        for (int currSize = n; currSize > 1; currSize--) {
	            int maxIndex = findBigOladii(sortedOladii, currSize);

	            if (maxIndex != currSize - 1) {

	                if (maxIndex != 0) {
	                    perevoroty.add(n - maxIndex);
	                    perevorot(sortedOladii, maxIndex + 1);
	                }


	                perevoroty.add(n - (currSize - 1));
	                perevorot(sortedOladii, currSize);
	            }
	        }
	      return perevoroty;
	    }


	    private static int findBigOladii(int[] oladii, int limit) {
	        int maxIndex = 0;
	        for (int i = 1; i < limit; i++) {
	            if (oladii[i] > oladii[maxIndex]) {
	                maxIndex = i;
	            }
	        }
	        return maxIndex;
	    }

	    private static void perevorot(int[] oladii, int k) {
	        int i = 0;
	        int j = k - 1;
	        while (i < j) {
	            int temp = oladii[i];
	            oladii[i] = oladii[j];
	            oladii[j] = temp;
	            i++;
	            j--;
	        }
	    }
	    
	    private static String formatPerevoroty(List<Integer> perevoroty){
	        StringBuilder sb = new StringBuilder();
	        for(int flip : perevoroty){
	           sb.append(" ").append(flip);
	        }
	        sb.append(" ").append(0);
	        return sb.toString();
	    }
	}

