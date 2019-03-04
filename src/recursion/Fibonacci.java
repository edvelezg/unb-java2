package recursion;

public class Fibonacci {
	int[] array;
	int n;
	int count;

	public Fibonacci(int n) {
		array = new int[n];
		this.n = n;
		count = 0;
	}

	public int rawCalc() {
		return rawFib(n);
	}
	public int calc() {
		return fib(n);
	}


	private int fib(int n) {
		if (n == 0 || n == 1) {
			return n;
		}
		if (array[n - 1] != 0) {
			return array[n - 1];
		} else {
			array[n - 1] = fib(n - 1) + fib(n - 2);
			return array[n - 1];
		}
	}

	private int rawFib(int n) {
		if (n == 0 || n == 1) {
			return n;
		} else {
			return rawFib(n - 1) + rawFib(n - 2);
		}
	}

	public static void pCount(int n) {
		Fibonacci f = new Fibonacci(n);
		int calc = f.rawCalc();
		int rawCount = f.count;
		f.count = 0;
		calc = f.calc();
		int count2 = f.count;
		System.out.println(String.format("%d\t%d\t%d", calc, count2, rawCount));
	} 

	public static void main(String[] args) {
		pCount(10);
		pCount(7);
		pCount(6);
		pCount(5);
		pCount(4);
		pCount(3);
		pCount(2);
		pCount(1);
		pCount(0);

	}
}
