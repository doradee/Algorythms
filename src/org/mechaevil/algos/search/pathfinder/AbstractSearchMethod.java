package org.mechaevil.algos.search.pathfinder;

public abstract class AbstractSearchMethod<E extends AbsNode> {

	protected final SolutionCallback<E> solutionCallback;
	protected final GoalNodeCallback<E> goalNodeCallback;


	public AbstractSearchMethod(SolutionCallback<E> solutionCallback,
			GoalNodeCallback<E> goalNodeCallback) {
		this.solutionCallback = solutionCallback;
		this.goalNodeCallback = goalNodeCallback;
	}


	public abstract boolean search(E root);

}
