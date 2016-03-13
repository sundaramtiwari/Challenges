package challenges;

/**
 * In an unsorted array of N integers ( 10 &lt; N &lt; 1000000 ) ,
 * find the kth largest integer , where  k is always less than 10.
 * @author sundaramtiwari
 *
 */
public class FindTheKthLargest3 {

	public static void main(String[] args) {
		int array[] = {12, 3, 5, 7, 19, 2, 2};
		int k = 3;

		System.out.println("K'th smallest element is " + find(array, k));
	}

	public static int find(int[] sourceArray, int k) {
		// remove duplicates from the array.
		sourceArray = removeDuplicates(sourceArray);
	    // Sort the given array
	    sort(sourceArray);
		return sourceArray[k-1];
	}

	private static void sort(int[] array) {
		for(int i=0;i<array.length;i++)
	    {
	        for(int j=i;j<array.length;j++)
	        {
	            if(array[i]>array[j])
	            {
	            int temp=array[i];
	            array[i]=array[j];
	            array[j]=temp;
	            }

	        }
	    }
	}
	
	public static int[] removeDuplicates(int[] input){
        
        int j = 0;
        int i = 1;
        //return if the array length is less than 2
        if(input.length < 2){
            return input;
        }
        while(i < input.length){
            if(input[i] == input[j]){
                i++;
            }else{
                input[++j] = input[i++];
            }   
        }
        int[] output = new int[j+1];
        for(int k=0; k<output.length; k++){
            output[k] = input[k];
        }
         
        return output;
    }
}