package org.mechaevil.algos.search.pathfinder;

public class DepthFirstSearch<E extends AbsNode> extends LimitedDepthFirstSearch<E> {

	public DepthFirstSearch(SolutionCallback<E> solutionCallback,
			GoalNodeCallback<E> goalNodeCallback) {
		super(solutionCallback, goalNodeCallback, Integer.MAX_VALUE);
	}

}
