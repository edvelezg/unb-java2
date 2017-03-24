package data.structures;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {

    private static <T extends Comparable<T>> String printArray(ArrayList<T> array, int p, int k) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < array.size(); i++) {
            if (i > 0) buf.append(", ");
            
            if (i == p && i == k) {
                buf.append("[[");
                buf.append(array.get(i));
                buf.append("]]");
            } else if (i == p || i == k) {
                buf.append("[");
                buf.append(array.get(i));
                buf.append("]");
            } else {
                buf.append(array.get(i));
            }
        }
        return buf.toString();
    }

    private ArrayList<T> items;
    private static Scanner sc;

    public Heap() {
        items = new ArrayList<T>();
    }

    private void siftUp() {
//        System.out.println("siftUp()");
        int k = items.size() - 1;
        while (k > 0) {
            int p = (k - 1) / 2;
            System.out.println(printArray(items, p, k));
            T item = items.get(k);
            T parent = items.get(p);
            if (item.compareTo(parent) > 0) {
                // swap
//                System.out.println("swap");
                items.set(k, parent);
                items.set(p, item);
                // move up one level
                k = p;
            } else {
                break;
            }
        }
    }

    public void insert(T item) {
        items.add(item);
        siftUp();
    }

    private void siftDown() {
        int k = 0;
        int l = 2 * k + 1;
        while (l < items.size()) {
            System.out.println(printArray(items, k, l));
            int max = l, r = l + 1;
            if (r < items.size()) { // there is a right child
                if (items.get(r).compareTo(items.get(l)) > 0) {
                    max++;
                }
            }
            if (items.get(k).compareTo(items.get(max)) < 0) {
                // switch
                T temp = items.get(k);
                items.set(k, items.get(max));
                items.set(max, temp);
                k = max;
                l = 2 * k + 1;
            } else {
                break;
            }
        }
    }

    public T delete()
        throws NoSuchElementException {
        if (items.size() == 0) {
            throw new NoSuchElementException();
        }
        if (items.size() == 1) {
            return items.remove(0);
        }
        T hold = items.get(0);
        items.set(0, items.remove(items.size() - 1));
        siftDown();
        return hold;
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();

    }

    public String toString() {
        return items.toString();
    }
    
    public static void main(String[] args) {
        Heap<Integer> hp = new Heap<Integer>();
        sc = new Scanner("1\n2\n3\n4\n5\n6\n7\n8\n9\ndone");
        String line = sc.next();
        while (!line.equals("done")) {
            hp.insert(Integer.parseInt(line));
            System.out.println(hp);
            System.out.println();
            line = sc.next();
        }
        
        while (!hp.isEmpty()) {
            int max = hp.delete();
            System.out.println(max + " " + hp);
            System.out.println();
        }
    }

}
