package algorithms.sorting;

public class Quicksort {

    public static void main(String args[]) {
        Integer[] array = new Integer[] { 5, 1, 4, 7, 9, 2, 8, 3, 6};
        sort(array);
    }
    
    private static <T extends Comparable<T>> String printArray(T[] array, int left, int right, int lo) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) buf.append(", ");
            
            if (i == lo) buf.append(String.format("(%d)", array[lo]));
            else if (i == left && i == right) {
                buf.append("[[");
                buf.append(array[i]);
                buf.append("]]");
            } else if (i == left || i == right) {
                buf.append("[");
                buf.append(array[i]);
                buf.append("]");
            } else {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    private static <T extends Comparable<T>> int splitList(T[] list, int lo, int hi) {
//        System.out.println("splitList(list=" + Arrays.toString(list)+" lo="+lo+" hi="+hi+") pivot: " + list[lo]);
        T pivot = list[lo]; 
        int left = lo + 1;
        int right = hi;

        while (true) {
            while (left <= right) {
                if (list[left].compareTo(pivot) <= 0) {
                    left++;
                    System.out.println(printArray(list, left, right, lo));
                }
                else break;
            }
            while (right > left) {
                if (list[right].compareTo(pivot) <= 0) break;
                else {
                    right--;
                    System.out.println(printArray(list, left, right, lo));
                }
            }
            if (left >= right) {
                break;
            }
            swap(list, left, right);
            System.out.println(printArray(list, left, right, lo));
        }
        swap(list, lo, left - 1);
        System.out.println(printArray(list, left, right, lo));

        return left - 1;
    }

    private static <T extends Comparable<T>> void swap(T[] list, int left, int right) {
//        System.out.println("SWAP (list="+ Arrays.toString(list)+" left="+left+" right="+right+")");
        if (left != right) {
            T temp = list[left];
            list[left] = list[right];
            list[right] = temp;
        }
    }


    private static <T extends Comparable<T>> void sort(T[] list, int lo, int hi) {
//        System.out.println("sort(list="+ Arrays.toString(list)+" lo="+lo+" hi="+hi+")");
        // base case:
        if (hi - lo <= 0) {
            return;
        }

        int splitPoint = splitList(list, lo, hi);
        sort(list, lo, splitPoint - 1);
        sort(list, splitPoint + 1, hi);
    }

    public static <T extends Comparable<T>> void sort(T[] list) {
        // nothing to sort.
        if (list == null || list.length < 2) return;

        sort(list, 0, list.length - 1);
    }
}
