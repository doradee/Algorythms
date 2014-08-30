package org.mechaevil.algos.search.pathfinder;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LimitedDepthFirstSearch<E extends AbsNode> extends
		AbstractSearchMethod<E> {

	private final int maxDepth;

	public LimitedDepthFirstSearch(SolutionCallback<E> solutionCallback,
			GoalNodeCallback<E> goalNodeCallback, int maxDepth) {
		super(solutionCallback, goalNodeCallback);
		this.maxDepth = maxDepth;
	}

	private class DFSNode {
		private final AbsNode node;
		private final int depth;

		public DFSNode(AbsNode node, int depth) {
			this.node = node;
			this.depth = depth;
		}

		public AbsNode getNode() {
			return node;
		}

		public int getDepth() {
			return depth;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean search(E root) {
		List<DFSNode> stack = new LinkedList<DFSNode>();
		Set<E> visitedNodes = new HashSet<E>();
		stack.add(new DFSNode(root, 0));
		while (!stack.isEmpty()) {
			DFSNode dfsNode = stack.remove(0);
			if (dfsNode.getDepth() < maxDepth) {
				E node = (E) dfsNode.getNode();
				visitedNodes.add(node);
				if (goalNodeCallback.isGoalNode(node)) {
					if (!solutionCallback.processSolution(node))
						return false;
				}
				for (AbsNode child : node.getChildren()) {
					if (!visitedNodes.contains(child)) {
						stack.add(new DFSNode(child, dfsNode.getDepth() + 1));
					}
				}
			}
		}
		return true;
	}
}
