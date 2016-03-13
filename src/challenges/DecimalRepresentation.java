package challenges;

import java.text.DecimalFormat;
import java.util.Stack;

public class DecimalRepresentation {

	public static void main(String[] args) {
		System.out.println(decimalRepresent(-123456));
	}

	private static String decimalFormatter(int number) {
		final String COMMA_SEPARATED = "###,###.##";
		DecimalFormat formatter = new DecimalFormat(COMMA_SEPARATED);
		return formatter.format(number);
	}

	private static String decimalRepresent(int no) {
		String noStr = Integer.toString(no);
		char[] charArr = noStr.toCharArray();
		StringBuffer sbr = new StringBuffer();
		Stack<Character> stack = new Stack<Character>();
		int pos = 0;
		for (int i=charArr.length-1; i>=0; i--) {
			if (pos == 3 && charArr[i] != '-') {
				stack.push(',');
				stack.push(charArr[i]);
				pos = 1;
			} else if (charArr[i] != '-'){
				pos++;
				stack.push(charArr[i]);
			} else {
				stack.push(charArr[i]);
			}
		}
		while(!stack.isEmpty()) {
			sbr.append(stack.pop());
		}
		return sbr.toString();
	}

}
