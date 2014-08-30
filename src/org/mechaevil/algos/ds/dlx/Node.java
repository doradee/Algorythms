package org.mechaevil.algos.ds.dlx;

public class Node {

	private final int i, j;
	private Node top, bottom, left, right;
	private ColumnHeader header;

	public Node(int i, int j) {
		this.i = i;
		this.j = j;
		this.top = this.bottom = this.left = this.right = this;
	}

	public Node getTop() {
		return top;
	}

	public void setTop(Node top) {
		this.top = top;
	}

	public Node getBottom() {
		return bottom;
	}

	public void setBottom(Node bottom) {
		this.bottom = bottom;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public ColumnHeader getHeader() {
		return header;
	}

	public void setHeader(ColumnHeader header) {
		this.header = header;
	}

	@Override
	public String toString() {
		if (i == -1)
			return j == -1 ? "Root  " : String.format("[%d] ", j);
		return String.format("(%d-%d)", i, j);
	}

}
