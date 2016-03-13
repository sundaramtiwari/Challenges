package challenges;

import java.util.Arrays;

public class FindTheKthLargest {

	public static void main(String[] args) {
		int arr[] = {12, 3, 5, 7, 19, 6};
		int k = 4;
		System.out.println("K'th smallest element is " + find(arr, k));
	}

	public static int find(int[] sourceArray, int K) {
		// Build a heap of first k elements: O(k) time
		MaxHeap mh = new MaxHeap(sourceArray, K);
		System.out.println(mh);
		// Process remaining n-k elements. If current element is
		// smaller than root, replace root with current element
		for (int i = K; i < sourceArray.length; i++) {
			if (sourceArray[i] < mh.getMax()) {
				mh.replaceMax(sourceArray[i]);
				System.out.println(mh);
			}
		}
		// Return root
		return mh.getMax();
	}
}

class MaxHeap {
	private int capacity; // maximum possible size of max heap
	private int heap_size; // Current number of elements in max heap
	private int[] array;

	public MaxHeap(int a[], int size) {
		heap_size = size;
		array = a; // store address of array
		int i = (heap_size - 1) / 2;
		while (i >= 0) {
			maxHeapify(i);
			i--;
		}
	}

	// Method to remove maximum element (or root) from max heap
	public int extractMax() {
		int root = 0;
		if (heap_size == 0) {
			// Store the maximum vakue.
			root = array[0];
		}

		// If there are more than 1 items, move the last item to root
		// and call heapify.
		if (heap_size > 1) {
			array[0] = array[heap_size - 1];
			maxHeapify(0);
		}
		heap_size--;

		return root;
	}

	// A recursive method to heapify a subtree with root at given index
	// This method assumes that the subtrees are already heapified
	public void maxHeapify(int i) {
		int l = left(i);
		int r = right(i);
		int largest = i;
		if (l < heap_size && array[l] > array[i])
			largest = l;
		if (r < heap_size && array[r] > array[largest])
			largest = r;
		if (largest != i) {
			swap(array[i], array[largest]);
			maxHeapify(largest);
		}
	}

	// A utility function to swap two elements
	protected void swap(int x, int y) {
		int temp = x;
		x = y;
		y = temp;
	}

	int parent(int i) {
		return (i - 1) / 2;
	}

	int left(int i) {
		return (2 * i + 1);
	}

	int right(int i) {
		return (2 * i + 2);
	}

	int getMax() {
		return array[0];
	} // Returns maximum

	// to replace root with new node x and heapify() new root
	void replaceMax(int x) {
		array[0] = x;
		maxHeapify(0);
	}

	@Override
	public String toString() {
		return "MaxHeap [capacity=" + capacity + ", heap_size=" + heap_size + ", array=" + Arrays.toString(array) + "]";
	}
	
}
