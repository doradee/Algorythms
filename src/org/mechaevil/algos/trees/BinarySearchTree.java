package org.mechaevil.algos.trees;

import java.util.LinkedList;
import java.util.Random;

public class BinarySearchTree<E extends Comparable<? super E>> {

	public interface BSTVisitor<E> {
		public boolean onVisit(E data); // return false, when you want to stop
										// iteration
	}

	private class ToString implements BSTVisitor<E> {
		private StringBuilder sb = new StringBuilder("[");

		@Override
		public boolean onVisit(E data) {
			if (sb.length() > 1)
				sb.append(',');
			sb.append(data);
			return true;
		}

		@Override
		public String toString() {
			return sb.append(']').toString();
		}

	}

	private class Node implements Comparable<Node> {
		private Node left, right, parent;
		private E data;

		public Node(Node parent, E data) {
			this(parent, data, null, null);
		}

		public Node(Node parent, E data, Node left, Node right) {
			this.left = left;
			this.right = right;
			this.data = data;
			setParent(parent);
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		@Override
		public int compareTo(Node o) {
			return data.compareTo(o.data);
		}

		@Override
		public String toString() {
			return String.format("%d[%d,%d]", data, left == null ? -1
					: left.data, right == null ? -1 : right.data);
		}

	}

	private Node root;
	private int size;

	public boolean add(E data) {
		if (data == null)
			return false;
		if (root == null) {
			size++;
			return (root = new Node(null, data)) != null;
		} else
			return addValue(data);
	}

	private boolean addValue(E data) {
		if (data == null)
			return false;
		Node index = root, prevNode = null;
		while (index != null) {
			prevNode = index;
			int cmp = index.data.compareTo(data);
			if (cmp == 0) {
				index.data = data; // update with newly added value
				return true;
			} else if (cmp < 0)
				index = index.right;
			else
				index = index.left;
		}
		if (index == null) {
			Node node = new Node(prevNode, data);
			if (prevNode.data.compareTo(data) < 0)
				prevNode.right = node;
			else
				prevNode.left = node;
		}
		size++;
		return true;
	}

	public boolean contains(E data) {
		return null != findNode(data);
	}

	private Node findNode(E data) {
		Node index = root;
		while (index != null && index.data.compareTo(data) != 0) {
			if (index.data.compareTo(data) < 0)
				index = index.right;
			else
				index = index.left;
		}
		return index;
	}

	public E findMin() {
		return findMin(root);
	}

	public E findMax() {
		return findMax(root);
	}

	private E findMin(Node index) {
		while (index != null && index.left != null)
			index = index.left;
		return index != null ? index.data : null;
	}

	private Node findMinNode(Node index) {
		while (index != null && index.left != null)
			index = index.left;
		return index;
	}

	private E findMax(Node index) {
		while (index != null && index.right != null)
			index = index.right;
		return index != null ? index.data : null;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean remove(E data) {
		Node node = findNode(data);
		if (node == null)
			return false;
		removeNode(node);
		size--;
		return true;
	}

	private void removeNode(Node node) {
		if (node.left != null && node.right != null) { // two child nodes?
			Node minNode = findMinNode(node.right);
			node.data = minNode.data;
			removeNode(minNode);
		} else {
			Node p = node.parent;
			Node anyChild = node.left == null ? node.right : node.left;
			if (p != null) {
				if (p.left == node)
					p.left = anyChild;
				else
					p.right = anyChild;
			}
			node.setParent(null);
			if (anyChild != null)
				anyChild.setParent(p);
			if (node == root)
				root = anyChild;
		}
	}

	public void inorder(BSTVisitor<E> callback) {
		iterateInorder(callback);
	}

	private void iterateInorder(BSTVisitor<E> callback) {
		if (callback != null && root != null) {
			LinkedList<Node> stack = new LinkedList<Node>();
			Node node = root;
			while (!stack.isEmpty() || null != node) {
				if (null != node) {
					stack.addLast(node);
					node = node.left;
				} else {
					node = stack.removeLast();
					if (!callback.onVisit(node.data))
						break;
					node = node.right;
				}
			}
		}
	}

	@Override
	public String toString() {
		ToString ts = new ToString();
		inorder(ts);
		return ts.toString();
	}

	public static void main(String[] args) {
		Random r = new Random();
		BinarySearchTree<Integer> binTree = new BinarySearchTree<Integer>();
		for (int i = 0; i < 100; i++) {
			binTree.add(r.nextInt(1000));
		}

		System.out.println(binTree);
		while (!binTree.isEmpty()) {
			Integer min = binTree.findMin();
			binTree.remove(min);
			System.out.println(min);
			// System.out.println(binTree);
		}
	}

}
