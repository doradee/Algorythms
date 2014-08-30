package org.mechaevil.algos.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.mechaevil.algos.graphs.edgelist.AdjMatrix;
import org.mechaevil.algos.graphs.edgelist.Edge;
import org.mechaevil.algos.graphs.edgelist.EdgeList;

public class GenericGraph<V> extends Graph {

	private EdgeList edgeList;
	private Index index;

	private class Index {
		Map<V, Integer> map = new HashMap<V, Integer>();
		List<V> list = new ArrayList<V>();

		public int indexOf(V value) {
			Integer id = map.get(value);
			if (id == null) {
				map.put(value, id = map.size());
				list.add(value);
			}
			return id;
		}

		public V at(int index) {
			return list.get(index);
		}
	}

	public GenericGraph(EdgeList edgeList) {
		super(edgeList);
		this.edgeList = edgeList;
		this.index = new Index();
	}

	public void addEdge(V u, V v) {
		edgeList.addEdge(index.indexOf(u), index.indexOf(v));
	}

	public void addEdge(V u, V v, int w) {
		edgeList.addEdge(index.indexOf(u), index.indexOf(v), w);
	}

	public static void main(String[] args) {
		int v = 26, e = 10;
		GenericGraph<String> g = new GenericGraph<String>(new AdjMatrix(v));
		Random r = new Random();
		for (int i = 0; i < e; i++) {
			String a = String.format("%c", r.nextInt(26) + 'A'), b = String
					.format("%c", r.nextInt(26) + 'A');
			g.addEdge(a, b);
		}
		for (int i = 0; i < v; i++) {
			for (Edge edge : g.edgeList.edges(i))
				System.out.println(g.genericEdge(edge));
		}
	}

	public V vertex(int u) {
		return index.at(u);
	}

	public String genericEdge(Edge e) {
		return String.format("%s-%s = %d", index.at(e.u()), index.at(e.v()),
				e.w());
	}
}
