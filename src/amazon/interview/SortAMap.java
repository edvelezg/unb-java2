package amazon.interview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SortAMap {
	public static void main(String[] args) {
		
		Map<String, Integer> unsortedMap = new HashMap<>();
		unsortedMap.put("apple", 30);
		unsortedMap.put("pear", 50);
		unsortedMap.put("orange", 20);
		unsortedMap.put("berry", 60);
		
		Set<Entry<String, Integer>> entrySet = unsortedMap.entrySet();
		LinkedList<Entry<String, Integer>> entryList = new LinkedList<Entry<String, Integer>>(entrySet);

		// Using Anonymous class
//		Collections.sort(entryList, new Comparator<Entry<String, Integer>>() {
//			@Override
//			public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
//				return arg1.getValue().compareTo(arg0.getValue());
//			}
//		});

		// Using lambda
		Collections.sort(entryList, (arg0, arg1) -> arg1.getValue().compareTo(arg0.getValue()));


		System.out.println(entryList);
		
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : entryList) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		
		System.out.println(sortedMap);
        ArrayList<Integer> counts = new ArrayList<>(5);
        
	}
}
