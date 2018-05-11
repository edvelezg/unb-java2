package math.algorithms;

public class IsAPower {

	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	public static void main(String[] args) {
		System.out.println(gcd(150, 25));
		System.out.println(isPower(9));
	}

	static boolean isPower(int a) {
		int factor = 2;
		int e = 0;
		while (a != 1) {
			int number = 0;
			while (a % factor == 0) {
				number++;
				a = a / factor;
			}
			if (e == 0) {
				e = number;
			} else {
				e = gcd(number, e);
			}
			if (e == 1)
				return false;
			factor++;
		}

		return true;

	}
}
