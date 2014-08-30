package org.mechaevil.algos.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class Fraction extends Number implements Comparable<Fraction> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Fraction ONE = new Fraction(1, 1);
	public static Fraction ZERO = new Fraction(0, 1);
	private final long p, q;
	private final double cache;

	public static Fraction parseFraction(String value) {
		StringTokenizer st = new StringTokenizer(value, "//");
		return new Fraction(Long.parseLong(st.nextToken()), Long.parseLong(st
				.nextToken()));
	}

	public Fraction(long p, long q) {
		if (q < 0) {
			p = -p;
			q = -q;
		}
		long gcd = gcd(p, q);
		if (gcd > 1) {
			this.p = p / gcd;
			this.q = q / gcd;
		} else {
			this.p = p;
			this.q = q;
		}
		this.cache = this.p / (double) this.q;
	}

	public Fraction() {
		this(0, 1);
	}

	public Fraction(long p) {
		this(p, 1);
	}

	public long numerator() {
		return p;
	}

	public long denominator() {
		return q;
	}

	public Fraction add(Fraction b) {
		return new Fraction(p * b.q + q * b.p, q * b.q);
	}

	public Fraction subtract(Fraction b) {
		return new Fraction(p * b.q - q * b.p, q * b.q);
	}

	public Fraction multiply(Fraction b) {
		return new Fraction(p * b.p, q * b.q);
	}

	public Fraction divide(Fraction b) {
		return new Fraction(p * b.q, q * b.p);
	}

	public Fraction add(long b) {
		return new Fraction(p + b * q, q);
	}

	public Fraction subtract(long b) {
		return new Fraction(p - b * q, q);
	}

	public Fraction multiply(long b) {
		return new Fraction(p * b, q);
	}

	public Fraction divide(long b) {
		return new Fraction(p, q * b);
	}

	public Fraction power(int exponent) {
		return new Fraction(MathFu.power(p, exponent),
				MathFu.power(q, exponent));
	}

	public Fraction abs() {
		return new Fraction(Math.abs(p), Math.abs(q));
	}

	public Fraction negate() {
		return new Fraction(-p, q);
	}

	private long gcd(long a, long b) {
		if (a < 0)
			a = -a;
		if (b < 0)
			b = -b;
		while (b != 0) {
			long c = a % b;
			a = b;
			b = c;
		}
		return a;
	}

	@Override
	public String toString() {
		if (q == 0) {
			if (p > 0)
				return "Infinity";
			if (p < 0)
				return "-Infinity";
			return "NaN";
		}
		if (q == 1)
			return String.valueOf(p);
		return String.format("%d/%d", p, q);
	}

	@Override
	public int intValue() {
		return (int) (p / q);
	}

	@Override
	public long longValue() {
		return p / q;
	}

	@Override
	public float floatValue() {
		return p / (float) q;
	}

	@Override
	public double doubleValue() {
		return cache;
	}

	@Override
	public int compareTo(Fraction o) {
		return Double.compare(cache, o.cache);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (p ^ (p >>> 32));
		result = prime * result + (int) (q ^ (q >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fraction other = (Fraction) obj;
		if (p != other.p)
			return false;
		if (q != other.q)
			return false;
		return true;
	}

	public static void main(String[] args) {
		List<Fraction> f = new ArrayList<Fraction>();
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			f.add(new Fraction(r.nextLong(), r.nextLong()));
		}
		Collections.sort(f);
		for (Fraction _ : f)
			System.out.println(_.doubleValue());
		System.out.println(f);
	}

}
