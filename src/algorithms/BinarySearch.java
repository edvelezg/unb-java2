// Testing the git bundle in textmate.
package algorithms;

public class BinarySearch {
    public static int search(int[] a, int x) {
        int pos = -1;
        int h = a.length-1;
        int l = 0;
        while (l <= h)
        {
            int m = (l + h)/2;
            if (x == a[m]) {
                pos = m;
                return pos;
            } else if (x < a[m]) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return pos;
    }
    
    public static void main(String[] args) {
        int[] array = new int[] {0,1,2,3,4,5,6,7,8};
        System.out.println(BinarySearch.search(array, 8));
    }
}
