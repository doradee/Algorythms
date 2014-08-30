package org.mechaevil.algos.ds;

// Default Min heap (override cmp() implementation)
public class BinaryHeap {

	private final int[] heap;
	private int heapSize;

	public BinaryHeap(int size) {
		heap = new int[size];
		heapSize = 0;
	}

	public BinaryHeap(int[] arr) {
		heap = arr;
		heapSize = arr.length;
		for (int i = arr.length / 2; i >= 0; i--)
			heapify(i);
	}

	public boolean isEmpty() {
		return heapSize == 0;
	}

	public void push(int key) {
		if (heapSize < heap.length) {
			int cur = heapSize++;
			while (cur > 0 && cmp(heap[cur >> 1], key)) {
				heap[cur] = heap[cur >> 1];
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
			// left child
			int c = 2 * root + 1;
			if (c < heapSize && cmp(heap[mxi], heap[c]))
				mxi = c;
			c++;
			// right child
			if (c < heapSize && cmp(heap[mxi], heap[c]))
				mxi = c;
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

	public static void main(String[] args) {
		int N = 100;
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = (int) (Math.random() * 1000);
		BinaryHeap h = new BinaryHeap(arr);
		while (!h.isEmpty())
			System.out.println(h.pop());
	}
}
