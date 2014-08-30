package org.mechaevil.algos.search;

import java.util.Arrays;
import java.util.Random;

import org.mechaevil.algos.debug.Debug;

public class SelectionAlgorithms {

	public static int[] minMax(int[] arr) { // using divide and conquer method.
		int[] mm = new int[2];
		if (arr == null)
			throw new NullPointerException();
		int len = arr.length;
		if (len == 0)
			throw new IllegalArgumentException("Array is empty.");
		if (len == 1) {
			return mm;
		}
		long v = minMax(arr, 0, len);
		mm[0] = (int) (v >> 32);
		mm[1] = (int) (v & 0xFFFFFFFF);
		return mm;
	}

	private static long minMax(int[] arr, int lo, int hi) { // helper for
															// minMax(int[])
		if (lo < hi) {
			if (hi - lo == 1) {
				return (long) lo << 32 | lo;
			} else {
				int mid = (lo + hi) / 2;
				long mm1 = minMax(arr, lo, mid);
				long mm2 = minMax(arr, mid, hi);
				long mm = 0;
				if (arr[(int) (mm1 >> 32)] < arr[(int) (mm2 >> 32)])
					mm = (mm1 >> 32) << 32;
				else
					mm = (mm2 >> 32) << 32;
				if (arr[(int) (mm1 & 0xFFFFFFFFL)] < arr[(int) (mm2 & 0xFFFFFFFFL)])
					mm |= (mm2 & 0xFFFFFFFFL);
				else
					mm |= (mm1 & 0xFFFFFFFFL);
				return mm;
			}
		}
		return 0;
	}

	private static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	/**
	 * @param arr
	 * @param k
	 * @return kth smallest element at arr[k]
	 */
	public static int quickSelect(int[] arr, int k) {
		if (k < 0 || k >= arr.length)
			throw new IllegalArgumentException("k not in range.");
		return quickSelect(arr, 0, arr.length, k);
	}

	private static int quickSelect(int[] arr, int lb, int ub, int k) {
		while (lb < ub) {
			int left = lb + 1, right = ub - 1;
			int pivot = arr[lb];
			do {
				while (left < ub && pivot >= arr[left])
					left++;
				while (right >= lb && pivot < arr[right])
					right--;
				if (left < right)
					swap(arr, left, right);
			} while (left < right);
			swap(arr, lb, right);
			if (k < right) {
				ub = right;
			} else {
				lb = left;
			}
		}
		return arr[k];
	}

	public static void main(String[] args) {
		Random r = new Random();
		int[] randomArray = Debug.randomArray(100000);
		int k = r.nextInt(randomArray.length / 2) + randomArray.length / 2;
		int kth = quickSelect(randomArray, k);
		System.out.println(kth);
		Arrays.sort(randomArray);
		System.out.println(randomArray[k]);
	}
}
