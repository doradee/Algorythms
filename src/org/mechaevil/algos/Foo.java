package org.mechaevil.algos;

import java.util.Arrays;

import org.mechaevil.algos.debug.Debug;

public class Foo {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int[] arr = Debug.randomArray(5, 1, 10);
		System.out.println(Arrays.toString(arr));
		System.out.println(minSwaps(arr));

		System.out.println(System.currentTimeMillis() - start);
	}

	private static int minSwaps(int[] arr) {
		int len;
		int[] sortedArray = Arrays.copyOf(arr, len = arr.length);
		int[] indices = new int[len];
		Arrays.sort(sortedArray);
		System.out.println(Arrays.toString(sortedArray));
		for (int i = 0; i < len; i++)
			indices[i] = Arrays.binarySearch(sortedArray, arr[i]);
		System.out.println(Arrays.toString(indices));
		int c = 0;
		for (int i = 0; i < len; i++) {
			if (indices[i] < 0 || indices[i] == i)
				continue;
			StringBuilder sb = new StringBuilder();
			sb.append(arr[i]).append(',');
			for (int j = indices[i]; j != i; c++) {
				sb.append(arr[j]).append(',');
				int hold = j;
				j = indices[j];
				indices[hold] = -1;
			}
			indices[i] = i;
			System.out.println(sb.toString());
		}
		System.out.println(Arrays.toString(indices));
		return c;
	}
}
