package org.mechaevil.algos.graphs.edgelist;

import java.util.ArrayList;
import java.util.List;

public abstract class EdgeList {

	private int vertexCount;

	public EdgeList(int vertexCount) {
		this.vertexCount = vertexCount;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public abstract void addEdge(int u, int v);

	public abstract void addEdge(int u, int v, int w);

	public abstract Iterable<Edge> edges(int u);

	public abstract int indegree(int u);

	public abstract int outdegree(int u);

	public int getUsedVertices() {
		return vertexCount;
	}

	public List<Edge> getEdges() {
		List<Edge> ret = new ArrayList<Edge>();
		for (int u = 0; u < vertexCount; u++)
			for (Edge e : edges(u))
				ret.add(e);
		return ret;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int u = 0, V = getVertexCount(); u < V; u++) {
			if (u > 0)
				sb.append(',');
			sb.append(u).append('=').append('{');
			for (Edge edge : edges(u)) {
				sb.append(edge).append(',');
			}
			sb.append('}');
		}
		return sb.toString();
	}

}
