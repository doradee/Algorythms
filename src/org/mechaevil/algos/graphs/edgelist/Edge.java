package org.mechaevil.algos.graphs.edgelist;

public class Edge implements Comparable<Edge> {

	private int u, v, w;

	public Edge(int u, int v, int w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}

	public int other(int u) {
		return this.u == u ? v : u;
	}

	public int u() {
		return u;
	}

	public int v() {
		return v;
	}

	public int w() {
		return w;
	}

	@Override
	public String toString() {
		return String.format("[%d-%d=%d]", u, v, w);
	}

	@Override
	public int compareTo(Edge o) {
		return w < o.w ? -1 : 1;
	}

}
