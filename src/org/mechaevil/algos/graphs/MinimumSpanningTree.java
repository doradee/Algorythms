package org.mechaevil.algos.graphs;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.mechaevil.algos.ds.UnionFind;
import org.mechaevil.algos.graphs.edgelist.AdjMatrix;
import org.mechaevil.algos.graphs.edgelist.Edge;

public class MinimumSpanningTree {

	public class Result {
		private Edge[] edges;
		private int mstCost;

		public Result(Edge[] edges, int mstCost) {
			this.edges = edges;
			this.mstCost = mstCost;
		}

		@Override
		public String toString() {
			return String.format("%d = %s", mstCost, Arrays.toString(edges));
		}
	}

	public Result kruskal(Graph g) {
		UnionFind uf = new UnionFind(g.getEdgeList().getVertexCount());
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(g.getEdgeList()
				.getEdges());
		List<Edge> mst = new ArrayList<Edge>();
		int mstCost = 0;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (!uf.connected(edge.u(), edge.v())) { // belong to different
														// trees?
				mst.add(edge);
				mstCost += edge.w();
				uf.union(edge.u(), edge.v());
			}
		}
		return new Result(mst.toArray(new Edge[mst.size()]), mstCost);
	}

	public Result prims(Graph g) {
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		boolean[] visited = new boolean[g.getEdgeList().getVertexCount()];
		int u = (int) (Math.random() * (float) g.getEdgeList().getVertexCount()), mstCost = 0;
		List<Edge> mst = new ArrayList<Edge>();
		for (Edge e : g.getEdgeList().edges(u))
			pq.add(e);
		visited[u] = true;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (visited[edge.u()] && visited[edge.v()])
				continue;
			int v = visited[edge.u()] ? edge.v() : edge.u();
			mst.add(edge);
			mstCost += edge.w();
			visited[v] = true;
			for (Edge e : g.getEdgeList().edges(v))
				pq.add(e);
		}
		return new Result(mst.toArray(new Edge[mst.size()]), mstCost);
	}

	public static void main(String[] args) throws Exception {
		kruskalTest();
		primsTest();

	}

	private static void kruskalTest() throws Exception {
		System.out.println("Kruskal Algorithm");
		// Solve Project Euler 107
		System.setProperty("http.proxyHost", "proxy.tcs.com");
		System.setProperty("http.proxyPort", "8080");
		Scanner scan = new Scanner(new URL(
				"http://projecteuler.net/project/network.txt").openStream());
		Graph g = new Graph(new AdjMatrix(40));
		int totalCost = 0;
		int u = 0;
		while (scan.hasNext()) {
			String line = scan.next();
			String[] value = line.split(",");
			for (int i = 0; i < value.length; i++) {
				if (!"-".equals(value[i])) {
					totalCost += Integer.valueOf(value[i]);
					g.getEdgeList().addEdge(u, i, Integer.valueOf(value[i]));
				}
			}
			u++;
		}
		Result result = new MinimumSpanningTree().kruskal(g);
		System.out.println(result);
		System.out.println(String.format("Total Cost : %d", totalCost / 2));
		System.out.println(String.format("PE107 : %d", totalCost / 2
				- result.mstCost));
		// Project Euler #107 : 259679
	}

	private static void primsTest() throws Exception {
		System.out.println("Prims Algorithm");
		// Solve Project Euler 107
		System.setProperty("http.proxyHost", "proxy.tcs.com");
		System.setProperty("http.proxyPort", "8080");
		Scanner scan = new Scanner(new URL(
				"http://projecteuler.net/project/network.txt").openStream());
		Graph g = new Graph(new AdjMatrix(40));
		int totalCost = 0;
		int u = 0;
		while (scan.hasNext()) {
			String line = scan.next();
			String[] value = line.split(",");
			for (int i = 0; i < value.length; i++) {
				if (!"-".equals(value[i])) {
					totalCost += Integer.valueOf(value[i]);
					g.getEdgeList().addEdge(u, i, Integer.valueOf(value[i]));
				}
			}
			u++;
		}
		Result result = new MinimumSpanningTree().prims(g);
		System.out.println(result);
		System.out.println(String.format("Total Cost : %d", totalCost / 2));
		System.out.println(String.format("PE107 : %d", totalCost / 2
				- result.mstCost));
		// Project Euler #107 : 259679
	}
}
