package other;

public class StringLib {

    static void permutation(String str, int length, StringBuffer output, boolean[] used, int position) {

        if (position == length) {
            System.out.println(output.toString());
            return;
        } else {
            for (int i = 0; i < length; i++) {
                /* skip already used characters */
                if (used[i])
                    continue;

                /* add fixed character to output, and mark it as used */
                output.append(str.charAt(i));
                used[i] = true;

                /* permute over remaining characters starting at position+1 */
                System.out.println(String.format("%6s%6s%5d%6s%5d", "Push", str, length, output, position + 1));
                permutation(str, length, output, used, position + 1);

                /* remove fixed character from output, and unmark it */
                output.deleteCharAt(output.length() - 1);
                used[i] = false;
                System.out.println(String.format("%6s%6s%5d%6s%5d", "Pop", str, length, output, position + 1));

            }
        }
    }

    public static void permute(String str) {
        int length = str.length();
        boolean[] used = new boolean[length];

        StringBuffer output = new StringBuffer(length);

        permutation(str, length, output, used, 0);
    }

    public static void main(String[] args) {
        permute("abc");
    }
}