package org.mechaevil.algos.ds.dlx;

import java.util.ArrayList;
import java.util.List;

public class DancingLinks {

	private RootNode root;
	private List<Node> solutionRows;
	private SolutionCallback callback;

	public DancingLinks(SolutionCallback callback) {
		this.root = new RootNode();
		this.solutionRows = new ArrayList<Node>();
		this.callback = callback;
	}

	public void addColumn(ColumnHeader ch) {
		this.root.addColumn(ch);
	}

	public void addRow(boolean[] values) {
		this.root.addRow(values);
	}

	public ColumnHeader chooseColumn() {
		ColumnHeader minHeader = null;
		for (ColumnHeader ch = (ColumnHeader) root.getRight(); ch != root; ch = (ColumnHeader) ch
				.getRight()) {
			if (minHeader == null || minHeader.getSize() > ch.getSize())
				minHeader = ch;
		}
		return minHeader;
	}

	public boolean solve() {
		boolean ret = true;
		if (root == root.getRight()) {
			if (callback != null) {
				return callback.accept(solutionRows);
			}
		} else {
			ColumnHeader bestColumn = chooseColumn();
			root.coverColumn(bestColumn);
			Node node = bestColumn.getBottom();
			while (node != bestColumn) {

				solutionRows.add(node);

				for (Node j = node.getRight(); j != node; j = j.getRight())
					root.coverColumn(j.getHeader());

				ret = solve();

				solutionRows.remove(node);

				for (Node j = node.getLeft(); j != node; j = j.getLeft())
					root.uncoverColumn(j.getHeader());

				node = node.getBottom();
				if (!ret)
					break;
			}
			root.uncoverColumn(bestColumn);
		}
		return ret;
	}

	@Override
	public String toString() {
		return root.toString();
	}

	public static void main(String[] args) {

		boolean[][] testCase = new boolean[][] { { false, false, true, true },
				{ true, true, false, false, }, { true, false, true, false, },
				{ false, true, false, true, }, };
		DancingLinks dlx = new DancingLinks(new SolutionPrinter());
		int N = testCase[0].length;
		for (int i = 0; i < N; i++)
			dlx.addColumn(new ColumnHeader(i));

		for (int j = 0; j < testCase.length; j++) {
			dlx.addRow(testCase[j]);
		}
		System.out.println(dlx);
		dlx.solve();
		System.out.println(dlx);
	}

}
