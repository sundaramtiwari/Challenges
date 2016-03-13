package challenges;

import java.util.HashSet;
import java.util.Set;

public class FindDistinctDigitsInNumber {

	public static void main(String[] args) {
		System.out.println(distinctDigits(-11234));
	}

	public static int distinctDigits(int n) {
		String str = n + "";
		Set<Character> set = new HashSet<Character>();
		char[] array = str.toCharArray();
		for (int i=0; i<array.length; i++) {
			if (array[i] != '-' && array[i] != '+') {
				set.add(array[i]);
			}
		}
		return set.size();
	}

	public static int solution(int number) {
		if (number == 0) {
			return 1;
		}

		boolean[] digitsFound = new boolean[10];
		while (number != 0) {
			digitsFound[number % 10] = true;
			number /= 10;
		}

		return numberOfTrueValuesIn(digitsFound);
	}

	private static int numberOfTrueValuesIn(boolean[] digit) {
		int count = 0;
		for (boolean occurs : digit) {
			if (occurs) {
				count++;
			}
		}
		return count;
	}
}
