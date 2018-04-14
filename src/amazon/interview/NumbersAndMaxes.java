package amazon.interview;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

class MyMax implements Comparable<MyMax> {
	private int max;
	private int pos;
	
	public MyMax(int max, int pos) {
		this.max = max;
		this.pos = pos;
	}
	
	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	@Override
	public int compareTo(MyMax arg0) {
		return this.getMax() - arg0.getMax();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("(%d, %d)", max, pos);
	}

}

public class NumbersAndMaxes {

	static int[] countsUsingMaxObjs(int[] nums, int[] maxes) {
		Arrays.sort(nums);
		System.out.println(Arrays.toString(nums));
		int[] counts = new int[maxes.length];

		MyMax[] maxObjs = new MyMax[maxes.length];
		for (int i = 0; i < counts.length; i++) {
			maxObjs[i] = new MyMax(maxes[i], i);
		}

		Arrays.sort(maxObjs);
		System.out.println(Arrays.toString(maxObjs));
		
		int j = 0;
		for (MyMax myMax : maxObjs) {
			int curMax = myMax.getMax();
			while (j < nums.length) {
				if (nums[j] <= curMax) {
					counts[myMax.getPos()] = j + 1;
				} else {
					break;
				}
				++j;
			}
		}

		return counts;
	}

	static int[] countsUsingHashMap(int[] nums, int[] maxes) {
		Arrays.sort(nums);
		System.out.println(Arrays.toString(nums));
		int[] counts = new int[maxes.length];

		// unsorted map of maxes with corresponding positions
		Map<Integer, Integer> unsortedMap = new HashMap<>();
		for (int i = 0; i < maxes.length; i++) {
			unsortedMap.put(i, maxes[i]);
		}

		Set<Entry<Integer, Integer>> entrySet = unsortedMap.entrySet();

		// Create a linked list of the entries in the map ordering the maxes in
		// ascending order
		LinkedList<Entry<Integer, Integer>> maxList = new LinkedList<Entry<Integer, Integer>>(entrySet);
		Collections.sort(maxList, new Comparator<Entry<Integer, Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> arg0, Entry<Integer, Integer> arg1) {
				return arg0.getValue().compareTo(arg1.getValue());
			}
		});

		int j = 0;
		for (Entry<Integer, Integer> entry : maxList) {
			int curMax = entry.getValue();
			while (j < nums.length) {
				if (nums[j] <= curMax) {
					counts[entry.getKey()] = j + 1;
				} else {
					break;
				}
				++j;
			}
		}

		return counts;
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 5, 4, 3, 2, 1, 7, 8, 9 };
		int[] maxes = new int[] { 3, 2, 5 };
		int[] counts = countsUsingMaxObjs(nums, maxes);

		System.out.println(Arrays.toString(counts));
	}
}
