package org.mechaevil.algos.graphs.edgelist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mechaevil.algos.debug.Debug;
import org.mechaevil.algos.graphs.Graph;
import org.mechaevil.algos.graphs.MinimumSpanningTree;
import org.mechaevil.algos.graphs.SingleSourceShortestPath;
import org.mechaevil.algos.graphs.SingleSourceShortestPath.Result;

public class AdjGrid extends EdgeList {

	private final int[][] dxy4 = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 },
			{ -1, 0 } };
	private final int[][] dxy8 = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 },
			{ -1, 0 }, { -1, -1 }, { 1, -1 }, { -1, 1 }, { 1, 1 } };

	private int[][] grid;
	private int W, H;
	private boolean fourWay;

	private class XYEdge extends Edge {

		public XYEdge(int x1, int y1, int x2, int y2, int w) {
			super(xy(x1, y1), xy(x2, y2), w);
		}

		@Override
		public String toString() {
			int u = u(), v = v();
			return String.format("(%d,%d)-(%d,%d):%d", u / W, u % W, v / W, v
					% W, w());
		}
	}

	public AdjGrid(int[][] grid) {
		this(grid, true);
	}

	public AdjGrid(int[][] grid, boolean fourWay) {
		super(grid.length * grid[0].length);
		this.grid = grid;
		this.W = grid.length;
		this.H = grid[0].length;
		this.fourWay = fourWay;
	}

	@Override
	public void addEdge(int u, int v) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addEdge(int u, int v, int w) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<Edge> edges(int u) {
		return getNeighbours(u, fourWay ? dxy4 : dxy8);
	}

	public int xy(int x, int y) {
		return x * W + y;
	}

	public int[] xy(int u) {
		return new int[] { u / W, u % W };
	}

	private Iterable<Edge> getNeighbours(int u, int[][] dxy) {
		List<Edge> list = new ArrayList<Edge>(4);
		int x = u / W, y = u % W;
		for (int i = 0, l = dxy.length; i < l; i++) {
			int _x = x + dxy[i][0], _y = y + dxy[i][1];
			if (_x >= 0 && _y >= 0 && _x < W && _y < H)
				list.add(new XYEdge(x, y, _x, _y, grid[_x][_y]));
		}
		return list;
	}

	public static void main(String[] args) {
		final int N = 1000;
		int[][] grid = new int[N][N];
		for (int i = 0; i < N; i++)
			grid[i] = Debug.randomArray(N, 10, 100);
		for (int[] arr : grid)
			System.out.println(Arrays.toString(arr));
		System.out.println();
		AdjGrid adjGrid = new AdjGrid(grid);
		Result dijkstra = new SingleSourceShortestPath().dijkstra(new Graph(
				adjGrid), adjGrid.xy(0, 0), adjGrid.xy(N - 1, N - 1));
		System.out.println(dijkstra);

		System.out.println(new MinimumSpanningTree().prims(new Graph(adjGrid)));

	}

	@Override
	public int indegree(int u) {
		int[][] dxy = fourWay ? dxy4 : dxy8;
		int c = 0;
		int x = u / W, y = u % W;
		for (int i = 0, l = dxy.length; i < l; i++) {
			int _x = x + dxy[i][0], _y = y + dxy[i][1];
			if (_x >= 0 && _y >= 0 && _x < W && _y < H)
				c++;
		}
		return c;
	}

	@Override
	public int outdegree(int u) {
		return indegree(u);
	}

}
