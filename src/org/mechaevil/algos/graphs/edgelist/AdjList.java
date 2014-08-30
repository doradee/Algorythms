package org.mechaevil.algos.graphs.edgelist;

import java.util.Iterator;
import java.util.Random;

public class AdjList extends EdgeList {

	private class Node extends Edge {
		private Node next;

		public Node(int u, int v, int w, Node next) {
			super(u, v, w);
			this.next = next;
		}
	}

	private Node[] head;
	private int[] indegree, outdegree;

	public AdjList(int vertexCount) {
		super(vertexCount);
		head = new Node[vertexCount];
		indegree = new int[vertexCount];
		outdegree = new int[vertexCount];
	}

	@Override
	public void addEdge(int u, int v) {
		head[u] = new Node(u, v, 1, head[u]);
		indegree[v]++;
		outdegree[u]++;
	}

	@Override
	public void addEdge(int u, int v, int w) {
		head[u] = new Node(u, v, w, head[u]);
		indegree[v]++;
		outdegree[u]++;
	}

	@Override
	public Iterable<Edge> edges(int u) {
		return new EdgeIterator(u);
	}

	private class EdgeIterator implements Iterator<Edge>, Iterable<Edge> {

		private Node node;

		public EdgeIterator(int u) {
			this.node = head[u];
		}

		@Override
		public boolean hasNext() {
			return node != null;
		}

		@Override
		public Edge next() {
			Node hold = node;
			node = node.next;
			return hold;
		}

		@Override
		public void remove() {

		}

		@Override
		public Iterator<Edge> iterator() {
			return this;
		}
	}

	public static void main(String[] args) {
		int v = 10, e = 10;
		AdjList edgeList = new AdjList(v);
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
