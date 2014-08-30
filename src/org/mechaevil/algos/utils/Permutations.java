package org.mechaevil.algos.utils;

import java.util.Arrays;

public class Permutations {

	public static boolean nextPermutation(int[] x) {
		int N = x.length - 1;
		int keyIndex = N, temp;
		while (keyIndex > 0 && x[keyIndex - 1] >= x[keyIndex])
			keyIndex--;
		keyIndex--;
		if (keyIndex < 0)
			return false;
		int pivotIndex = N;
		while (x[pivotIndex] <= x[keyIndex])
			pivotIndex--;
		temp = x[keyIndex];
		x[keyIndex] = x[pivotIndex];
		x[pivotIndex] = temp;
		for (keyIndex++, pivotIndex = N; keyIndex < pivotIndex; keyIndex++, pivotIndex--) {
			temp = x[keyIndex];
			x[keyIndex] = x[pivotIndex];
			x[pivotIndex] = temp;
		}
		return true;
	}

	public static boolean nextPermutation(char[] x) {
		int N = x.length - 1;
		int keyIndex = N;
		char temp;
		while (keyIndex > 0 && x[keyIndex - 1] >= x[keyIndex])
			keyIndex--;
		keyIndex--;
		if (keyIndex < 0)
			return false;
		int pivotIndex = N;
		while (x[pivotIndex] <= x[keyIndex])
			pivotIndex--;
		temp = x[keyIndex];
		x[keyIndex] = x[pivotIndex];
		x[pivotIndex] = temp;
		for (keyIndex++, pivotIndex = N; keyIndex < pivotIndex; keyIndex++, pivotIndex--) {
			temp = x[keyIndex];
			x[keyIndex] = x[pivotIndex];
			x[pivotIndex] = temp;
		}
		return true;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		do {
			System.out.println(Arrays.toString(arr));
		} while (nextPermutation(arr));
	}
}
