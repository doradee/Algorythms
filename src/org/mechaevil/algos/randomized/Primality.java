package org.mechaevil.algos.randomized;

import java.util.Random;

import org.mechaevil.algos.math.MathFu;

public class Primality {

	private static Random r = new Random();

	public static boolean isPrime(int n) {
		if (n <= 3 || n % 2 == 0 || n % 3 == 0)
			return n == 2 || n == 3;
		int sqrt = (int) Math.sqrt(n);
		for (int i = 5; i <= sqrt; i += 6)
			if (n % i == 0 || n % (i + 2) == 0)
				return false;
		return true;
	}

	public static boolean fermatsTest(int n) {
		return fermatsTest(n, 10);
	}

	public static boolean fermatsTest(int n, int certainity) {
		if (n <= 2 || n % 2 == 0)
			return n == 2;
		while (certainity-- > 0) {
			int a = 1 + r.nextInt(n - 2), m = n - 1, exp = 1;
			while (m > 0) {
				if ((m & 1) == 1) {
					exp = MathFu.mulMod(exp, a, n);
				}
				a = MathFu.mulMod(a, a, n);
				m >>= 1;
			}
			if (exp != 1)
				return false;
		}
		return true;
	}

	public static boolean millerRabin(int number) {
		return number > 1
				&& (number == 2 || millerRabinPass(2, number)
						&& (number <= 7 || millerRabinPass(7, number))
						&& (number <= 61 || millerRabinPass(61, number)));
	}

	private static boolean millerRabinPass(final int a, final int n) {
		int d = n - 1, s = 0, pow_a;
		for (; d % 2 == 0; d >>= 1)
			s++;
		pow_a = MathFu.powMod(a, d, n);
		if (pow_a == 1)
			return true;
		for (int r = 0; r < s; r++) {
			if (pow_a == n - 1)
				return true;
			pow_a = MathFu.powMod(pow_a, 2, n);
		}
		return a == n - 1;
	}

	public static void main(String[] args) {
		int N = 10000000;
		for (int i = 0; i < N; i++) {
			boolean millerRabin = millerRabin(i);
			boolean fermatsTest = fermatsTest(i);
			boolean prime = isPrime(i);
			if (millerRabin != fermatsTest || fermatsTest != prime
					|| millerRabin != prime)
				System.out.println(String.format(
						"%d millerRabin(%s) fermatTest(%s) trailDivision(%s)",
						i, millerRabin, fermatsTest, prime));
		}
		// trail division
		int c = 0;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			if (isPrime(i))
				c++;
		}
		System.out.println(String.format(
				"isPrime[0,%d) - Found %d primes - Time Elapsed %d", N, c,
				System.currentTimeMillis() - startTime));
		// millerRabin
		c = 0;
		startTime = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			if (millerRabin(i))
				c++;
		}
		System.out.println(String.format(
				"millerRabin[0,%d) - Found %d primes - Time Elapsed %d", N, c,
				System.currentTimeMillis() - startTime));
		// fermatTest
		c = 0;
		startTime = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			if (fermatsTest(i))
				c++;
		}
		System.out.println(String.format(
				"fermatTest[0,%d) - Found %d primes - Time Elapsed %d", N, c,
				System.currentTimeMillis() - startTime));

	}
}
