package org.mechaevil.algos.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import org.mechaevil.algos.graphs.edgelist.AdjList;
import org.mechaevil.algos.graphs.edgelist.Edge;

public class TopologicalSort {

	public int[] topologicalSort(Graph g) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int V = g.getEdgeList().getVertexCount();
		int usedVertices = g.getEdgeList().getUsedVertices();
		int[] indegree = new int[V];
		for (int u = 0; u < V; u++) {
			if (g.getEdgeList().indegree(u) == 0
					&& g.getEdgeList().outdegree(u) > 0) {
				queue.addLast(u);
			}
			indegree[u] = g.getEdgeList().indegree(u);
		}
		boolean[] visited = new boolean[V];
		int[] order = new int[usedVertices];
		int size = 0;
		while (!queue.isEmpty()) {
			int u = queue.pop();

			if (visited[u])
				return null;
			visited[u] = true;
			for (Edge e : g.getEdgeList().edges(u)) {
				int v = e.other(u);
				if (indegree[v] > 0)
					indegree[v]--;
				if (indegree[v] == 0) {
					queue.addLast(v);
				}
			}
			order[size++] = u;
		}
		return (size == usedVertices) ? order : null;
	}

	public static void main(String[] args) {
		String testCase = "A B\r\n" + "B C\r\n" + "C D\r\n" + "D A";
		Scanner scan = new Scanner(testCase);
		int V = 12;
		GenericGraph<String> g = new GenericGraph<String>(new AdjList(V));
		while (scan.hasNext()) {
			g.addEdge(scan.next(), scan.next());
		}
		System.out.println(g);
		int[] order = new TopologicalSort().topologicalSort(g);
		System.out.println(Arrays.toString(order));
		if (order != null)
			for (int u : order)
				System.out.println(g.vertex(u));
	}
}
