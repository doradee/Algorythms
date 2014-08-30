package org.mechaevil.algos.ds;

// Default Min heap (override cmp() implementation)
public class DHeap {

	private final int[] heap;
	private int heapSize;
	private final int nChildren;

	public DHeap(int size, int nChildren) {
		this.heap = new int[size];
		this.heapSize = 0;
		if (nChildren < 2)
			throw new IllegalArgumentException(
					"Number of Children should be >= 2");
		this.nChildren = nChildren;
	}

	public DHeap(int size) {
		this(size, 5);
	}

	public DHeap(int[] arr) {
		this(arr, 5);
	}

	public DHeap(int[] arr, int nChildren) {
		this.heap = arr;
		this.heapSize = arr.length;
		if (nChildren < 2)
			throw new IllegalArgumentException(
					"Number of Children should be >= 2");
		this.nChildren = nChildren;
		for (int i = arr.length / 2; i >= 0; i--)
			heapify(i);
	}

	public boolean isEmpty() {
		return heapSize == 0;
	}

	public void push(int key) {
		if (heapSize < heap.length) {
			int cur = heapSize++;
			while (cur > 0 && cmp(heap[cur / nChildren], key)) {
				heap[cur] = heap[cur / nChildren];
				cur >>= 1;
			}
			this.heap[cur] = key;
		}
	}

	public int pop() {
		int hold = heap[0];
		heap[0] = heap[--heapSize];
		heapify(0);
		return hold;
	}

	private void heapify(int root) {
		do {
			int mxi = root;

			for (int i = 1, f = nChildren * root; i <= nChildren; i++) {
				int c = f + i;
				if (c < heapSize && cmp(heap[mxi], heap[c]))
					mxi = c;
			}
			if (mxi == root)
				break;
			swap(mxi, root);
			root = mxi;
		} while (true);
	}

	private void swap(int i, int j) {
		int t = heap[i];
		heap[i] = heap[j];
		heap[j] = t;
	}

	public int size() {
		return heapSize;
	}

	public boolean cmp(int first, int second) {
		// return first > second; //minHeap
		return first < second; // maxHeap
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < heapSize; i++) {
			if (sb.length() > 0)
				sb.append(',');
			sb.append(heap[i]);
		}
		return String.format("[%s]", sb.toString());
	}

	public static void main(String[] args) {
		int N = 10;
		int[] arr = new int[N];
		DHeap h = new DHeap(N, 5);
		for (int i = 0; i < N; i++) {
			arr[i] = (int) (Math.random() * 1000);
			h.push(arr[i]);
			System.out.println(h);
		}
		while (!h.isEmpty())
			System.out.println(h.pop());
	}
}
