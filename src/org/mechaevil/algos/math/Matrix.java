package org.mechaevil.algos.math;

import java.util.Random;

public class Matrix {

	private final int nRows, nColumns;
	private final long[][] values;

	public static Matrix identityMatrix(int N) {
		Matrix m = new Matrix(N, N);
		for (int i = 0; i < N; i++)
			m.values[i][i] = 1;
		return m;
	}

	public Matrix(int N) {
		this(N, N);
	}

	public Matrix(int nRows, int nColumns) {
		this.nRows = nRows;
		this.nColumns = nColumns;
		this.values = new long[nRows][nColumns];
	}

	public void set(int i, int j, long value) {
		if (i < 0 || i >= nRows || j < 0 || j >= nColumns)
			throw new IllegalArgumentException("Invalid indices.");
		values[i][j] = value;
	}

	public long get(int i, int j) {
		rangeCheck(i, j);
		return values[i][j];
	}

	public Matrix transpose() {
		Matrix t = new Matrix(nColumns, nRows);
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nColumns; j++) {
				t.values[j][i] = values[i][j];
			}
		}
		return t;

	}

	private void rangeCheck(int i, int j) {
		if (i < 0 || i >= nRows || j < 0 || j >= nColumns)
			throw new IllegalArgumentException("Invalid indices.");
	}

	public Matrix add(Matrix other) {
		if (!compatible(other)) {
			throw new IllegalArgumentException("Order of Matrix do not match.");
		}
		Matrix sum = new Matrix(nRows, nColumns);
		for (int i = 0; i < nRows; i++)
			for (int j = 0; j < nColumns; j++)
				sum.values[i][j] = values[i][j] + other.values[i][j];
		return sum;
	}

	public Matrix subtract(Matrix other) {
		if (!compatible(other)) {
			throw new IllegalArgumentException("Order of Matrix do not match.");
		}
		Matrix diff = new Matrix(nRows, nColumns);
		for (int i = 0; i < nRows; i++)
			for (int j = 0; j < nColumns; j++)
				diff.values[i][j] = values[i][j] - other.values[i][j];
		return diff;
	}

	public Matrix multiply(int scale) {
		Matrix m = new Matrix(nRows, nColumns);
		for (int i = 0; i < nRows; i++)
			for (int j = 0; j < nColumns; j++)
				m.values[i][j] = values[i][j] * scale;
		return m;
	}

	public Matrix multiply(Matrix other) {
		if (!canMultiply(other)) {
			throw new IllegalArgumentException("Cannot multiple matrices.");
		}
		Matrix m = new Matrix(nRows, other.nColumns);
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < other.nColumns; j++) {
				for (int k = 0; k < nColumns; k++) {
					m.values[i][j] += values[i][k] * other.values[k][j];
				}
			}
		}
		return m;
	}

	private boolean canMultiply(Matrix other) {
		return nColumns == other.nRows;
	}

	private boolean compatible(Matrix other) {
		return nRows == other.nRows && nColumns == other.nColumns;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nColumns; j++) {
				if (j > 0)
					sb.append(',');
				sb.append(values[i][j]);
			}
			sb.append('\n');
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Random r = new Random();
		int N = 2;
		Matrix m2 = new Matrix(N, N);
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				m2.set(i, j, r.nextInt(10));
			}
		Matrix m3 = new Matrix(N, N);
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				m3.set(i, j, r.nextInt(10));
			}

		System.out.println(m2);
		System.out.println(m3);
		System.out.println(m2.multiply(m3));
	}
}
