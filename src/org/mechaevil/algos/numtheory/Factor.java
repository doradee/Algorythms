package org.mechaevil.algos.numtheory;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.mechaevil.algos.math.MathFu;

public class Factor {

	public static int smallestFactor(final int n) {
		if (n % 2 == 0)
			return 2;
		int sqrt = (int) Math.sqrt(n);
		for (int i = 3; i <= sqrt; i += 2)
			if (n % i == 0)
				return i;
		return n;
	}

	public static long rhoPollard(final long n) {
		if (n <= 1)
			return 1;
		int q = 3;
		long x = q, y = q, d, c = -1;
		do {
			x = ((x * x) % n + c) % n;
			y = ((y * y) % n + c) % n;
			y = ((y * y) % n + c) % n;
			d = MathFu.gcd(Math.abs(x - y), n);
			if (d == n)
				return 1;
		} while (d == 1);
		return d;
	}

	public static int ndiv(int n) {
		if (n <= 1)
			return 1;
		int c = 0, ndiv = 1;
		while (n % 2 == 0) {
			c++;
			n >>= 1;
		}
		if (c > 0)
			ndiv *= (c + 1);
		int prime = 3;
		while (n > 1) {
			c = 0;
			while (n % prime == 0) {
				n /= prime;
				c++;
			}
			if (c > 0)
				ndiv *= (c + 1);
			prime += 2;
		}
		return ndiv;
	}

	public static long sdiv(int n) {
		if (n <= 1)
			return 1;
		long pie = 1, sigma = 1, primepow;
		primepow = 2;
		while (n % 2 == 0) {
			sigma += primepow;
			n >>= 1;
			primepow *= 2;
		}
		pie *= sigma;
		int prime = 3;
		while (n > 1) {
			sigma = 1;
			primepow = prime;
			while (n % prime == 0) {
				n /= prime;
				sigma += primepow;
				primepow *= prime;
			}
			pie *= sigma;
			prime += 2;
		}
		return pie;
	}

	public static Set<Long> getFactors(long n) {
		Set<Long> factors = new TreeSet<Long>();
		long sqrt = (long) Math.sqrt(n);
		for (long i = 1; i <= sqrt; i++)
			if (n % i == 0) {
				factors.add(i);
				factors.add(n / i);
			}
		return factors;
	}

	public static Map<Long, Integer> getPrimeFactors(long n) {
		Map<Long, Integer> primeFactors = new TreeMap<Long, Integer>();
		if (n > 1) {
			int c = 0;
			while (n % 2 == 0) {
				c++;
				n >>= 1;
			}
			if (c > 0)
				primeFactors.put(2L, c);
			long prime = 3;
			while (n > 1) {
				c = 0;
				while (n % prime == 0) {
					n /= prime;
					c++;
				}
				if (c > 0)
					primeFactors.put(prime, c);
				prime += 2;
				if (prime > n / prime)
					break;
			}
			if (n > 1)
				primeFactors.put(n, 1);
		}
		return primeFactors;
	}

	public static void main(String[] args) {
		System.out.println(getPrimeFactors(259679));
	}

}
