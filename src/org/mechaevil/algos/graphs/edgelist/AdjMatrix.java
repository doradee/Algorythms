package org.mechaevil.algos.graphs.edgelist;

import java.util.Iterator;
import java.util.Random;

public class AdjMatrix extends EdgeList {

	private int[][] wt;
	private int[] indegree, outdegree;

	public AdjMatrix(int vertexCount) {
		super(vertexCount);
		wt = new int[vertexCount][vertexCount];
		indegree = new int[vertexCount];
		outdegree = new int[vertexCount];
	}

	@Override
	public void addEdge(int u, int v) {
		wt[u][v] = 1;
		indegree[v]++;
		outdegree[u]++;
	}

	@Override
	public void addEdge(int u, int v, int w) {
		wt[u][v] = w;
		indegree[v]++;
		outdegree[u]++;
	}

	@Override
	public Iterable<Edge> edges(int u) {
		return new EdgeIterator(u);
	}

	private class EdgeIterator implements Iterator<Edge>, Iterable<Edge> {

		private int u, index, V;

		public EdgeIterator(int u) {
			this.u = u;
			this.index = 0;
			this.V = getVertexCount();
		}

		@Override
		public boolean hasNext() {
			while (index < V && wt[u][index] == 0)
				index++;
			return index < V;
		}

		@Override
		public Edge next() {
			return new Edge(u, index, wt[u][index++]);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Iterator<Edge> iterator() {
			return this;
		}
	}

	public static void main(String[] args) {
		int v = 10, e = 10;
		AdjMatrix edgeList = new AdjMatrix(v);
		Random r = new Random();
		for (int i = 0; i < e; i++) {
			edgeList.addEdge(r.nextInt(v), r.nextInt(v));
		}
		for (int i = 0; i < v; i++) {
			for (Edge edge : edgeList.edges(i))
				System.out.println(edge);
		}
		System.out.println(edgeList);
	}

	@Override
	public int indegree(int u) {
		return indegree[u];
	}

	@Override
	public int outdegree(int u) {
		return outdegree[u];
	}

	@Override
	public int getUsedVertices() {
		int c = 0;
		for (int u = 0, V = getVertexCount(); u < V; u++)
			if (indegree[u] > 0 || outdegree[u] > 0)
				c++;
		return c;
	}
}
