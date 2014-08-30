package org.mechaevil.algos.search;

import java.util.Arrays;

public class NQueens {

	public interface SolutionCallback {
		public boolean accept(int solutionCount, int[] board);
	}

	public static class SolutionPrinter implements SolutionCallback {

		@Override
		public boolean accept(int solutionCount, int[] board) {
			System.out.println(solutionCount);
			System.out.println(Arrays.toString(board));
			return solutionCount < 10;
		}
	}

	private int N;
	private int[] board;
	private boolean[] used;
	private int solutionCount;
	private SolutionCallback callback;

	public NQueens(int N, SolutionCallback callback) {
		this.board = new int[this.N = N];
		this.used = new boolean[N];
		this.callback = callback;
	}

	public void findSolutions() {
		Arrays.fill(used, false); // reset
		solutionCount = 0; // reset
		findSolutions(0);
	}

	private boolean findSolutions(int col) {
		if (col == N) {
			return callback.accept(++solutionCount, board);
		}
		boolean ret = true;
		for (int row = 0; ret && row < N; row++)
			if (!used[row]) {
				if (canPlace(row, col)) {
					board[col] = row;
					used[row] = true;
					if (ret)
						ret = findSolutions(col + 1);
					used[row] = false;
				}
			}
		return ret;
	}

	private boolean canPlace(int row, int col) {
		for (int c = col - 1; c >= 0; c--) {
			int r = board[c];
			if (Math.abs(row - r) == Math.abs(col - c))
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		char[] row = new char[N];
		for (int c = 0; c < N; c++) {
			Arrays.fill(row, '_');
			row[board[c]] = 'Q';
			sb.append(Arrays.toString(row)).append('\n');
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		new NQueens(25, new NQueens.SolutionPrinter()).findSolutions();
	}

}
