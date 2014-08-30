package org.mechaevil.algos.ds.dlx;

import java.util.ArrayList;
import java.util.List;

public class RootNode extends ColumnHeader {

	private List<ColumnHeader> columns;
	private List<Node> rows;

	public RootNode() {
		super(-1);
		this.columns = new ArrayList<ColumnHeader>();
		this.rows = new ArrayList<Node>();
	}

	public void addColumn(ColumnHeader columnHeader) {
		columns.add(columnHeader);
		Node lastColumnHeader = getLeft();
		// Append to last Column header
		lastColumnHeader.setRight(columnHeader);
		// make Root's left as new header
		setLeft(columnHeader);
		// Fix links of the new header
		columnHeader.setLeft(lastColumnHeader);
		columnHeader.setRight(this);
	}

	public void addRow(boolean[] values) {
		if (values.length != columns.size())
			throw new IllegalArgumentException(
					"Array length does not match number of columns.");

		Node firstNode = null;
		for (int i = 0, l = values.length; i < l; i++) {
			if (values[i]) {
				ColumnHeader ch = columns.get(i); // ColumnHeader of the new
													// Node
				Node newNode = new Node(rows.size(), i);

				if (firstNode == null) {
					firstNode = newNode; // First node of row?
				} else {
					Node rowNode = firstNode.getLeft();

					// Set left/right links of newNode
					newNode.setLeft(rowNode);
					newNode.setRight(firstNode);

					// loop first node to the new one
					firstNode.setLeft(newNode);
					// loop new node to the first one
					rowNode.setRight(newNode);
				}

				ch.addNode(newNode);
			}
		}
		if (firstNode != null)
			rows.add(firstNode);
	}

	public void coverColumn(ColumnHeader columnHeader) {
		columnHeader.getRight().setLeft(columnHeader.getLeft());
		columnHeader.getLeft().setRight(columnHeader.getRight());

		for (Node i = columnHeader.getBottom(); i != columnHeader; i = i
				.getBottom()) {
			for (Node j = i.getRight(); j != i; j = j.getRight()) {
				j.getBottom().setTop(j.getTop());
				j.getTop().setBottom(j.getBottom());
				j.getHeader().setSize(j.getHeader().getSize() - 1);
			}
		}
	}

	public void uncoverColumn(ColumnHeader columnHeader) {

		for (Node i = columnHeader.getTop(); i != columnHeader; i = i.getTop()) {
			for (Node j = i.getLeft(); j != i; j = j.getLeft()) {
				j.getBottom().setTop(j);
				j.getTop().setBottom(j);
				j.getHeader().setSize(j.getHeader().getSize() + 1);
			}
		}
		columnHeader.getRight().setLeft(columnHeader);
		columnHeader.getLeft().setRight(columnHeader);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append('\n');
		Node ch = getRight();
		do {
			sb.append(ch).append(" : ");
			Node node = ch.getBottom();
			do {
				sb.append(node).append(',');
				node = node.getBottom();
			} while (node != ch);
			ch = ch.getRight();
			sb.append('\n');
		} while (ch != this);
		return sb.toString();
	}

	public ColumnHeader getColumn(int i) {
		return columns.get(i);
	}
}
