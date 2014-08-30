package org.mechaevil.algos.graphs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.mechaevil.algos.graphs.edgelist.AdjList;
import org.mechaevil.algos.graphs.edgelist.Edge;

public class SingleSourceShortestPath {

	public class Result {
		private int path[];
		private int minCost, src, dst;

		public Result(int c) {
			path = new int[c];
		}

		public Result(int src, int dst) {
			path = null;
			this.src = src;
			this.dst = dst;
			minCost = Integer.MAX_VALUE;
		}

		@Override
		public String toString() {
			if (path == null)
				return String.format("No Path from %d to %d", src, dst);
			return String.format("%s = %s", Arrays.toString(path), minCost);
		}
	}

	private class DijkstraComparator implements Comparator<Integer> {

		private final int[] minCost;

		public DijkstraComparator(int[] minCost) {
			this.minCost = minCost;
		}

		@Override
		public int compare(Integer first, Integer second) {
			return minCost[first] < minCost[second] ? -1 : 1;
		}

	}

	public Result dijkstra(Graph g, int src, int dst) {
		// minCost[i] stores minimum distance from src to i
		int[] minCost = new int[g.getEdgeList().getVertexCount() + 1];
		// previous intermediate node that shortens the path from v to src
		int[] prev = new int[minCost.length];
		Arrays.fill(minCost, Integer.MAX_VALUE / 2); // High value
		Arrays.fill(prev, -1); // -1 is un-initialized
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(minCost.length,
				new DijkstraComparator(minCost));
		minCost[src] = 0;
		pq.add(src);
		while (!pq.isEmpty()) {
			int u = pq.poll();
			if (u == dst)
				break;
			for (Edge edge : g.getEdgeList().edges(u)) { // relax all edges
				int v = edge.other(u);
				if (minCost[v] > minCost[u] + edge.w()) {
					minCost[v] = minCost[u] + edge.w();
					prev[v] = u;
					pq.add(v);
				}
			}
		}
		if (prev[dst] < 0) {
			return new Result(src, dst);
		} else {
			int node = dst, c = 0;
			while (node >= 0) {
				node = prev[node];
				c++;
			}
			Result result = new Result(c);
			result.minCost = minCost[dst];
			node = dst;
			while (node >= 0) {
				result.path[--c] = node;
				node = prev[node];
			}
			return result;
		}
	}

	// Strictly for acyclic graphs
	public Result negativeWeighted(Graph g, int src, int dst) {
		// minCost[i] stores minimum distance from src to i
		int[] minCost = new int[g.getEdgeList().getVertexCount() + 1];
		// previous intermediate node that shortens the path from v to src
		int[] prev = new int[minCost.length];
		boolean[] inQ = new boolean[minCost.length];
		Arrays.fill(minCost, Integer.MAX_VALUE / 2); // High value
		Arrays.fill(prev, -1); // -1 is un-initialized
		LinkedList<Integer> q = new LinkedList<Integer>();
		minCost[src] = 0;
		q.addLast(src);
		inQ[src] = true;
		while (!q.isEmpty()) {
			int u = q.pop();
			if (u == dst)
				break;
			for (Edge edge : g.getEdgeList().edges(u)) { // relax all edges
				int v = edge.other(u);
				if (minCost[v] > minCost[u] + edge.w()) {
					minCost[v] = minCost[u] + edge.w();
					prev[v] = u;
					if (!inQ[v]) {
						q.addLast(v);
						inQ[v] = true;
					}
				}
			}
		}
		if (prev[dst] < 0) {
			return new Result(src, dst);
		} else {
			int node = dst, c = 0;
			while (node >= 0) {
				node = prev[node];
				c++;
			}
			Result result = new Result(c);
			result.minCost = minCost[dst];
			node = dst;
			while (node >= 0) {
				result.path[--c] = node;
				node = prev[node];
			}
			return result;
		}
	}

	public Result bellmanFord(Graph g, int src, int dst) {
		// minCost[i] stores minimum distance from src to i
		int[] minCost = new int[g.getEdgeList().getVertexCount() + 1];
		// previous intermediate node that shortens the path from v to src
		int[] prev = new int[minCost.length];
		Arrays.fill(minCost, Integer.MAX_VALUE / 2); // High value
		Arrays.fill(prev, -1); // -1 is un-initialized

		List<Edge> edges = g.getEdgeList().getEdges();

		minCost[src] = 0;
		for (int vc = 0, V = g.getEdgeList().getVertexCount(); vc < V; vc++) {
			for (Edge e : edges) {
				int u = e.u(), v = e.v();
				if (minCost[u] + e.w() < minCost[v]) {
					minCost[v] = minCost[u] + e.w();
					prev[v] = u;
				}
			}
		}
		for (Edge e : edges) {
			int u = e.u(), v = e.v();
			if (minCost[u] + e.w() < minCost[v]) {
				return null;
			}
		}

		if (prev[dst] < 0) {
			return new Result(src, dst);
		} else {
			int node = dst, c = 0;
			while (node >= 0) {
				node = prev[node];
				c++;
			}
			Result result = new Result(c);
			result.minCost = minCost[dst];
			node = dst;
			while (node >= 0) {
				result.path[--c] = node;
				node = prev[node];
			}
			return result;
		}
	}

	public static void main(String[] args) throws Exception {
		test();

	}

	private static void test() {
		String testCase = "3\r\n" + "3 2\r\n" + "1 2 5\r\n" + "2 3 7\r\n"
				+ "1 3\r\n" + "3 3\r\n" + "1 2 4\r\n" + "1 3 7\r\n"
				+ "2 3 1\r\n" + "1 3\r\n" + "3 1\r\n" + "1 2 4\r\n" + "1 3";

		Scanner scan = new Scanner(testCase);
		int T = scan.nextInt();
		while (T-- > 0) {
			Graph g = new Graph(new AdjList(scan.nextInt()));
			int E = scan.nextInt();
			while (E-- > 0) {
				g.getEdgeList().addEdge(scan.nextInt(), scan.nextInt(),
						scan.nextInt());
			}
			int src = scan.nextInt(), dst = scan.nextInt();
			Result dijkstra = new SingleSourceShortestPath().dijkstra(g, src,
					dst);
			Result bellmanFord = new SingleSourceShortestPath().bellmanFord(g,
					src, dst);
			System.out.println(dijkstra);
			System.out.println(bellmanFord);
		}
	}
}
