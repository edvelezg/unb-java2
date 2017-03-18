package google.interview;

import java.util.Arrays;

public class Practice {
	public static void main(String[] args) {
		String str = "abracadabra";
		String removeDups = removeDups(str);
		System.out.println(removeDups);
	}

	public static String removeDups(String str) {
		char[] chArr = str.toCharArray();
		int t = 1;
		for (int i = 0; i < chArr.length; i++) {
			for (int j = i+1; j < chArr.length; j++) {
				if (chArr[i] != chArr[j]) {
					chArr[t] = chArr[j];
					System.out.println(t);
				}

			}
		}

		// int i = 0;
		// int tail = 1;
		// while (i < chArr.length) {
		// for (int j = 1; j < chArr.length; ++j) {
		// if (chArr[i] != chArr[j]) {
		// chArr[tail] = chArr[j];
		// tail++;
		// }
		// }
		// ++i;
		// }
		return new String(Arrays.copyOfRange(chArr, 0, t));
	}
}
