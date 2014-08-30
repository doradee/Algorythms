package org.mechaevil.algos.misc;

import java.util.Arrays;

public class SudokuSolver {

	private SudokuSolver() {
	}

	public static boolean solve(int[][] mtx) {
		int l = mtx.length;
		int x = -1, y = -1;
		for (int i = 0; x < 0 && y < 0 && i < l; i++) {
			for (int j = 0; j < l; j++) {
				if (mtx[i][j] == 0) {
					x = i;
					y = j;
					break;
				}
			}
		}
		if (x < 0 && y < 0)
			return true;
		boolean[] digits = new boolean[l + 1];
		int sq = (int) Math.sqrt(l);
		for (int i = 0; i < l; i++) {
			digits[mtx[i][y]] = digits[mtx[x][i]] = true;
			if (sq * sq == l) {
				digits[mtx[(x / sq) * sq + (i / sq)][(y / sq) * sq + (i % sq)]] = true;

			}
		}
		for (int i = 9; i > 0; i--) {
			if (!digits[i]) {
				mtx[x][y] = i;
				if (solve(mtx))
					return true;
				mtx[x][y] = 0;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] mtx = new int[9][9];
		solve(mtx);
		for (int[] row : mtx)
			System.out.println(Arrays.toString(row));

	}
}
