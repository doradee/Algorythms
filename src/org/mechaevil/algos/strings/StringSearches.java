package org.mechaevil.algos.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringSearches {

	public static int naiveSearch(String text, String substring) {
		assert text != null && substring != null;
		return naiveSearch(text.toCharArray(), substring.toCharArray(), 0);
	}

	public static int naiveSearch(char[] hay, char[] needle, int start) {
		assert hay != null && needle != null;
		int l = hay.length, nl = needle.length, j, max = l - nl + 1;
		if (nl == 0)
			return start;
		char first = needle[0];
		for (int i = start; i < max; i++) {

			while (i < max && hay[i] != first)
				i++;

			if (i < max) {
				for (j = 1; j < nl; j++)
					if (hay[i + j] != needle[j])
						break;
				if (j == nl)
					return i;
			}
		}
		return -1;
	}

	final static int rk_prime = 65521, rk_radix_shift = 7;

	public static int rabinKarp(String text, String substring) {
		assert text != null && substring != null;
		return rabinKarp(text.toCharArray(), substring.toCharArray(), 0);
	}

	public static int rabinKarp(char[] hay, char[] needle, int start) {
		assert hay != null && needle != null;
		int l = hay.length, nl = needle.length;
		if (start + nl < l) {
			int dm = 1;
			for (int i = 0; i < nl - 1; i++)
				dm = (dm << rk_radix_shift) % rk_prime;

			int max = l - nl;
			int hayHash = rabinKarpHash(hay, start, nl);
			int needleHash = rabinKarpHash(needle, 0, nl);
			for (int i = start; i < max; i++) {
				if (hayHash == needleHash) {
					// match ?
					int j = 0;
					while (j < nl && hay[i + j] == needle[j])
						j++;
					if (j == nl)
						return i;
				} else {
					// remove left char from hash
					hayHash = hayHash - dm * hay[i];
					// add next letter
					hayHash = ((hayHash << rk_radix_shift) + hay[i + nl])
							% rk_prime;
				}
			}
		}
		return -1;

	}

	private static int rabinKarpHash(char[] arr, int start, int len) {
		int h = 0;
		while (start < len) {
			h = (((h << rk_radix_shift) % rk_prime) + arr[start++]) % rk_prime;
		}
		return h;
	}

	public static void main(String[] args) {
		test();
		// System.out.println(rabinKarp("iamgaurav", "gau"));
	}

	private static void test() {
		Random r = new Random();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10000; i++)
			sb.append(r.nextInt());
		String text = sb.toString();

		int T = 1000;
		List<String> list = new ArrayList<String>(T);
		for (int i = 0; i < T; i++) {
			list.add(String.valueOf(r.nextInt()));
		}
		long start = System.currentTimeMillis();
		for (String s : list)
			rabinKarp(text, s);
		System.out.println(System.currentTimeMillis() - start);

		start = System.currentTimeMillis();
		for (String s : list)
			text.indexOf(s);
		System.out.println(System.currentTimeMillis() - start);
		for (int i = 0; i < T; i++) {
			String s = String.valueOf(r.nextInt(100));
			assert rabinKarp(text, s) == text.indexOf(s);
		}
	}
}
