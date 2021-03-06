package doItAlgorithm;

public class Factorial {

	static int factorialRecursive(int n) {
		if (n == 0)
			return 1;
		if (n == 1)
			return 1;
		return n * factorialRecursive(n - 1);
	}

	static int factorialRepeat(int n) {
		if (n == 0)
			return 1;
		if (n == 1)
			return 1;
		int res = 1;
		for (int i = 1; i <= n; i++) {
			res *= i;
		}
		return res;
	}
	
	

	public static void main(String args[]) {
		System.out.println(factorialRecursive(5));
		System.out.println(factorialRepeat(5));
				
	}
}
