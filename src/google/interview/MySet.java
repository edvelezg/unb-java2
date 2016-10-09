package google.interview;

import java.util.HashSet;
import java.util.Random;

public class MySet {
    HashSet<Integer> set;
    
    public MySet() {
        set = new  HashSet<Integer>();
    }
    
    public boolean insert(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
        return false;
    }

    public boolean remove(int item) {
        if (set.contains(item)) {
            set.remove(item);
        }
        return false;
    }
    
    public int getRandomElement() {
        if (set.isEmpty()) {
            return -1;
        }
        
        Random rand = new Random();
        int idx = Math.abs(rand.nextInt()) % set.size();
        System.out.println(idx);
        Integer[] array = set.toArray(new Integer[set.size()]);
        return array[idx];
    }
    
    public static void main(String[] args) {
        MySet s = new MySet();
        int elem = 0;
        try {
            elem = s.getRandomElement();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(elem);
        s.insert(5);
        s.insert(8);
        s.insert(7);
        s.insert(4);
        s.insert(3);
        s.insert(2);
        s.insert(9);
        elem = s.getRandomElement();
        System.out.println(elem);
        elem = s.getRandomElement();
        System.out.println(elem);
        elem = s.getRandomElement();
        System.out.println(elem);
        elem = s.getRandomElement();
        System.out.println(elem);
        elem = s.getRandomElement();
        System.out.println(elem);
    }
}
