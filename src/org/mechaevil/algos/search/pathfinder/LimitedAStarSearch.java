package org.mechaevil.algos.search.pathfinder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class LimitedAStarSearch<E extends AbsNode> extends
		AbstractSearchMethod<E> {

	private ASGoalNodeCallback<E> asGoalNodeCallback;
	private final int maxDepth;

	public LimitedAStarSearch(SolutionCallback<E> solutionCallback,
			ASGoalNodeCallback<E> goalNodeCallback, int maxDepth) {
		super(solutionCallback, goalNodeCallback);
		this.asGoalNodeCallback = goalNodeCallback;
		this.maxDepth = maxDepth;
	}

	private class AStarNode implements Comparable<AStarNode> {
		private final E node;
		private final int hx;
		private final int depth;
		private boolean invalid;

		public AStarNode(E node, int depth) {
			this.node = node;
			this.depth = depth;
			this.hx = asGoalNodeCallback.getHx(node);
			this.invalid = false;
		}

		public int getDepth() {
			return depth;
		}

		public void setInvalid(boolean invalid) {
			this.invalid = invalid;
		}

		public boolean isInvalid() {
			return invalid;
		}

		public E getNode() {
			return node;
		}

		@Override
		public int compareTo(AStarNode o) {
			int t1 = node.getGx() + hx;
			int t2 = o.node.getGx() + o.hx;
			if (t1 == t2) {
				return node.getGx() < o.node.getGx() ? -1 : 1;
			}
			return t1 < t2 ? -1 : 1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean search(E root) {
		PriorityQueue<AStarNode> openList = new PriorityQueue<AStarNode>();
		Map<E, AStarNode> openMap = new HashMap<E, AStarNode>();
		Set<E> closedList = new HashSet<E>();
		openList.add(new AStarNode(root,0));

		while (!openList.isEmpty()) {
			AStarNode asnode = openList.poll();
			if (asnode.getDepth() < maxDepth && !asnode.isInvalid()) {
				E node = asnode.getNode();
				closedList.add(node);
				if (goalNodeCallback.isGoalNode(node)) {
					if (!solutionCallback.processSolution(node))
						return false;
				}
				for (AbsNode child : node.getChildren()) {
					// ignore if it's present in closed List
					if (closedList.contains(child))
						continue;
					AStarNode exists = openMap.get(child);
					// already exists in openList?
					if (exists != null) {
						// new child's path is better?
						if (exists.getNode().getGx() > child.getGx()) {
							// mark it invalid, easier than searching through PQ
							// heap.
							exists.setInvalid(true);
							AStarNode asChild = new AStarNode((E) child, asnode.getDepth() + 1);
							openList.add(asChild);
							openMap.put((E) child, asChild);
						}
					} else {
						AStarNode asChild = new AStarNode((E) child,asnode.getDepth() + 1);
						openList.add(asChild);
						openMap.put((E) child, asChild);
					}
				}
			}
		}
		return false;
	}

}
