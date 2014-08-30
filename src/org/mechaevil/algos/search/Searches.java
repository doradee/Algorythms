package org.mechaevil.algos.search;

import java.util.Arrays;
import java.util.Random;

import org.mechaevil.algos.debug.Debug;

public class Searches {

	public static int linearSearch(int[] arr, int key) {
		return linearSearch(arr, 0, arr.length, key);
	}

	public static int linearSearch(int[] arr, int lb, int key) {
		return linearSearch(arr, lb, arr.length, key);
	}

	public static int linearSearch(int[] arr, int lb, int ub, int key) {
		while (lb < ub) {
			if (key == arr[lb])
				return lb;
			lb++;
		}
		return -1;
	}

	public static int binarySearch(int[] arr, int key) {
		return binarySearch(arr, 0, arr.length, key);
	}

	public static int binarySearch(int[] arr, int lb, int key) {
		return binarySearch(arr, lb, arr.length, key);
	}

	public static int binarySearch(int[] arr, int lb, int ub, int key) {
		int lo = lb, hi = ub;
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if (arr[mid] < key)
				lo = mid + 1;
			else
				hi = mid;
		}
		return lo < ub && arr[lo] == key ? lo : ~lo;
	}

	public static int interpolationSearch(int[] arr, int key) {
		return interpolationSearch(arr, 0, arr.length, key);
	}

	public static int interpolationSearch(int[] arr, int lb, int ub, int key) {
		while (lb < ub) {
			if (key < arr[lb])
				return ~lb;
			if (key > arr[ub - 1])
				return ~ub;
			double normalize = (key - arr[lb])
					/ (1.0 * (arr[ub - 1] - arr[lb]));
			int guess = (int) (lb + normalize * (ub - lb - 1));
			if (arr[guess] == key)
				return guess;
			else if (arr[guess] < key)
				lb = guess + 1;
			else
				ub = guess;
		}
		return ~lb;
	}

	public static void main(String[] args) {
		Random r = new Random();
		int[] arr = Debug.randomArray(100, 1000);
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		for (int i = 0; i < arr.length; i++) {
			int rand = r.nextInt(1000);
			int pos = interpolationSearch(arr, rand);
			System.out.println(rand + "," + pos);
		}
	}
}
