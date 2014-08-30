package org.mechaevil.algos.strings;

public class Strings {

	public static int hammingDistance(String a, String b) {
		int al, bl;
		int[][] d = new int[al = a.length() + 1][bl = b.length() + 1];
		for (int i = 0; i < al; i++) {
			for (int j = 0; j < bl; j++) {
				d[i][j] = i == 0 ? j : j == 0 ? i : j;
				if (i > 0 && j > 0) {
					if (a.charAt(i - 1) == b.charAt(j - 1))
						d[i][j] = d[i - 1][j - 1];
					else
						d[i][j] = Math.min(d[i - 1][j - 1],
								Math.min(d[i - 1][j], d[i][j - 1])) + 1;
				}
			}
		}
		return d[al - 1][bl - 1];
	}

	public static String nextLexographicWord(String word) {
		char[] ch = word.toCharArray();
		int i = ch.length - 1;
		while (i >= 0) {
			char c = ch[i];
			switch (ch[i]) {
			case 'z':
				c = 'a';
				break;
			case 'Z':
				c = 'A';
				break;
			case '9':
				c = '0';
				break;
			}

			if (c == ch[i]) {
				ch[i]++;
				break;
			} else {
				ch[i] = c;
			}
			i--;
		}

		return i < 0 ? null : new String(ch);
	}

	public static void main(String[] args) {
		String w = "000A";
		while (w != null) {
			System.out.println(w);
			w = nextLexographicWord(w);
		}

	}
}
