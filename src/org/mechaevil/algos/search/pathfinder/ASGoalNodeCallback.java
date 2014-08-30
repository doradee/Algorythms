package org.mechaevil.algos.search.pathfinder;

public interface ASGoalNodeCallback<E extends AbsNode> extends
		GoalNodeCallback<E> {
	public int getHx(E node);
}