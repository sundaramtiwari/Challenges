package challenges;

public class AOL1 {

	public static void main(String[] args) {
		System.out.println(solution(0,23045));
	}

	private static int solution(int a, int b) {
		String num1 = String.valueOf(a);
		String num2 = String.valueOf(b);
		return num2.indexOf(num1);
	}

}
