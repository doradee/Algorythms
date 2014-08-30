package org.mechaevil.algos.ds;

import java.util.Arrays;
import java.util.Random;

public class UnionFind {

	final private int[] parent;
	int count;

	public UnionFind(int size) {
		this.parent = new int[size];
		this.count = size;
		Arrays.fill(parent, -1);
	}

	public void union(int i, int j) {
		int pi = find(i), pj = find(j);
		if (pi != pj) { // already connected
			if (parent[pi] < parent[pj]) {
				parent[pi] += parent[pj];
				parent[pj] = pi;
			} else {
				parent[pj] += parent[pi];
				parent[pi] = pj;
			}
			count--;
		}
	}

	public int find(int i) {
		int finalParent = i;
		while (parent[finalParent] >= 0)
			finalParent = parent[finalParent];
		while (parent[i] >= 0) {
			int p = parent[i];
			parent[i] = finalParent;
			i = p;
		}
		return finalParent;
	}

	public boolean connected(int i, int j) {
		return find(i) == find(j);
	}

	public int size() {
		return count;
	}

	public int[][] disjointSets() {
		int[][] ds = new int[count][];
		for (int i = 0, l = parent.length, k = 0; i < l; i++) {
			if (parent[i] < 0)
				ds[k++] = setContaining(i);
		}
		return ds;
	}

	public int[] setContaining(int i) {
		int pi = find(i);
		int[] set = new int[-parent[pi]];
		for (int j = 0, k = 0, l = parent.length; j < l; j++)
			if (parent[j] == pi || j == pi)
				set[k++] = j;
		return set;
	}

	public int getSetSize(int i) {
		return -parent[find(i)];
	}

	@Override
	public String toString() {
		return Arrays.toString(parent);
	}

	public static void main(String[] args) {
		final int N = 10;
		UnionFind uf = new UnionFind(N);
		Random r = new Random();
		for (int i = 0; i < N; i++) {
			int a = r.nextInt(N), b = r.nextInt(N);
			uf.union(a, b);
			System.out.println(String.format("Unite %d and %d : %d", a, b,
					uf.size()));
			System.out.println(String.format("Parent %d : %d", a, uf.find(a)));
			System.out.println(String.format("Parent %d : %d", b, uf.find(b)));
			System.out.println();
		}

		for (int i = 0; i < N; i++) {
			System.out.println(String.format("%d : %s", i,
					Arrays.toString(uf.setContaining(i))));
		}

		System.out.println(uf.size());
		System.out.println(uf);
		int[][] ds = uf.disjointSets();
		for (int[] s : ds)
			System.out.println(Arrays.toString(s));
		System.out.println(uf.getSetSize(4));
	}

}
