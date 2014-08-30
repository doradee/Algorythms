package org.mechaevil.algos.utils;

import java.util.Arrays;

import org.mechaevil.algos.debug.Debug;

public class Sorts {

	private static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	public static void bubbleSort(int[] arr) {
		for (int l = arr.length, i = l - 1; i >= 0; i--)
			for (int j = 0; j < i; j++)
				if (arr[i] < arr[j]) {
					swap(arr, i, j);
				}
	}

	public static void selectionSort(int[] arr) {
		for (int i = 0, l = arr.length; i < l; i++) {
			int minIndex = i;
			for (int j = i + 1; j < l; j++)
				if (arr[minIndex] > arr[j])
					minIndex = j;
			if (minIndex != i)
				swap(arr, minIndex, i);
		}
	}

	public static void binaryInsertionSort(int[] arr) {
		for (int i = 1, l = arr.length; i < l; i++) {
			// insert arr[i] into arr[0..i)
			int h = arr[i];
			int pos = Arrays.binarySearch(arr, 0, i, h);
			if (pos < 0)
				pos = ~pos;
			for (int j = i; j > pos; j--)
				arr[j] = arr[j - 1];
			arr[pos] = h;
		}
	}

	public static void insertionSort(int[] arr) {
		for (int i = 1, l = arr.length; i < l; i++) {
			// insert arr[i] into arr[0..i)
			int h = arr[i];
			int j = i;
			for (; j > 0 && h < arr[j - 1]; j--)
				arr[j] = arr[j - 1];
			arr[j] = h;
		}
	}

	public static void shellSort(int[] arr) {
		for (int l = arr.length, gap = l / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < l; i++) {
				int temp = arr[i], j;
				for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
					arr[j] = arr[j - gap];
				arr[j] = temp;
			}
		}
	}

	private static boolean sorted(int[] arr) {
		for (int i = 0, l = arr.length; i < l - 1; i++)
			if (arr[i] > arr[i + 1])
				return false;
		return true;
	}

	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length);
	}

	public static void quickSort(int[] arr, int lb, int ub) {
		if (lb + 1 < ub) {
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
			if (lb < right)
				quickSort(arr, lb, right);
			if (left < ub)
				quickSort(arr, left, ub);
		}
	}

	public static void mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length, new int[arr.length]);
	}

	private static void mergeSort(int[] arr, int lb, int ub, int[] aux) {
		if (lb + 1 < ub) {
			int mid = lb + (ub - lb) / 2;
			if (lb < mid)
				mergeSort(arr, lb, mid, aux);
			if (mid < ub)
				mergeSort(arr, mid, ub, aux);
			int i = lb, j = mid, k = 0;
			while (i < mid && j < ub) {
				aux[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
			}
			while (i < mid)
				aux[k++] = arr[i++];
			while (j < ub)
				aux[k++] = arr[j++];
			for (i = lb, k = 0; i < ub; i++, k++)
				arr[i] = aux[k];
		}
	}

	public static void radixSort(int[] arr) {
		int l = arr.length;
		for (int i = 0; i < l; i++)
			assert arr[i] >= 0 : "Negative Numbers not supported. Yet.";
		int[][] buckets = new int[10][l];
		int[] index = new int[10];
		for (int p = 1; p > 0; p *= 10) {
			for (int i = 0; i < l; i++) {
				int d = (arr[i] / p) % 10;
				buckets[d][index[d]++] = arr[i];
			}
			for (int i = 0, k = 0; i < 10; i++)
				for (int j = 0, m = index[i]; j < m; j++)
					arr[k++] = buckets[i][j];
			Arrays.fill(index, 0);
		}
	}

	public static void main(String[] args) {
		long totalTime = 0;
		int tn = 10000;
		for (int t = 0; t < tn; t++) {
			int[] arr = Debug.randomArray(1000);
			long startTime = System.currentTimeMillis();
			mergeSort(arr);
			totalTime += System.currentTimeMillis() - startTime;
			assert sorted(arr) : Arrays.toString(arr);
		}
		System.out.println(totalTime);
	}

}
