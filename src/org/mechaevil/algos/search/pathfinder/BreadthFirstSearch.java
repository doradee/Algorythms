package org.mechaevil.algos.search.pathfinder;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearch<E extends AbsNode> extends
		AbstractSearchMethod<E> {

	public BreadthFirstSearch(SolutionCallback<E> solutionCallback,
			GoalNodeCallback<E> goalNodeCallback) {
		super(solutionCallback, goalNodeCallback);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean search(E root) {
		Set<E> visitedNodes = new HashSet<E>();
		Queue<E> queue = new LinkedList<E>();
		queue.add(root);
		while(!queue.isEmpty()){
			E node = queue.poll();
			visitedNodes.add(node);
			if (goalNodeCallback.isGoalNode(node)) {
				if (!solutionCallback.processSolution(node))
					return false;
			}
			for (AbsNode child : node.getChildren()) {
				if (!visitedNodes.contains(child)) {
					queue.add((E)child);
				}
			}
		}
		return true;
	}

}
