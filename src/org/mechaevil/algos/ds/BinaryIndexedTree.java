package org.mechaevil.algos.ds;

import java.util.Arrays;

import org.mechaevil.algos.debug.Debug;

public class BinaryIndexedTree { // Also Called Fenwick Trees

	final int[] x;
	final int size;

	public BinaryIndexedTree(int size) {
		this.x = new int[size + 1];
		this.size = size;
	}

	public void add(int index, int value) {
		if (index > 0)
			while (index <= size) {
				x[index] += value;
				index += (index & -index);
			}
	}

	public int at(int index) {
		int s = x[index];
		if (index > 0) {
			int next = index - (index & -index);
			index--;
			while (index != next) {
				s += x[index];
				index -= (index & -index);
			}
		}
		return s;
	}

	public int sum(int index) {
		int s = 0;
		while (index > 0) {
			s += x[index];
			index -= (index & -index);
		}
		return s;
	}

	public int sum(int from, int to) {
		return sum(to) - sum(from - 1);
	}

	@Override
	public String toString() {
		return Arrays.toString(x);
	}

	public static void main(String[] args) {
		final int N = 10;
		int[] arr = Debug.randomArray(N, 10);
		arr[0] = 0;
		BinaryIndexedTree bit = new BinaryIndexedTree(N);
		for (int i = 1; i < N; i++) {
			bit.add(i, arr[i]);
		}
		System.out.println(Arrays.toString(arr));
		System.out.println(bit);
		System.out.println(bit.sum(3, 5));
		System.out.println(bit.at(3));
	}

}
