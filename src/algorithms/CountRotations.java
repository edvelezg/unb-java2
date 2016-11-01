package algorithms;

class CountRotations {

    public CountRotations() {
    }

    public static void main(String args[]) {
        int num = 7;
        int[] a = new int[num];
        for ( int i = 0; i < num; i++ ) {
            int k = 0;
            for ( int j = i; j < num; j++ ) {
                System.out.print(String.format("%d ", j));
                a[k] = j;
                ++k;
            }

            for ( int l = 0; k < num; l++, k++) {
                System.out.print(String.format("%d ", l));
                a[k] = l;
            }
            System.out.println();
            System.out.println(countRots(a));
        }

//        System.out.println("rotation count: " + countRots(a, a.length));
    }

    public static int countRots(int[] a) {

        int l = 0;
        int h = a.length - 1;

        if ( a[l] < a[h] ) return 0;

        while ( l <= h ) {
            int m = (l + h) / 2;
            if (isPivot(a, m)) return m;
            if (isPivot(a, l)) return l;
            if (isPivot(a, h)) return h;
            if ( a[m] <= a[h] ) h = m-1;
            else l = m+1;
        }

        return 0;

    }

	private static boolean isPivot(int[] a, int m) {
		int p = (m - 1 + a.length) % a.length;
		int f = (m + 1) % a.length;
//		System.out.println(p);
		return (a[m] < a[p] && a[m] < a[f]);
	}
}
