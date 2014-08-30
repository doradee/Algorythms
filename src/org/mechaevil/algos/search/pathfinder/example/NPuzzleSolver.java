package org.mechaevil.algos.search.pathfinder.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.mechaevil.algos.search.pathfinder.ASGoalNodeCallback;
import org.mechaevil.algos.search.pathfinder.AbsNode;
import org.mechaevil.algos.search.pathfinder.GoalNodeCallback;
import org.mechaevil.algos.search.pathfinder.LimitedAStarSearch;
import org.mechaevil.algos.search.pathfinder.LimitedDepthFirstSearch;
import org.mechaevil.algos.search.pathfinder.SolutionCallback;

public class NPuzzleSolver {

	private class NPuzzleNode extends AbsNode {

		private final int[][] dxdy = new int[][] { { 1, 0 }, { 0, 1 },
				{ -1, 0 }, { 0, -1 } };
		private char[] boardState;

		public NPuzzleNode(NPuzzleNode parent, int gx, char[] boardState) {
			super(parent, gx);
			this.boardState = boardState;
		}

		@Override
		public List<NPuzzleNode> getChildren() {
			int blankIndex = 0;
			int boardLen = boardState.length;
			while (blankIndex < boardLen && boardState[blankIndex] != '0')
				blankIndex++;
			int N = (int) Math.sqrt(boardLen);
			int x = blankIndex / N, y = blankIndex % N;
			List<NPuzzleNode> children = new ArrayList<NPuzzleNode>();
			for (int i = dxdy.length - 1; i >= 0; i--) {
				int _x = x + dxdy[i][0], _y = y + dxdy[i][1];
				if (_x >= 0 && _y >= 0 && _x < N && _y < N) {
					int index = _x * N + _y;
					char[] newboard = Arrays.copyOf(boardState, boardLen);
					newboard[blankIndex] = boardState[index];
					newboard[index] = '0';
					children.add(new NPuzzleNode(this, getGx() + 1, newboard));
				}
			}
			return children;
		}

		@Override
		public int hashCode() {
			return Arrays.hashCode(boardState);
		}

		@Override
		public boolean equals(Object obj) {
			return (obj instanceof NPuzzleNode)
					&& Arrays
							.equals(boardState, ((NPuzzleNode) obj).boardState);
		}

		@Override
		public String toString() {
			int N = (int) Math.sqrt(boardState.length);
			StringBuilder sb = new StringBuilder();
			for (int i = 0, k = 0; i < N; i++) {
				if (i > 0)
					sb.append('\n');
				for (int j = 0; j < N; j++) {
					sb.append(boardState[k++]).append(' ');
				}
			}

			return sb.toString();
			// return Arrays.toString(boardState);
		}

	}

	public static void main(String[] args) {
		new NPuzzleSolver().go2();

	}

	private void go2() {
		GoalNodeCallback<NPuzzleNode> gnc = new GoalNodeCallback<NPuzzleNode>() {
			private final char[] goalBoard = "0123456789ABCDEF".toCharArray();

			@Override
			public boolean isGoalNode(NPuzzleNode node) {
				return Arrays.equals(node.boardState, goalBoard);
			}
		};

		SolutionCallback<NPuzzleNode> sc = new SolutionCallback<NPuzzleSolver.NPuzzleNode>() {

			@Override
			public boolean processSolution(NPuzzleNode solutionNode) {
				LinkedList<AbsNode> stack = new LinkedList<AbsNode>();
				for (AbsNode node = solutionNode; node != null; node = node
						.getParent())
					stack.add(node);
				System.out.println(String.format("Solution Steps  : %d",
						solutionNode.getGx()));
				while (!stack.isEmpty()) {
					System.out.println(stack.pollLast());
					System.out.println();
				}
				return false;
			}
		};

		// 867254301 hardest
		// 647850321
		char[] startBoard = "0FD1C3B648952A7E".toCharArray();
		NPuzzleNode root = new NPuzzleNode(null, 0, startBoard);

		LimitedDepthFirstSearch<NPuzzleNode> ldfs = new LimitedDepthFirstSearch<NPuzzleSolver.NPuzzleNode>(
				sc, gnc, 50);
		ldfs.search(root);
		// DepthFirstSearch<NPuzzleNode> dfs = new
		// DepthFirstSearch<NPuzzleNode>(
		// sc, gnc);
		// dfs.search(new NPuzzleNode(null, 0, startBoard));

		// IterativeDepthFirstSearch<NPuzzleNode> idfs = new
		// IterativeDepthFirstSearch<NPuzzleSolver.NPuzzleNode>(sc, gnc);
		// idfs.search(new NPuzzleNode(null, 0, startBoard));

		// BreadthFirstSearch<NPuzzleNode> bfs = new
		// BreadthFirstSearch<NPuzzleSolver.NPuzzleNode>(sc, gnc);
		// bfs.search(root);

		ASGoalNodeCallback<NPuzzleNode> agnc = new ASGoalNodeCallback<NPuzzleSolver.NPuzzleNode>() {

			private final char[] goalBoard = "0123456789ABCDEF".toCharArray();

			@Override
			public boolean isGoalNode(NPuzzleNode node) {
				return Arrays.equals(node.boardState, goalBoard);
			}

			@Override
			public int getHx(NPuzzleNode node) {
				int hx = 0, N = (int) Math.sqrt(goalBoard.length);
				String goal = new String(goalBoard);
				for (int index = 0; index < node.boardState.length; index++) {
					char c = node.boardState[index];
					int gIndex = goal.indexOf(c);

					hx += Math.abs(index / N - gIndex / N)
							+ Math.abs(index % N - gIndex % N);
				}
				return hx;
			}
		};
		//
		// AStarSearch<NPuzzleNode> las = new
		// AStarSearch<NPuzzleSolver.NPuzzleNode>(
		// sc, agnc);
		// las.search(root);

	}

	private void go() {
		GoalNodeCallback<NPuzzleNode> gnc = new GoalNodeCallback<NPuzzleNode>() {
			private final char[] goalBoard = "123456780".toCharArray();

			@Override
			public boolean isGoalNode(NPuzzleNode node) {
				return Arrays.equals(node.boardState, goalBoard);
			}
		};

		SolutionCallback<NPuzzleNode> sc = new SolutionCallback<NPuzzleSolver.NPuzzleNode>() {

			@Override
			public boolean processSolution(NPuzzleNode solutionNode) {
				LinkedList<AbsNode> stack = new LinkedList<AbsNode>();
				for (AbsNode node = solutionNode; node != null; node = node
						.getParent())
					stack.add(node);
				System.out.println(String.format("Solution Steps  : %d",
						solutionNode.getGx()));
				while (!stack.isEmpty()) {
					System.out.println(stack.pollLast());
					System.out.println();
				}
				return true;
			}
		};

		// 867254301 hardest
		// 647850321
		char[] startBoard = "647850321".toCharArray();
		NPuzzleNode root = new NPuzzleNode(null, 0, startBoard);
		//
		// LimitedDepthFirstSearch<NPuzzleNode> ldfs = new
		// LimitedDepthFirstSearch<NPuzzleSolver.NPuzzleNode>(
		// sc, gnc, 100);
		// ldfs.search(new NPuzzleNode(null, 0, startBoard));
		// DepthFirstSearch<NPuzzleNode> dfs = new
		// DepthFirstSearch<NPuzzleNode>(
		// sc, gnc);
		// dfs.search(new NPuzzleNode(null, 0, startBoard));

		// IterativeDepthFirstSearch<NPuzzleNode> idfs = new
		// IterativeDepthFirstSearch<NPuzzleSolver.NPuzzleNode>(sc, gnc);
		// idfs.search(new NPuzzleNode(null, 0, startBoard));

		// BreadthFirstSearch<NPuzzleNode> bfs = new
		// BreadthFirstSearch<NPuzzleSolver.NPuzzleNode>(sc, gnc);
		// bfs.search(root);

		ASGoalNodeCallback<NPuzzleNode> agnc = new ASGoalNodeCallback<NPuzzleSolver.NPuzzleNode>() {

			private final char[] goalBoard = "123456780".toCharArray();

			@Override
			public boolean isGoalNode(NPuzzleNode node) {
				return Arrays.equals(node.boardState, goalBoard);
			}

			@Override
			public int getHx(NPuzzleNode node) {
				int hx = 0, N = (int) Math.sqrt(goalBoard.length);
				String goal = new String(goalBoard);
				for (int index = 0; index < node.boardState.length; index++) {
					char c = node.boardState[index];
					int gIndex = goal.indexOf(c);

					hx += Math.abs(index / N - gIndex / N)
							+ Math.abs(index % N - gIndex % N);
				}
				return hx;
			}
		};

		LimitedAStarSearch<NPuzzleNode> las = new LimitedAStarSearch<NPuzzleSolver.NPuzzleNode>(
				sc, agnc, 32);
		las.search(root);
	}

}
