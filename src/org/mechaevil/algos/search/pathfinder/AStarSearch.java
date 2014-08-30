package org.mechaevil.algos.search.pathfinder;

public class AStarSearch<E extends AbsNode>extends LimitedAStarSearch<E> {

	public AStarSearch(SolutionCallback<E> solutionCallback,
			ASGoalNodeCallback<E> goalNodeCallback) {
		super(solutionCallback, goalNodeCallback, Integer.MAX_VALUE);
	}

}
