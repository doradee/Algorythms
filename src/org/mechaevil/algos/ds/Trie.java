package org.mechaevil.algos.ds;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Trie {

	private class TNode {
		private final char c;
		private final int len;
		private TNode[] child;
		private boolean EOW;

		public TNode(char c, int len) {
			this.c = Character.toLowerCase(c);
			this.len = len;
			this.EOW = false;
		}

		public void setEndOfWord(boolean b) {
			this.EOW = b;
		}

		public TNode getChild(char c) {
			return (child == null) ? null
					: child[Character.toLowerCase(c) - 'a'];
		}

		public TNode addChild(char next) {
			if (child == null)
				child = new TNode[26];
			return child[Character.toLowerCase(next) - 'a'] = new TNode(next,
					len + 1);
		}
	}

	private final TNode root;
	private int size;

	public Trie() {
		root = new TNode((char) 0, 0);
	}

	private TNode findPrefixNode(String text) {
		TNode node = root;
		for (char c : text.toCharArray()) {
			TNode nextNode = node.getChild(c);
			if (nextNode == null)
				return node;
			node = nextNode;
		}
		return node;
	}

	public List<String> prefixSearch(String prefix) {
		TNode prefixNode = findPrefixNode(prefix);
		if (prefixNode.len < prefix.length())
			return null;
		List<String> list = new ArrayList<String>();
		if (prefixNode.EOW)
			list.add(prefix);
		iterateAll(prefixNode, list, prefix);
		return list;
	}

	private void iterateAll(TNode prefixNode, List<String> list, String prefix) {
		if (prefixNode != null && prefixNode.child != null) {
			for (TNode child : prefixNode.child) {
				if (child != null) {
					String word = String.format("%s%c", prefix, child.c);
					if (child.EOW)
						list.add(word);
					iterateAll(child, list, word);
				}
			}
		}
	}

	public void addWord(String word) {
		if (word == null || word.length() == 0)
			return;
		TNode node = findPrefixNode(word);
		int l = word.length();
		for (int i = node.len; i < l; i++)
			node = node.addChild(word.charAt(i));
		node.setEndOfWord(true);
		size++;
	}

	public int size() {
		return size;
	}

	public boolean contains(String key) {
		TNode node = findPrefixNode(key);
		return node != null && node.EOW;
	}

	public static void main(String[] args) throws Exception {
		Trie trie = new Trie();
		Scanner scan = new Scanner(new File("C:\\332609\\mword10\\SINGLE.txt"));
		String prefix = "try";
		List<String> list = new ArrayList<String>();
		while (scan.hasNext()) {
			String key = scan.next();
			key = key.replaceAll("[^A-Za-z]", "");
			trie.addWord(key);
			list.add(key);
		}
		testPrefix(list, prefix);
		testTrie(trie, prefix);
	}

	private static void testPrefix(List<String> list, String prefix) {
		long start = System.currentTimeMillis();
		List<String> ans = new ArrayList<String>();
		for (int i = 0, sz = list.size(); i < sz; i++) {
			if (list.get(i).startsWith(prefix))
				ans.add(list.get(i));
		}
		System.out.println(System.currentTimeMillis() - start);
		System.out.println(ans);
		System.out.println(ans.size());
	}

	private static void testTrie(Trie trie, String prefix) {
		long start = System.currentTimeMillis();
		List<String> ans = trie.prefixSearch(prefix);
		System.out.println(System.currentTimeMillis() - start);
		System.out.println(ans);
		System.out.println(ans.size());
	}

}