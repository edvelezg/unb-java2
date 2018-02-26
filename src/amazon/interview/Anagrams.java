package amazon.interview;

import java.util.HashMap;
import java.util.Map;

public class Anagrams {

	/**
	 * function that determines if two strings are anagrams
	 * @param str1 the first string
	 * @param str2 the second string
	 * @return array of differences 
	 */
	static int areAnagrams(String str1, String str2) {
		Map<Character, Integer> histogram = new HashMap<>();
		
		if (str1.length() != str2.length()) {
			return -1;
		}

		/* build the histogram */
		for (int i = 0; i < str1.length(); i++) {
			char ch = str1.charAt(i);
			int v = histogram.getOrDefault(ch, 0);
			histogram.put(ch, v+1);
		}
		
		// decrease the count each time we find a character in the histogram.
		int count = str1.length();
		for (int i = 0; i < str2.length(); i++) {
			char ch = str2.charAt(i);
			int v = histogram.getOrDefault(ch, 0);
			if (v == 0) {
				continue;
			}
			count--;
			histogram.put(ch, v-1);
		}
		return count;
	}

	public static int[] getMinimumDifference(String[] a, String[] b) {
		
		if (a.length != b.length) {
			return null;
		}
		
		int[] res = new int[a.length-1]; 
		
		for (int i = 0; i < res.length; i++) {
			res[i] = areAnagrams(a[i], b[i]);
		}
		
		return res;

	}
	
	public static void main(String[] args) {
		
		System.out.println(areAnagrams("toe", "ate"));
	}

}
