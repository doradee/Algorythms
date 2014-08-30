package org.mechaevil.algos.encoder;

import java.io.ByteArrayOutputStream;

public class Base64 {

	private static final char[] ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
			.toCharArray();

	private static final int[] index;

	static {
		index = new int[256];
		int i = 0;
		for (char c : ALPHABETS)
			index[c] = i++;
	}

	public static String encode(byte[] data) {
		StringBuilder sb = new StringBuilder();
		int l = data.length;
		for (int i = 0; i < l; i += 3) {
			int t = data[i] << 16;
			if (i + 1 < l)
				t |= data[i + 1] << 8;
			if (i + 2 < l)
				t |= data[i + 2];

			sb.append(ALPHABETS[(int) ((t >> 18) & 0x3F)]);
			sb.append(ALPHABETS[(int) ((t >> 12) & 0x3F)]);
			if (i + 1 < l)
				sb.append(ALPHABETS[(int) ((t >> 6) & 0x3F)]);
			if (i + 2 < l)
				sb.append(ALPHABETS[(int) ((t) & 0x3F)]);
		}
		switch (l % 3) {
		case 1:
			sb.append('=');
		case 2:
			sb.append('=');
		default:
			break;
		}
		return sb.toString();
	}

	public static byte[] decode(String data) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				(int) (data.length() * 1.3));
		char[] charArray = data.toCharArray();
		for (int i = 0; i < charArray.length; i += 4) {
		}
		return baos.toByteArray();
	}

	public static void main(String[] args) {

	}
}
