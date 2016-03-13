package challenges;

import java.util.HashSet;
import java.util.Set;

/**
 * There are two integer arrays A and B,
 * characteristics of Array A :
 * has a million integers. Its sorted in ascending order. can have duplicates 
 * (i.e. the same number can be repeated)
 * characteristics of Array B :
 * has only ten integers its not sorted does not have duplicates 
 * Now write an efficient program which will report how many integers 
 * of Array B are also present in Array A. Use java for syntax purposes only and not any more.
 * @author sundaramtiwari
 *
 */
public class FindSmallnBig {

	public static void main(String[] args) {
		int[] bigArray = {12, 3, 5, 7, 19, 2, 2, 3};
		int[] smallArray = {12, 3, 5};
		int[] common = find(bigArray, smallArray);
		for (int i = 0; i < common.length; i++) {
			System.out.print(common[i] + " ");
		}
	}

	public static int[] find(int[] bigArray, int[] smallArray) {
		Set<Integer> commonSet = new HashSet<Integer>();
		Set<Integer> bigArraySet = new HashSet<Integer>();

		for (int i = 0; i < bigArray.length; i++) {
			bigArraySet.add(bigArray[i]);
		}

		for (int i = 0; i < smallArray.length; i++) {
			if (!bigArraySet.add(smallArray[i])) {
				commonSet.add(smallArray[i]);
			}
		}

		int index = 0;
		int[] commonArr = new int[commonSet.size()];
		for (int num : commonSet) {
			commonArr[index++] = num;
		}

		return commonArr;
	}
}
