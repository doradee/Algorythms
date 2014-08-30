package org.mechaevil.algos.ds;

import java.util.Arrays;
import java.util.Random;

public class RangeQuery {

	private final int[] x, sum;
	private final int k;

	public RangeQuery(int size) {
		this.x = new int[size];
		this.k = (int) Math.sqrt(size);
		this.sum = new int[size / k + 1];
	}

	public void update(int index, int value) {
		sum[index / k] += value - x[index];
		x[index] = value;
	}

	public int query(int lo, int hi) {
		int s = 0;
		int index = lo;
		while (index % k != 0 && index <= hi) {
			s += x[index++];
		}
		while (index + k <= hi) {
			s += sum[index / k];
			index += k;
		}
		while (index <= hi) {
			s += x[index++];
		}
		return s;
	}

	public int brute(int lo, int hi) {
		int s = 0;
		for (int i = lo; i <= hi; i++)
			s += x[i];
		return s;
	}

	public static void main(String[] args) {
		Random r = new Random();
		final int N = 1000;
		RangeQuery rq = new RangeQuery(N);
		for (int i = 0; i < N; i++) {
			rq.update(i, r.nextInt());
		}

		System.out.println(rq);
		for (int i = 0; i < 1000; i++) {
			int a = r.nextInt(N), b = r.nextInt(N);
			int mn = Math.min(a, b), mx = Math.max(a, b);
			int s = rq.query(mn, mx);
			int s2 = rq.brute(mn, mx);
			if (s != s2) {
				System.out.println("Sums of " + mn + "," + mx + " : " + s + ","
						+ s2);
			}
		}
	}

	@Override
	public String toString() {
		return Arrays.toString(x);
	}
}
