package org.mechaevil.algos.series;

public class GeometricProgression {
	private double a, r;

	public GeometricProgression(double a, double r) {
		this.a = a;
		this.r = r;
	}

	public double getNthTerm(int n) {
		return a * Math.pow(r, n - 1);
	}

	public double sum(int n) {
		return sum(0, n);
	}

	public double sum(int m, int n) {
		return a * (Math.pow(r, m) - Math.pow(r, n + 1)) / (1 - r);
	}

	public static void main(String[] args) {
		GeometricProgression gp = new GeometricProgression(1, -3);
		for (int i = 1; i < 10; i++)
			System.out.println(gp.getNthTerm(i));
	}

}
