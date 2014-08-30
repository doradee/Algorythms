package org.mechaevil.algos.search.pathfinder;

public class IterativeDepthFirstSearch<E extends AbsNode> extends
		AbstractSearchMethod<E> {

	public IterativeDepthFirstSearch(SolutionCallback<E> solutionCallback,
			GoalNodeCallback<E> goalNodeCallback) {
		super(solutionCallback, goalNodeCallback);
	}

	@Override
	public boolean search(E root) {
		for (int depth = 1;; depth++) {
			System.out.println(String.format("Depth : %d", depth));;
			LimitedDepthFirstSearch<E> fdfs = new LimitedDepthFirstSearch<E>(
					solutionCallback, goalNodeCallback, depth);
			if(!fdfs.search(root))
				return false;
		}
	}

}
