package other;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class StringsAndArrays {

    public static void main(String[] args) {
        String str = "asbestas";
        // findFirstNonRep(str);

        System.out.println(firstNoRepeat(str));

        String str1 = "the world is crazy";
        String str2 = "rlhe";
        System.out.println(removeChars(str1, str2));

        String str3 = "Do or do not, there is no try.";
        rvrsSent(str3);

    }

    private static void findFirstNonRep(String str) {
        Set<Character> set = new HashSet<Character>();
        Queue<Character> queue = new ArrayDeque<>();

        for (int i = 0; i < str.length(); i++) {
            if (!set.contains(str.charAt(i))) {
                set.add(str.charAt(i));
                queue.add(str.charAt(i));
            } else {
                queue.remove(str.charAt(i));
            }
        }

        for (Character character : queue) {
            System.out.println(character);
        }
    }

    public static Character firstNonRepeated(String str) {
        HashMap<Character, Integer> charHash = new HashMap<Character, Integer>();
        int len = str.length();

        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (charHash.containsKey(c)) {
                charHash.put(c, charHash.get(c) + 1);
            } else {
                charHash.put(c, 1);
            }
        }

        char c;
        for (int i = 0; i < len; i++) {
            c = str.charAt(i);
            if (charHash.get(c) == 1)
                return c;
        }
        return null;
    }

    public static String firstNoRepeat(String str) {
        int len = str.length();
        HashMap<Integer, Object> char_set = new HashMap<Integer, Object>();
        Object seenOnce = new Object(), seenMultipleTimes = new Object();
        Object seen;
        for (int i = 0; i < len;) {
            final int cp = str.codePointAt(i);
            i += Character.charCount(cp);
            seen = char_set.get(cp);
            if (seen == null) {
                char_set.put(cp, seenOnce);
            } else {
                if (seen.equals(seenOnce)) {
                    char_set.put(cp, seenMultipleTimes);
                }
            }
        }

        for (int i = 0; i < len; i++) {
            final int cp = str.codePointAt(i);
            if (char_set.get(cp).equals(seenOnce)) {
                return new String(Character.toChars(cp));
            }
        }
        return null;
    }

    public static String removeChars(String str, String str2) {
        if (str == null || str2 == null) {
            return str;
        }

        Set<Character> hashSet = new HashSet<Character>();
        for (int i = 0; i < str2.length(); i++) {
            hashSet.add(str2.charAt(i));
        }

        char[] array = str.toCharArray();
        int tail = 0;
        for (int i = 0; i < array.length; i++) {
            if (!hashSet.contains(array[i])) {
                array[tail] = array[i];
                ++tail;
            }
        }

        if (tail < str.length()) {
            array[tail] = 0;
        }

        return new String(array, 0, tail);
    }

    public static void rvrsSent(String sentence) {
        String[] array = sentence.split(" ");

        StringBuffer str = new StringBuffer();
        for (int i = array.length - 1; i >= 0; --i) {

            if (i > 0) {
                str.append(array[i] + " ");
            } else {
                str.append(array[i]);
            }
        }
        System.out.println(str.toString());

        int len = sentence.length();

        char[] arr = sentence.toCharArray();
        int i = 0, j = len - 1;
        while (j > i) {
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }

        System.out.println(new String(arr, 0, len));
    }

}
