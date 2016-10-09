package algorithms;

class CountRotations {
    
    public CountRotations() { 
        
    }

    public static void main (String args[]) {
        // int [] a = new int[] {6, 0, 1, 2, 3, 4, 5}; // 1 rot
        // int [] a = new int[] {5, 6, 0, 1, 2, 3, 4}; // 2 rot
        // int [] a = new int[] {4, 5, 6, 0, 1, 2, 3}; // 1 rot
        int [] a = new int[] {3, 4, 5, 6, 0, 1, 2}; // 1 rot
        // int [] a = new int[] {1, 2, 3, 4, 5, 6, 0}; // 6 rot
        // int [] a = new int[] {0, 1, 2, 3, 4, 5, 6};
        System.out.println("rot cnt: " + countRots(a, a.length)); 
    }

    public static int countRots(int[] a, int n) { 

        if (a[0] <= a[n-1]) return 0;

        int hi = n-1;
        int lo = 0;

        while (lo <= hi) {
            int m = (hi + lo) / 2;
            int nxt = (m + 1) % n;
            int prv = (m - 1) % n;
            if (a[m] <= a[nxt] && a[m] <= a[prv]) return m;
            else if (a[m] <= a[hi]) hi = m - 1;
            else lo = m + 1;
        }

        return -1;
    }
}
