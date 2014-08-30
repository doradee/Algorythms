package org.mechaevil.algos.ds.dlx;

public class ColumnHeader extends Node {

	private int size;

	public ColumnHeader(int j) {
		super(-1, j);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void addNode(Node newNode) {
		newNode.setHeader(this);
		newNode.setTop(getTop());
		newNode.setBottom(this);
		getTop().setBottom(newNode);
		setTop(newNode);
		size++;
	}

}
