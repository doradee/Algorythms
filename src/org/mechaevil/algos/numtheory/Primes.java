package org.mechaevil.algos.numtheory;

import java.util.Arrays;

public class Primes {

	public static boolean isPrime(final int n) {
		if (n <= 3 || n % 2 == 0 || n % 3 == 0)
			return n == 2 || n == 3;
		int sqrt = (int) Math.sqrt(n);
		for (int i = 5; i <= sqrt; i += 6)
			if (n % i == 0 || n % (i + 2) == 0)
				return false;
		return true;
	}

	public static boolean isPrimeWheel(final int n) {

		final int product = 30;
		if (n % 2 == 0 || n % 3 == 0 || n % 5 == 0 || n <= 5)
			return n == 2 || n == 3 || n == 5;

		int sqrt = (int) Math.sqrt(n);
		for (int p = 0; p + 7 <= sqrt; p += product)
			if (n % (p + 7) == 0 || n % (p + 11) == 0 || n % (p + 13) == 0
					|| n % (p + 17) == 0 || n % (p + 19) == 0
					|| n % (p + 23) == 0 || n % (p + 29) == 0
					|| n % (p + 31) == 0)
				return false;
		return true;
	}

	public static boolean[] sieveCheckPrimes(final int N) {
		if (N < 3)
			return new boolean[] { true };
		boolean[] primes = new boolean[N / 2];
		Arrays.fill(primes, true);
		primes[0] = false;
		int sqrt = (int) Math.sqrt(N);
		for (int i = 3; i <= sqrt; i += 2)
			if (primes[i >> 1])
				for (int j = i * i; j < N; j += 2 * i)
					primes[j >> 1] = false;
		return primes;
	}

	public static int[] sievePrimes(final int N) {
		if (N < 2)
			return null;
		boolean[] checkPrime = sieveCheckPrimes(N);
		int primeCount = 1;
		for (int i = 3; i < N; i += 2)
			if (checkPrime[i >> 1])
				primeCount++;
		int[] primes = new int[primeCount];
		primes[0] = 2;
		int k = 1;
		for (int i = 3; i < N; i += 2)
			if (checkPrime[i >> 1])
				primes[k++] = i;
		return primes;
	}

	public static void main(String[] args) {
		// for (int prime : sievePrimes(10000000))
		// System.out.println(prime);
		long start = System.currentTimeMillis();
		int N = 5000000;
		int c = 0;
		for (int i = 1; i < N; i += 2)
			if (isPrime(i))
				c++;
		System.out.println(c);
		System.out.println(System.currentTimeMillis() - start);
		System.out.println();
		start = System.currentTimeMillis();
		c = 0;
		for (int i = 1; i < N; i += 2)
			if (isPrimeWheel(i))
				c++;
		System.out.println(c);

		System.out.println(System.currentTimeMillis() - start);
	}
}
