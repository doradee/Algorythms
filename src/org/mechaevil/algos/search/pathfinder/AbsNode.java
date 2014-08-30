package org.mechaevil.algos.search.pathfinder;

import java.util.List;

public abstract class AbsNode {

	private final AbsNode parent;
	private final int gx;

	public AbsNode(AbsNode parent, int gx) {
		this.parent = parent;
		this.gx = gx;
	}

	public int getGx() {
		return gx;
	}

	public AbsNode getParent() {
		return parent;
	}

	public abstract <E extends AbsNode> List<E> getChildren();

	public abstract int hashCode();

	public abstract boolean equals(Object obj);

}
