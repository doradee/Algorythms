package org.mechaevil.algos.encoder;

import java.util.PriorityQueue;

import org.mechaevil.algos.debug.Debug;

public class HuffmanEncoder {

	private class Node implements Comparable<Node> {
		char c;
		int freq;
		int code, codeLen;
		Node left, right;

		public Node(char c, int freq) {
			this.c = c;
			this.freq = freq;
		}

		public Node(Node left, Node right) {
			this.left = left;
			this.right = right;
			this.freq = left.freq + right.freq;
			this.c = 0;
		}

		@Override
		public int compareTo(Node o) {
			return freq < o.freq ? -1 : 1;
		}

		@Override
		public String toString() {
			return String.format("%c [%d] : %s", c, freq,
					padLeft(Integer.toBinaryString(code), "0", codeLen));
		}
	}

	private String padLeft(String s, String p, int l) {
		StringBuilder sb = new StringBuilder();
		l = l - s.length();
		while (l-- > 0)
			sb.append(p);
		return sb.append(s).toString();
	}

	private Node[] nodes;
	private char[] dataArray;

	public HuffmanEncoder(String text) {
		createCodes(processText(this.dataArray = text.toCharArray()), 0, 0);
	}

	private void createCodes(Node node, int code, int codeLen) {
		if (node != null) {
			if (node.left == null && node.right == null) {
				node.code = code;
				node.codeLen = codeLen;
			}
			createCodes(node.left, code << 1, codeLen + 1);
			createCodes(node.right, (code << 1) | 1, codeLen + 1);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 256; i++)
			if (nodes[i] != null && nodes[i].freq > 0)
				sb.append(nodes[i]).append('\n');
		return sb.append(compressionStats()).toString();
	}

	private Node processText(char[] dataArray) {
		nodes = new Node[256];
		for (char c : dataArray) {
			if (nodes[c] == null)
				nodes[c] = new Node(c, 0);
			nodes[c].freq++;
		}
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		for (Node node : nodes)
			if (node != null)
				pq.add(node);
		while (pq.size() > 1) {
			pq.add(new Node(pq.poll(), pq.poll()));
		}
		return pq.poll();
	}

	private String compressionStats() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Original Length : %d", dataArray.length))
				.append('\n');
		long totalBits = 0;
		for (char c : dataArray)
			totalBits += nodes[c].codeLen;
		long totalLen = (totalBits + 8) / 8;
		sb.append(String.format("Compressed Length : %d", totalLen)).append(
				'\n');
		sb.append(String.format("Compression Ratio : %.2f%%",
				100 * (totalLen / (1.0 * dataArray.length))));

		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		String text = Debug.randomString(10000);
		// text = Files.toString(new File("C:\\332609\\dict.txt"),
		// Charsets.US_ASCII);
		System.out.println(new HuffmanEncoder(text));
	}
}
