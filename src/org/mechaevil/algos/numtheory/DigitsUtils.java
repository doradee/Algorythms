package org.mechaevil.algos.numtheory;

public class DigitsUtils {

	public static int sumDigits(int N) {
		int s = 0, n = Math.abs(N);
		while (n > 0) {
			s += n % 10;
			n /= 10;
		}
		return s;
	}

	public static int sumDigits(long N) {
		int s = 0;
		long n = Math.abs(N);
		while (n > 0) {
			s += n % 10;
			n /= 10;
		}
		return s;
	}

	public static int productDigits(int N) {
		int p = 1, n = Math.abs(N);
		while (n > 0) {
			p *= n % 10;
			n /= 10;
		}
		return p;
	}

	public static int productDigits(long N) {
		int p = 1;
		long n = Math.abs(N);
		while (n > 0) {
			p *= n % 10;
			n /= 10;
		}
		return p;
	}

	public static int countDigits(int N) {
		if (N == 0)
			return 1;
		int c = 0;
		int n = Math.abs(N);
		while (n > 0) {
			c++;
			n /= 10;
		}
		return c;
	}

	public static int countDigits(long N) {
		if (N == 0)
			return 1;
		int c = 0;
		long n = Math.abs(N);
		while (n > 0) {
			c++;
			n /= 10;
		}
		return c;
	}

	public static byte[] splitDigits(int N) {
		byte[] digits = new byte[countDigits(N)];
		int n = Math.abs(N), i = digits.length - 1;
		while (n > 0) {
			digits[i--] = (byte) (n % 10);
			n /= 10;
		}
		return digits;
	}

	public static byte[] splitDigits(long N) {
		byte[] digits = new byte[countDigits(N)];
		long n = Math.abs(N);
		int i = digits.length - 1;
		while (n > 0) {
			digits[i--] = (byte) (n % 10);
			n /= 10;
		}
		return digits;
	}

	public static int countDigit(int N, int digit) {
		int c = 0;
		int n = Math.abs(N);
		while (n > 0) {
			if (n % 10 == digit)
				c++;
			n /= 10;
		}
		return c;
	}

	public static int countDigit(long N, int digit) {
		int c = 0;
		long n = Math.abs(N);
		while (n > 0) {
			if (n % 10 == digit)
				c++;
			n /= 10;
		}
		return c;
	}

	public static long reverseDigits(int N) {
		long r = 0;
		int n = Math.abs(N);
		while (n > 0) {
			r = r * 10 + n % 10;
			n /= 10;
		}
		return r;
	}

	public static long reverseDigits(long N) throws NumberFormatException {
		return Long.parseLong(new StringBuilder(String.valueOf(Math.abs(N)))
				.reverse().toString());
	}

	public static int sortDigits(int N) {
		int[] f = new int[10];
		int n = Math.abs(N), s = 0;
		while (n > 0) {
			f[n % 10]++;
			n /= 10;
		}
		for (int i = 0; i < 10; i++)
			while (f[i]-- > 0)
				s = s * 10 + i;
		return s;
	}

	public static long sortDigits(long N) {
		int[] f = new int[10];
		long n = Math.abs(N), s = 0;
		while (n > 0) {
			f[(int) (n % 10)]++;
			n /= 10;
		}
		for (int i = 0; i < 10; i++)
			while (f[i]-- > 0)
				s = s * 10 + i;
		return s;
	}

	public static boolean isPalindrome(int N) {
		long n = Math.abs(N), r = 0;
		while (n > 0) {
			r = r * 10 + n % 10;
			n /= 10;
		}
		return r == Math.abs(N);
	}

	public static boolean isPalindrome(long N) {
		String n = String.valueOf(Math.abs(N));
		String reverse = new StringBuilder(n).reverse().toString();
		return reverse.equals(n);
	}

	public static boolean isPermutation(int a, int b) {
		if (a == b)
			return true;
		byte[] digits = new byte[10];
		for (a = Math.abs(a); a > 0; digits[a % 10]++, a /= 10)
			;
		for (b = Math.abs(b); b > 0; digits[b % 10]--, b /= 10)
			;
		for (byte d : digits)
			if (d != 0)
				return false;
		return true;
	}

	public static boolean isPermutation(long a, long b) {
		if (a == b)
			return true;
		byte[] digits = new byte[10];
		for (a = Math.abs(a); a > 0; digits[(int) (a % 10)]++, a /= 10)
			;
		for (b = Math.abs(b); b > 0; digits[(int) (b % 10)]--, b /= 10)
			;
		for (byte d : digits)
			if (d != 0)
				return false;
		return true;
	}

	public static boolean isPandigital0(int N, int d) {
		int c = 0;
		int n = Math.abs(N);
		while (n > 0) {
			int _ = n % 10;
			if ((c & (1 << _)) > 0)
				return false;
			c |= (1 << _);
			n /= 10;
		}
		return c == (1 << (d + 1)) - 2;
	}

	public static boolean isPandigital(int N, int d) {
		int c = 0;
		int n = Math.abs(N);
		while (n > 0) {
			int _ = n % 10;
			if ((c & (1 << _)) > 0)
				return false;
			c |= (1 << _);
			n /= 10;
		}
		return c == (1 << (d + 1)) - 1;
	}

	public static void main(String[] args) {

		for (int i = 0; i < 500; i++) {
			System.out.println(i);
		}
	}

}
