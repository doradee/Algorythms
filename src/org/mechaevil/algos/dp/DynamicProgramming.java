package org.mechaevil.algos.dp;

import java.util.Arrays;

public class DynamicProgramming {

	public static boolean subsetSumProblem(int[] set, int sum) {
		boolean[] v = new boolean[sum + 1];
		v[0] = true;
		for (int value : set)
			for (int i = sum; i >= value; i--)
				if (v[i - value])
					v[i] = true;
		return v[sum];
	}

	public static boolean coinChangeProblem(int[] coins, int amt) {
		boolean[] v = new boolean[amt + 1];
		v[0] = true;
		for (int coin : coins)
			for (int i = 0, l = amt - coin; i <= l; i++)
				if (v[i])
					v[i + coin] = true;
		return v[amt];
	}

	public static void allSubsetSumProblem(int[] set, int sum) {
		assert set.length < 32;
		boolean[] v = new boolean[sum + 1];
		int[] index = new int[sum + 1];
		v[0] = true;
		for (int c = 0, l = set.length; c < l; c++) {
			int value = set[c];
			for (int i = sum; i >= value; i--)
				if (v[i - value]) {
					v[i] = true;
					index[i] |= 1 << c;
				}
		}
		processSolutions(set, index, sum, new int[set.length], 0, 0);
	}

	private static void processSolutions(int[] set, int[] index, int i,
			int[] solution, int j, int mask) {
		if (i == 0) {
			System.out.println(Arrays.toString(Arrays.copyOf(solution, j)));
		}
		for (int k = 0; k < set.length; k++) {
			if (((mask & (1 << k)) == 0) && ((1 << k) & index[i]) > 0) {
				solution[j] = set[k];
				processSolutions(set, index, i - set[k], solution, j + 1, mask
						| (1 << k));
			}
		}
	}

	public static String longestPalindrome(String text) {
		int l = text.length();
		boolean[][] palindrome = new boolean[l][l];
		int startIndex = 0, maxLen = 0;
		for (int i = 0; i < l; i++) {
			palindrome[i][i] = true;
			if (i + 1 < l) {
				palindrome[i][i + 1] = text.charAt(i) == text.charAt(i + 1);
				startIndex = i;
				maxLen = 2;
			}
		}
		for (int len = 3; len <= l; len++) {
			for (int i = 0; i + len - 1 < l; i++) {
				int j = i + len - 1;
				if (palindrome[i + 1][j - 1]
						&& text.charAt(i) == text.charAt(j)) {
					palindrome[i][j] = true;
					if (len > maxLen) {
						startIndex = i;
						maxLen = len;
					}
				}
			}
		}
		return text.substring(startIndex, startIndex + maxLen);
	}

	public static String bruteLongestPalindrome(String text) {
		int l = text.length(), startIndex = 0, maxLen = 0;
		for (int i = 0; i < l; i++)
			for (int j = i; j < l; j++) {
				String subs = text.substring(i, j);
				if (subs.equals(new StringBuilder(subs).reverse().toString())) {
					if (subs.length() > maxLen) {
						startIndex = i;
						maxLen = subs.length();
					}
				}
			}
		return text.substring(startIndex, startIndex + maxLen);
	}

	//
	// public static int makePalindrome(String text) {
	// int len = text.length();
	// if (len <= 1) {
	// return 0;
	// } else if (text.charAt(0) == text.charAt(len - 1)) {
	// return makePalindrome(text.substring(1, len - 1));
	// } else {
	// return 1 + Math.min(makePalindrome(text.substring(0, len - 1)),
	// makePalindrome(text.substring(1, len)));
	// }
	// }

	public static int makePalindromeDP(String text) {
		int len = text.length();
		int[][] min = new int[len][len];
		for (int i = 0; i < len; i++)
			for (int j = 0; j < len; j++)
				min[i][j] = i == j ? 0 : -1;
		return makePalindromeDP(text, min, 0, len - 1);
	}

	private static int makePalindromeDP(String text, int[][] min, int i, int j) {
		if (min[i][j] >= 0)
			return min[i][j];
		if (j - i < 1)
			return min[i][j] = 0;
		else if (text.charAt(i) == text.charAt(j)) {
			return min[i][j] = makePalindromeDP(text, min, i + 1, j - 1);
		} else {
			return min[i][j] = 1 + Math.min(
					makePalindromeDP(text, min, i + 1, j),
					makePalindromeDP(text, min, i, j - 1));
		}
	}

	public static String longestCommonSubsequence(String a, String b) {
		int m = a.length(), n = b.length();
		int[][] best = new int[m + 1][n + 1];
		int[][] way = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					best[i][j] = 1 + best[i - 1][j - 1];
				} else {
					best[i][j] = Math.max(best[i - 1][j], best[i][j - 1]);
					way[i][j] = best[i - 1][j] - best[i][j - 1];
				}
			}
		}
		return reconstructLCS(a, b, way);
	}

	private static String reconstructLCS(String a, String b, int[][] way) {
		int m = a.length(), n = b.length();
		StringBuilder sb = new StringBuilder();
		while (m > 0 && n > 0) {
			if (way[m][n] == 0) {
				m--;
				n--;
				sb.append(a.charAt(m));
			} else {
				if (way[m][n] < 0) {
					n--;
					sb.append(b.charAt(n));
				} else {
					m--;
					sb.append(a.charAt(m));
				}
			}
		}
		return sb.reverse().toString();
	}

	public static void main(String[] args) {
		int N = 5;
		String randomString1 = "abcd";// Debug.randomString(N);
		String randomString2 = "abec";// Debug.randomString(N);
		System.out.println(longestCommonSubsequence(randomString1,
				randomString2));
	}

}
