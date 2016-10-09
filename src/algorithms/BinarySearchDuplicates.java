package algorithms;

class BinarySearchDuplicates {

    public BinarySearchDuplicates() { 
    }

    public static void main (String args[]) {
        int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 10, 10, 11, 11, 12, 12};
        // int[] arr = new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9};

        System.out.println("count: " + search(arr, arr.length, 9)); 
    }

    public static int search(int[] arr, int n, int x) { 
        int h = n - 1;
        int l = 0;
        while (l <= h) {
            int m = (h + l)/2;
            if (x == arr[m]) {
                int count = 1;
                int last = m;
                while ((m+1) < arr.length && arr[++m] == x) {
                    count += 1;
                    last = m;
                }
                System.out.println("last: " + last); 
                m = m - count; // reset to old value of m
                int first = m;
                while ((m-1) >= 0 && arr[--m] == x) {
                    count += 1;
                    first = m;
                }
                System.out.println("first: " + first); 
                return count; 
            } else if (x > arr[m]) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return 0;
    }

}

