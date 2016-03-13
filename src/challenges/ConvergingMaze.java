package challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * @author Sundaram
 *
 */
public class ConvergingMaze {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		/*Scanner sc = new Scanner(System.in);
		int size = Integer.parseInt(sc.nextLine());
		String[] mazeArr = sc.nextLine().split(" ");
		int[] edges = Arrays.stream(mazeArr).mapToInt(Integer::parseInt).toArray();
		int cell1 = sc.nextInt();
		int cell2 = sc.nextInt();*/

		int size = 23;
		int[] edges = {4 ,4 ,1 ,4 ,13 ,8 ,8 ,8 ,0 ,8 ,14 ,9 ,15 ,11 ,-1 ,10 ,15 ,22 ,22 ,22 ,22 ,22 ,21};
		int cell1 = 9;
		int cell2 = 2;

		int maxIncomingEdges = maxIncomingEdges(edges, size);
		System.out.println("MaxIncomingEdges: " + maxIncomingEdges);

		int largestCycle = largestCycle(edges, size);
		System.out.println("LargestCycle: " + largestCycle);
		
		int nearestMeetingCell = getNearestMeetingCell (edges, size, cell1, cell2);
		System.out.println("NearestMeetingCell: " + nearestMeetingCell);

	}

	private static int getNearestMeetingCell(int[] edges, int size, int cell1, int cell2) {

		Map<Integer, Integer> edgeMap = getEdgeMapFromArray(edges, size);
		
		List<Integer> cellList1 = new ArrayList<Integer>();
		List<Integer> cellList2 = new ArrayList<Integer>();
		
		traverseCell(edgeMap, cellList1, cell1);
		traverseCell(edgeMap, cellList2, cell2);
		
		List<Integer> cellList = new ArrayList<Integer>(cellList1);
		cellList.retainAll(cellList2);

		int nearestCell = -1, distance = Integer.MAX_VALUE;

		for (int cell : cellList) {
			int disList1 = cellList1.indexOf(cell);
			int disList2 = cellList2.indexOf(cell);
			
			if (distance > disList1 + disList2) {
				nearestCell = cell;
				distance = disList1 + disList2;
			}
		}

		return nearestCell;
	}

	private static void traverseCell(Map<Integer, Integer> edgeMap, List<Integer> cellList, int cell) {
		cellList.add(cell);
		
		while (true) {
			cell = edgeMap.get(cell);
			if (!cellList.contains(cell)) {
				cellList.add(cell);
			}
			else
				break;
		}
	}

	private static int largestCycle(int[] edges, int size) {
		Map<Integer, Integer> edgeMap = getEdgeMapFromArray(edges, size);

		// Loop through mazeMap and check for loops
		int maxLoopSize = getLargestCycle(edgeMap, edges);

		return maxLoopSize;
	}

	private static Map<Integer, Integer> getEdgeMapFromArray(int[] edges, int size) {

		Map<Integer, Integer> mazeMap = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < size; i++) 
			mazeMap.put(i, edges[i]);

		return mazeMap;
	}

	private static int getLargestCycle(Map<Integer, Integer> edgeMap, int[] maze) {
		List<Integer> maxList = new ArrayList<Integer>();

		for (Entry<Integer, Integer> entry : edgeMap.entrySet()) {
			List<Integer> list = new ArrayList<Integer>();
			list.add(entry.getKey());

			// Navigate through values, adding data to a list with duplicate check to find loop in maze
			// Used list DS just to be able to scale up this solution to print largest cycle elements in order if required.
			boolean isCycle = hasCycle(edgeMap, entry.getValue(), list);

			if (isCycle && list.size() > maxList.size()) {
				maxList = list;
			}
		}

		return maxList.size() != 0 ? maxList.size() : -1;
	}

	private static boolean hasCycle(Map<Integer, Integer> edgeMap, int val, List<Integer> list) {
		boolean isCycle = false;
		while (true) {
			if (list.contains(val)) {
				if (list.get(0).equals(val))
					isCycle = true;
				break;
			} else {
				list.add(val);
			}
			if (edgeMap.get(val) != null)
				val = edgeMap.get(val);
			else
				break;
		}
		return isCycle;
	}

	private static int maxIncomingEdges(int[] edges, int size) {
		MazeMap<Integer, Integer> map = new MazeMap<Integer, Integer>();

		for (int i = 0; i < size; i++) {
			int key = edges[i];
			if (map.containsKey(key))
				map.put(key, map.get(key) + 1);
			else
				map.put(key, 1);
		}

		return map.getMax();
	}

}

class MazeMap<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = -2262849478474889017L;

	private int max;

	@Override
	public V put(K key, V value) {
		max = (int) value > max ? (int) value : max;
		return super.put(key, value);
	}

	public int getMax() {
		return max;
	}
}
