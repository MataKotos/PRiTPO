package lr3;
import java.util.Scanner;

public class PowerOfTwo {

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        while (scanner.hasNextInt()) {
	            int target = scanner.nextInt();
	            int power = findPowerOfTwo(target);

	            if (power != -1) {
	                System.out.println(power);
	            } else {
	                System.out.println("no power of 2");
	            }
	        }
	    }

	    static int findPowerOfTwo(int target) {
	        for (int exponent = 1; exponent <= 150; exponent++) {
	            double powerOfTwo = Math.pow(2, exponent);
	            String powerStr = String.valueOf(powerOfTwo);
	            int targetLength = String.valueOf(target).length();

	            if (powerStr.length() >= targetLength) {
	                String firstDigits = powerStr.substring(0, targetLength);
	                try{
	                    if (Integer.parseInt(firstDigits) == target) {
	                      return exponent;
	                    }
	                }catch (NumberFormatException e){
	                    System.err.println("ошибка");
	                    return -1;
	                }
	            }
	        }

	        return -1;
	    }
	}