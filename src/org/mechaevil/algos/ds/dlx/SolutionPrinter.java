package org.mechaevil.algos.ds.dlx;

import java.util.List;

public class SolutionPrinter implements SolutionCallback {

	@Override
	public boolean accept(List<Node> rows) {
		System.out.println("Solution");
		for (Node n : rows)
			System.out.println(n);
		return true;
	}

}
