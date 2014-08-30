package org.mechaevil.algos.ds;

import java.util.Arrays;
import java.util.Random;

import org.mechaevil.algos.debug.Debug;

public class BinaryIndexedTree2D {

	private final int[][] tree;
	private final int maxx, maxy;

	public BinaryIndexedTree2D(int maxx, int maxy) {
		this.tree = new int[maxx][maxy];
		this.maxx = maxx;
		this.maxy = maxy;
	}

	public void add(int x, int y, int value) {
		if (x > 0)
			while (x <= maxx) {
				int y1 = y;
				if (y > 0)
					while (y1 <= maxy) {
						tree[x][y] += value;
						y1 += (y1 & -y1);
					}
				x += (x & -x);
			}
	}

	public int at(int x, int y) {
		return sum(x, y) - sum(x - 1, y - 1);
	}

	public int sum(int x, int y) {
		int s = 0;
		while (x > 0) {
			int y1 = y;
			while (y1 > 0) {
				s += tree[x][y1];
				y1 -= (y1 & -y1);
			}
			x -= (x & -x);
		}
		return s;
	}

	public int sum(int x1, int y1, int x2, int y2) {
		return sum(x2, y2) - sum(x1 - 1, y1 - 1);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int[] row : tree)
			sb.append(Arrays.toString(row)).append('\n');
		return sb.toString();
	}

	public static void main(String[] args) {
		final int N = 5;
		BinaryIndexedTree2D bit2d = new BinaryIndexedTree2D(N, N);
		int[][] arr = new int[N][N];
		Random r = new Random();
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				arr[i][j] = r.nextInt(2);
				arr[i][j] = i == 0 || j == 0 ? 0 : arr[i][j];
				bit2d.add(i, j, arr[i][j]);
			}
		System.out.println(Debug.toString(arr));
		System.out.println();
		System.out.println(bit2d);
		System.out.println(bit2d.sum(2, 1));
	}
}
