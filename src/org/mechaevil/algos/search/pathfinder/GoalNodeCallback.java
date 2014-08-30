package org.mechaevil.algos.search.pathfinder;

public interface GoalNodeCallback<E extends AbsNode> {

	public boolean isGoalNode(E node);

}
