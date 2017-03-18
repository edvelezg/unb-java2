package bitmanipulation;

public class BitClass {

    private static String printInBinary(String num) {
        System.out.println(num);
        int intPart = Integer.parseInt(num.substring(0, num.indexOf('.')));
        double decPart = Double.parseDouble(num.substring(num.indexOf('.'), num.length()));

        // System.out.println("int " + intPart);
        StringBuffer buf = new StringBuffer();
        while (intPart > 0) {
            int r = intPart % 2;
            buf.append(r);
            intPart >>= 1;
            System.out.println(intPart + " R " + r);
        }

        buf.append('.');

        int size = buf.length();
        // System.out.println("dec " + decPart);
        while (decPart > 0) {
            double r = decPart * 2;
            // System.out.println(r);

            if (buf.length() > size + 32) {
                // System.out.println("Ran out of precision");
                return "";
            }

            if (r >= 1) {
                buf.append(1);
                decPart = r - 1;
            } else {
                buf.append(0);
                decPart = r - 0;
            }
        }

        return buf.toString();
    }

    // public static String toBinaryString(int x) {
    //
    // byte[] b = new byte[32]; // 32 bits per int
    // int pos = 0;
    // do {
    // x = x >> 1; // /2
    // b[31 - pos++] = (byte) (x % 2);
    // } while (x > 0);
    //
    // return Arrays.toString(b);
    // }

    // You are given two 32-bit numbers, N and M, and two bit positions, i and
    // j. Write a
    // method to set all bits between i and j in N equal to M (e.g., M becomes a
    // substring of
    // N located at i and starting at j).
    public static int updateBits(int n, int m, int i, int j) {
        System.out.println("updateBits(n=" + n + "m=" + m + "i=" + i + "j=" + j + ")");
        int max = ~0; /* All 1’s */
        // System.out.println("int max = ~0; /* All 1’s */");
        // System.out.println(String.format("%32s",
        // Integer.toBinaryString(max)).replace(' ', '0'));

        // 1’s through position j, then 0’s
        // System.out.println("(1 << j)");
        // System.out.println(String.format("%32s", Integer.toBinaryString((1 <<
        // j))).replace(' ', '0'));
        // System.out.println("(1 << j) - 1)");
        // System.out.println(String.format("%32s", Integer.toBinaryString((1 <<
        // j) - 1)).replace(' ', '0'));
        int left = max - ((1 << j) - 1);
        // System.out.println("int left = max - ((1 << j) - 1);");
        // System.out.println(String.format("%32s",
        // Integer.toBinaryString(left)).replace(' ', '0'));

        // 1’s after position i
        int right = ((1 << i) - 1);
        // System.out.println("int right = ((1 << i) - 1);");
        // System.out.println(String.format("%32s",
        // Integer.toBinaryString(right)).replace(' ', '0'));

        // 1’s, with 0s between i and j
        int mask = left | right;
        // System.out.println("int mask = left | right;");
        // System.out.println(String.format("%32s",
        // Integer.toBinaryString(mask)).replace(' ', '0'));
        //
        // // Clear i through j, then put m in there
        // System.out.println("(n & mask)");
        // System.out.println(String.format("%32s",
        // Integer.toBinaryString(n&mask)).replace(' ', '0'));
        // System.out.println("(m << i);");
        // System.out.println(String.format("%32s", Integer.toBinaryString(m <<
        // i)).replace(' ', '0'));
        // System.out.println("(n & mask) | (m << i);");
        int ret = (n & mask) | (m << i);
        System.out.println(String.format("%32s", Integer.toBinaryString(ret)).replace(' ', '0'));
        return ret;
    }

    public static boolean GetBit(int n, int index) {
        System.out.println("GetBit(n=" + n + "index=" + index + ")");
        // System.out.println("(1 << index)");
        // System.out.println(String.format("%32s", Integer.toBinaryString(1 <<
        // index)).replace(' ', '0'));
        // System.out.println("(n & (1 << index))");
        // System.out.println(String.format("%32s", Integer.toBinaryString(n &
        // (1 << index))).replace(' ', '0'));
        // System.out.println("((n & (1 << index)) > 0)");
        return ((n & (1 << index)) > 0);
    }

    public static int SetBit(int n, int index, boolean b) {
        System.out.println("SetBit(n=" + n + ", " + "index=" + index + ", " + "b=" + b + ")");
        if (b) {
            // System.out.println("if (b) {");
            // System.out.println("(1 << index)");
            // System.out.println(String.format("%32s", Integer.toBinaryString(1
            // << index)).replace(' ', '0'));
            // System.out.println("n | (1 << index);");
            // System.out.println(String.format("%32s", Integer.toBinaryString(n
            // | (1 << index))).replace(' ', '0'));
            return n | (1 << index);
        } else {
            int mask = ~(1 << index);
            // System.out.println(String.format("%32s",
            // Integer.toBinaryString(mask)).replace(' ', '0'));
            // System.out.println("n & ~(1 << index)");
            // System.out.println(String.format("%32s", Integer.toBinaryString(n
            // & mask)).replace(' ', '0'));
            return n & mask;
        }
    }

    public static int GetNext_NP(int n) {
        System.out.println("GetNext_NP(n=" + n + ")");
        System.out.println(String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0'));
        if (n <= 0)
            return -1;

        int index = 0;
        int countOnes = 0;

        // Find first one.
        System.out.println("=== Find first one.");
        while (!GetBit(n, index))
            index++;

        // Turn on next zero.
        System.out.println("=== Turn on next zero.");
        while (GetBit(n, index)) {
            index++;
            countOnes++;
        }
        n = SetBit(n, index, true);

        // Turn off previous one
        System.out.println("=== Turn off previous one");
        index--;
        n = SetBit(n, index, false);
        countOnes--;

        System.out.println("=== Count ones: " + countOnes);

        // Set zeros
        System.out.println("=== Set zeros");
        for (int i = index - 1; i >= countOnes; i--) {
            n = SetBit(n, i, false);
        }

        // Set ones
        System.out.println("=== Set ones");
        for (int i = countOnes - 1; i >= 0; i--) {
            n = SetBit(n, i, true);
        }

        System.out.println(String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0'));
        return n;
    }

    public static int GetPrevious_NP(int n) {
        System.out.println("GetPrevious_NP(n=" + n + ")");
        if (n <= 0)
            return -1; // Error

        int index = 0;
        int countZeros = 0;

        // Find first zero.
        while (GetBit(n, index))
            index++;

        // Turn on next 1.
        while (!GetBit(n, index)) {
            index++;
            countZeros++;
        }
        n = SetBit(n, index, false);

        // Turn on previous zero
        index--;
        n = SetBit(n, index, true);
        countZeros--;

        // 01111111111111111111111111111111
        // Set ones
        for (int i = index - 1; i >= countZeros; i--) {
            n = SetBit(n, i, true);
        }

        // Set zeros
        for (int i = countZeros - 1; i >= 0; i--) {
            n = SetBit(n, i, false);
        }

        return n;
    }

    public static void main(String[] args) {
        // prog1();
        // prog2();

        // int n = 1732; // 11011000100
        int n = 2147483647; // ‭011111111111‬
        GetNext_NP(n);

        // GetPrevious_NP(n);
    }

    @SuppressWarnings("unused")
    private static void prog2() {
        String num = "55.828125";
        System.out.println(printInBinary(num));
    }

    @SuppressWarnings("unused")
    private static void prog1() {
        int n = 1024;
        System.out.println("int n = 1024;");
        System.out.println(String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0'));
        int m = 21;
        System.out.println("int m = 21;");
        System.out.println(String.format("%32s", Integer.toBinaryString(m)).replace(' ', '0'));
        int x = updateBits(n, m, 2, 6);
    }
}
