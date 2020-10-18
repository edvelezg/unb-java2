package amazon.interview;

public class GCDInArray {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public int generalizedGCD(int num, int[] arr) {
        int result = 0;
        for (int element : arr) {
            result = gcd(result, element);

            if (result == 1) {
                return 1;
            }
        }

        return result;

    }

    // Function to return gcd of a and b
    private int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }


    public static void main(String[] args) {
        int arr[] = {2, 4, 6, 8, 16};
        int n = arr.length;
        System.out.println(new GCDInArray().generalizedGCD(n, arr));
    }

}
