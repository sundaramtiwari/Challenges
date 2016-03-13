package challenges;


public class FindTheKthLargest2 {

	public static void main(String[] args) {
		int arr[] = {12, 3, 5, 7, 19};
		int k = 3;
		System.out.println("K'th smallest element is " + find(arr, k));
	}

	public static int find(int[] sourceArray, int k) {
		return kthSmallest(sourceArray, 0, sourceArray.length-1, k);
	}
	
	static int kthSmallest(int arr[], int l, int r, int k)
	{
	    // If k is smaller than number of elements in array
	    if (k > 0 && k <= r - l + 1)
	    {
	        // Partition the array around last element and get
	        // position of pivot element in sorted array
	        int pos = partition(arr, l, r);
	 
	        // If position is same as k
	        if (pos-l == k-1)
	            return arr[pos];
	        if (pos-l > k-1)  // If position is more, recur for left subarray
	            return kthSmallest(arr, l, pos-1, k);
	 
	        // Else recur for right subarray
	        return kthSmallest(arr, pos+1, r, k-pos+l-1);
	    }
	 
	    // If k is more than number of elements in array
	    return Integer.MAX_VALUE;
	}
	
	protected static void swap(int x, int y) {
		int temp = x;
		x = y;
		y = temp;
	}
	 
	// Standard partition process of QuickSort().  It considers the last
	// element as pivot and moves all smaller element to left of it
	// and greater elements to right
	static int partition(int arr[], int l, int r)
	{
	    int x = arr[r], i = l;
	    for (int j = l; j <= r - 1; j++)
	    {
	        if (arr[j] <= x)
	        {
	            swap(arr[i], arr[j]);
	            i++;
	        }
	    }
	    swap(arr[i], arr[r]);
	    return i;
	}
}