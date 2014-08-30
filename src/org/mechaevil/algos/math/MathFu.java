package org.mechaevil.algos.math;

import java.math.BigInteger;

public class MathFu {

	public static double hornerEval(double[] a, double x) {
		return hornerEval(a, x, 0);
	}

	// helper function
	private static double hornerEval(double[] a, double x, int index) {
		if (index == a.length - 1)
			return a[index];
		else {
			return hornerEval(a, x, index + 1) * x + a[index];
		}
	}

	public static double hornerEval2(double[] a, double x) {
		double r = 0;
		for (int i = a.length - 1; i >= 0; i--)
			r = r * x + a[i];
		return r;
	}

	public static long power(long p, int n) {
		long result = 1;
		while (n > 0) {

			if ((n & 1) == 1) {
				result *= p;
				n -= 1;
			}
			p *= p;
			n /= 2; /* integer division, rounds down */
		}
		return result;
	}

	public static int powMod(int a, int b, int c) {
		long _a = a % c, r = 1;
		while (b > 0) {
			if ((b & 1) == 1)
				r = (r * _a) % c;
			_a = (_a * _a) % c;
			b >>>= 1;
		}
		return (int) r;
	}

	public static int gcd(int a, int b) {
		if (b < 0)
			b = -b;
		if (a < 0)
			a = -a;
		while (b > 0) {
			int c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	public static long gcd(long a, long b) {
		if (b < 0)
			b = -b;
		if (a < 0)
			a = -a;
		while (b > 0) {
			long c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	public static int lcm(int a, int b) {
		return (a * b) / gcd(a, b);
	}

	public static long lcm(long a, long b) {
		return (a * b) / gcd(a, b);
	}

	public static long mulMod(long a, long b, long mod) {
		return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b))
				.mod(BigInteger.valueOf(mod)).longValue();
	}

	public static int mulMod(int a, int b, int mod) {
		return (int) (((long) a * b) % mod);
	}

	public static int addMod(int a, int b, int mod) {
		return (int) (((long) a + b) % mod);
	}

	public static long addMod(long a, long b, long mod) {
		a %= mod;
		b %= mod;
		return (a > Long.MAX_VALUE - b) ? a - (mod - b) : (a + b) % mod;
	}

	public static boolean isPerfectSquare(int N) {
		int h = (int) (N & 0xF); // h is the last hex "digit"
		if (h > 9)
			return false;
		// Use lazy evaluation to jump out of the if statement as soon as
		// possible
		if (h != 2 && h != 3 && h != 5 && h != 6 && h != 7 && h != 8) {
			long t = (long) Math.floor(Math.sqrt((double) N) + 0.5);
			return t * t == N;
		}
		return false;
	}

	public static boolean isPerfectSquare(long N) {
		int h = (int) (N & 0xF); // h is the last hex "digit"
		if (h > 9)
			return false;
		// Use lazy evaluation to jump out of the if statement as soon as
		// possible
		if (h != 2 && h != 3 && h != 5 && h != 6 && h != 7 && h != 8) {
			long t = (long) Math.floor(Math.sqrt((double) N) + 0.5);
			return t * t == N;
		}
		return false;
	}

	public long fibMod(long n, long mod) {
		long[][] mtx = new long[][] { { 1, 1 }, { 1, 0 } };
		long[][] res = new long[][] { { 1, 0 }, { 0, 1 } };
		long a, b, c, d, e, f, g, h;
		while (n > 0) {
			a = mtx[0][0];
			b = mtx[0][1];
			c = mtx[1][0];
			d = mtx[1][1];
			if (n % 2 == 1) {
				// res *= mtx;
				e = res[0][0];
				f = res[0][1];
				g = res[1][0];
				h = res[1][1];
				res[0][0] = ((a * e) % mod + (b * g) % mod) % mod;
				res[0][1] = ((a * f) % mod + (b * h) % mod) % mod;
				res[1][0] = ((c * e) % mod + (c * g) % mod) % mod;
				res[1][1] = ((c * f) % mod + (d * h) % mod) % mod;
			}
			n >>= 1;
			// mtx = mtx*mtx
			mtx[0][0] = ((a * a) % mod + (b * c) % mod) % mod;
			mtx[0][1] = ((a * b) % mod + (b * d) % mod) % mod;
			mtx[1][0] = ((a * c) % mod + (c * d) % mod) % mod;
			mtx[1][1] = ((b * c) % mod + (d * d) % mod) % mod;
		}
		return res[0][1] % mod;
	}

	public long countDigitsUpto(int N, int digit) {
		int n = N;
		long c = 0;
		long pow = 1;
		while (n > 0) {
			int numberBeforeDivideByTen = n / 10;
			int numberAfterDivideByTen = n % 10;
			if (digit != 0) {
				c += numberBeforeDivideByTen * pow;
			} else {
				c += (numberBeforeDivideByTen - 1) * pow;
			}

			if (numberAfterDivideByTen > digit) {
				c += pow;
			} else if (numberAfterDivideByTen == digit) {
				c += N % pow + 1;
			}
			n = n / 10;
			pow = pow * 10;
		}
		return c;
	}

	public static long choose(int n, int r) {
		if (n < 0 || r < 0 || r > n)
			return 0;
		r = Math.min(r, n - r);
		long c = 1;
		for (int k = 1; k <= r; k++) {
			int g = gcd(n - r + k, k);
			c /= (k / g);
			c *= ((n - r + k) / g);// (n - (r - k));
		}
		return c;
	}

	public static long choose(int n,int k,long mod){
		if(k > n || k < 0) return 0;
		k = Math.min(k,n-k);
		BigInteger c = BigInteger.ONE;
		for(int i = 1; i <=k;i++){
			c = c.multiply(BigInteger.valueOf(n - (k - i)));
			c = c.divide(BigInteger.valueOf(i));
		}
		return c.mod(BigInteger.valueOf(mod)).longValue();
	}

	public static boolean isPowerOfTwo(long n) {
		return (n > 0) && ((n & (n - 1)) == 0);

	}

	public static long[][] binomialCoefficients(int n) {
		long[][] result = new long[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			result[i][0] = 1;
			for (int j = 1; j <= i; j++)
				result[i][j] = result[i - 1][j - 1] + result[i - 1][j];
		}
		return result;
	}

	public static long[][] generateBinomialCoefficients(int n, long module) {
		long[][] result = new long[n + 1][n + 1];
		if (module == 1)
			return result;
		for (int i = 0; i <= n; i++) {
			result[i][0] = 1;
			for (int j = 1; j <= i; j++) {
				result[i][j] = result[i - 1][j - 1] + result[i - 1][j];
				if (result[i][j] >= module)
					result[i][j] -= module;
			}
		}
		return result;
	}

	public static long factorialLength(long n) {
		double f = 0.5 * Math.log10(2 * Math.PI * n) + n
				* Math.log10(n / Math.E);
		return (long) f + 1;
	}

	static private long[] factorials = { 1L /* 0 */, 1L /* 1 */, 2L /* 2 */,
			6L /* 3 */, 24L /* 4 */, 120L /* 5 */, 720L /* 6 */, 5040L /* 7 */,
			40320L /* 8 */, 362880L /* 9 */, 3628800L /* 10 */,
			39916800L /* 11 */, 479001600L /* 12 */, 6227020800L /* 13 */,
			87178291200L /* 14 */, 1307674368000L /* 15 */,
			20922789888000L /* 16 */, 355687428096000L /* 17 */,
			6402373705728000L /* 18 */, 121645100408832000L /* 19 */,
			2432902008176640000L /* 20 */
	};

	public static long factorial(int n) {
		if (!(0 <= n && n <= 20)) {
			throw new IllegalArgumentException(
					"factorial can only handle 0 <= n <= 20");
		}
		return factorials[n];
	}

	public static BigInteger bigFact(BigInteger n) {
		BigInteger p = new BigInteger("1");

		while (n.compareTo(BigInteger.ONE) > 0) {
			p = p.multiply(n);
			n = n.subtract(BigInteger.ONE);
		}
		return p;
	}

	public static int powerInFactorial(int N, int f) {
		int s = 0;
		while (N >= f)
			s += N /= f;
		return s;
	}

	public static int[] xgcd(int a, int b) {
		int x = 0, lastx = 1;
		int y = 1, lasty = 0;
		while (b != 0) {
			int quotient = a / b;

			int temp = b;
			b = a % b;
			a = temp;

			temp = x;
			x = lastx - quotient * x;
			lastx = temp;

			temp = y;
			y = lasty - quotient * y;
			lasty = temp;
		}
		return new int[] { a, lastx, lasty };
	}

	public static long[] xgcd(long a, long b) {
		long x = 0, lastx = 1;
		long y = 1, lasty = 0;
		while (b != 0) {
			long quotient = a / b;

			long temp = b;
			b = a % b;
			a = temp;

			temp = x;
			x = lastx - quotient * x;
			lastx = temp;

			temp = y;
			y = lasty - quotient * y;
			lasty = temp;
		}
		return new long[] { a, lastx, lasty };
	}

	public static boolean isHappyNumber(int N) {
		int n = Math.abs(N);
		while (n > 0 && n != 1 && n != 4) {
			int s = 0;
			while (n > 0) {
				s += (n % 10) * (n % 10);
				n /= 10;
			}
			n = s;
		}
		return n == 1;
	}

	public static boolean isHappyNumber(long N) {
		long n = Math.abs(N);
		while (n > 0 && n != 1 && n != 4) {
			int s = 0;
			while (n > 0) {
				s += (n % 10) * (n % 10);
				n /= 10;
			}
			n = s;
		}
		return n == 1;
	}

	public static boolean isPoliteNumber(int N) {
		return N > 0 && Integer.bitCount(N) != 1;
	}

	public static boolean isPoliteNumber(long N) {
		return N > 0 && Long.bitCount(N) != 1;
	}

	public static int politeNumber(int n) {
		double log2n = Math.log(n) / Math.log(2);
		return n + (int) (Math.log(n + log2n) / Math.log(2));
	}

	public static boolean isHammingNumber(int N) {
		int n = Math.abs(N);
		for (int i : new int[] { 2, 3, 5 })
			while (n > 0 && n % i == 0)
				n /= i;
		return n == 1;
	}

	public static long[] hammingNumbers(int N) {
		int i2, i3, i5;
		i2 = i3 = i5 = 0;
		long[] h = new long[N];
		h[0] = 1;
		long m2, m3, m5;
		m2 = h[i2] * 2;
		m3 = h[i3] * 3;
		m5 = h[i5] * 5;
		for (int i = 1; i < N; i++) {
			h[i] = Math.min(m2, Math.min(m3, m5));
			if (h[i] == m2) {
				i2++;
				m2 = h[i2] * 2;
			}
			if (h[i] == m3) {
				i3++;
				m3 = h[i3] * 3;
			}
			if (h[i] == m5) {
				i5++;
				m5 = h[i5] * 5;
			}
		}
		return h;
	}

	public static void main(String[] args) {
		int N = 100;
		long[][] bc = binomialCoefficients(N);
	}
}
