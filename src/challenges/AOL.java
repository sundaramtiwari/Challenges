package challenges;

public class AOL {

	public static void main(String[] args) {
		System.out.println(solution(1234,1112));

	}

	private static int solution(int A, int B) {
		String num1 = String.valueOf(A);
		String num2 = String.valueOf(B);
		char[] num1Arr = num1.toCharArray();
		char[] num2Arr = num2.toCharArray();

		StringBuffer sbf = new StringBuffer();
		boolean flag = false;
		int index = 0;
		for (int i = 0; i < num1Arr.length; index = i) {
			if (i < num2Arr.length) {
				if (!flag) {
					sbf.append(num1Arr[i]);
					flag = true;
				} else {
					sbf.append(num2Arr[i]);
					flag = false;
					i++;
				}
			} else {
				sbf.append(num1Arr[i]);
				i++;
			}
		}
		if (num2Arr.length > num1Arr.length) {
			for (int j = index; j < num2Arr.length; j++) {
				sbf.append(num2Arr[j]);
			}
		}
		int result = Integer.parseInt(sbf.toString());
		return result < 100000000 ? result : -1	;
	}
}
