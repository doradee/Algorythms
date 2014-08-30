package org.mechaevil.algos.search.pathfinder;

public interface SolutionCallback<E extends AbsNode>{

	public boolean processSolution(E solutionNode);

}
