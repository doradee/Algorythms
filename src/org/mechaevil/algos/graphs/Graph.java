package org.mechaevil.algos.graphs;

import org.mechaevil.algos.graphs.edgelist.EdgeList;

public class Graph {
	private EdgeList edgeList;

	public Graph(EdgeList edgeList) {
		this.edgeList = edgeList;
	}

	public EdgeList getEdgeList() {
		return edgeList;
	}

	@Override
	public String toString() {
		return edgeList.toString();
	}

}
