package org.mechaevil.algos.trees;

import java.util.Random;

public class AVLTree<E extends Comparable<? super E>> {

	final int THRESHOLD = 2;

	private AVLNode root;
	private int size;

	public void add(E data) {
		root = insert(root, data);
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean contains(E data) {
		AVLNode node = findNode(data);
		return node != null && node.data.compareTo(data) == 0;
	}

	private AVLNode findNode(E data) {
		AVLNode node = root, prev = null;
		while (node != null) {
			prev = node;
			int cmp = data.compareTo(node.data);
			if (cmp == 0)
				return node;
			if (cmp < 0)
				node = node.left;
			else
				node = node.right;
		}
		return prev;
	}

	private AVLNode insert(AVLNode parent, E data) {
		if (parent == null) {
			size++;
			return new AVLNode(data);
		}
		int cmp = data.compareTo(parent.data);
		if (cmp != 0) {
			if (cmp < 0) {
				parent.left = insert(parent.left, data);
			} else if (cmp > 0) {
				parent.right = insert(parent.right, data);
			} else {
				parent.data = data;
			}

			switch (height(parent.left) - height(parent.right)) {
			case THRESHOLD:
				if (data.compareTo(parent.left.data) < 0)
					parent = rotateLeft(parent);
				else
					parent = doubleRotateLeft(parent);
				break;
			case -THRESHOLD:
				if (data.compareTo(parent.right.data) > 0)
					parent = rotateRight(parent);
				else
					parent = doubleRotateRight(parent);
				break;
			default:
				break;
			}
		}
		return parent;
	}

	public int size() {
		return size;
	}

	public E min() {
		AVLNode node = root;
		if (node != null)
			while (node.left != null)
				node = node.left;
		return node == null ? null : node.data;
	}

	public E max() {
		AVLNode node = root;
		if (node != null)
			while (node.right != null)
				node = node.right;
		return node == null ? null : node.data;
	}

	private class AVLNode {
		private E data;
		private AVLNode left, right;

		public AVLNode(E data) {
			this.left = this.right = null;
			this.data = data;
		}
	}

	private AVLNode doubleRotateRight(AVLNode node) {
		node.right = rotateLeft(node.right);
		return rotateRight(node);
	}

	private AVLNode rotateRight(AVLNode node) {
		AVLNode k2 = node.right;
		node.right = k2.left;
		k2.left = node;
		return k2;
	}

	private AVLNode rotateLeft(AVLNode node) {
		AVLNode k1 = node.left;
		node.left = k1.right;
		k1.right = node;
		return k1;
	}

	private AVLNode doubleRotateLeft(AVLNode node) {
		node.left = rotateRight(node.left);
		return rotateLeft(node);
	}

	private int height(AVLNode node) {
		return node != null ? 1 + Math.max(height(node.left),
				height(node.right)) : 0;
	}

	private void inorder(StringBuilder sb, AVLNode node) {
		if (node != null) {
			if (node.left != null)
				inorder(sb, node.left);
			if (sb.length() > 0)
				sb.append(", ");
			sb.append(node.data);
			if (node.right != null)
				inorder(sb, node.right);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		inorder(sb, root);
		return sb.toString();
	}

	public static void main(String[] args) {
		Random r = new Random();
		AVLTree<Integer> tree = new AVLTree<Integer>();
		for (int i = 0; i < 100; i++)
			tree.add(r.nextInt(101));
		System.out.println(tree);
		System.out.println(tree.min());
		System.out.println(tree.max());
		System.out.println(tree.contains(100));
	}

}
