package lr2;

	import java.util.*;

	public class commonLetters {

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        while (scanner.hasNextLine()) {
	            String a = scanner.nextLine();
	            if (!scanner.hasNextLine()) break;
	            String b = scanner.nextLine();

	            Map<Character, Integer> countA = new HashMap<>();
	            Map<Character, Integer> countB = new HashMap<>();

	            for (char c : a.toCharArray()) {
	                countA.put(c, countA.getOrDefault(c, 0) + 1);
	            }
	            for (char c : b.toCharArray()) {
	                countB.put(c, countB.getOrDefault(c, 0) + 1);
	            }

	            StringBuilder result = new StringBuilder();
	            List<Character> commonChars = new ArrayList<>();
	            for (char c = 'a'; c <= 'z'; c++) {
	                if (countA.containsKey(c) && countB.containsKey(c)) {
	                    commonChars.add(c);
	                }
	            }

	            Collections.sort(commonChars);

	            for (char c : commonChars) {
	                int count = Math.min(countA.get(c), countB.get(c));
	                for (int i = 0; i < count; i++) {
	                    result.append(c);
	                }
	            }

	            System.out.println(result.toString());
	        }
	        scanner.close();
	    }
	}
