package org.mechaevil.algos.ds;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

public class SuffixArray {

	private final char[] text;
	private final int len;
	private final Integer[] index;

	public SuffixArray(String s) {
		if (s == null)
			throw new NullPointerException("Parameter cannot be null.");
		this.text = s.toCharArray();
		this.len = s.length();
		this.index = new Integer[text.length];
		prepSuffixArray();
	}

	private void prepSuffixArray() {
		for (int i = 0; i < len; i++)
			index[i] = i;
		Arrays.sort(index, new SuffixComparator()); // O(nlogn)
	}

	private class SuffixComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer i, Integer j) {
			while (i < len && j < len && text[i] == text[j]) {
				i++;
				j++;
			}
			if (i < len && j < len)
				return text[i] - text[j];
			return i < len ? 1 : -1;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			if (sb.length() > 0)
				sb.append('\n');
			sb.append(index[i]).append(':')
					.append(new String(text, index[i], len - index[i]));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		SuffixArray sa = new SuffixArray("ABRACADABRA");
		System.out.println(sa);
		System.out.println(BigInteger.valueOf(100).divide(new BigInteger("3")));
	}

}
