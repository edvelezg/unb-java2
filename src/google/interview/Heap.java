package google.interview;

import java.util.ArrayList;
import java.util.Arrays;

public class Heap {
	private ArrayList<Integer> heap;
	public Heap() {
		heap = new ArrayList<Integer>();
	}
	
	public void insert(int item) {
		heap.add(item);
		siftup();
	}
	
	public void print() {
		String str = Arrays.toString(heap.toArray(new Integer[heap.size()]));
		System.out.println(str);
	}
	
	private void siftup() {
		int  k = heap.size() -1;
		while (k > 0) {
			int p = (k - 1) / 2;
			int child = heap.get(k);
			int parent = heap.get(p);
			if (child > parent) {
				heap.set(k, parent);
				heap.set(p, child);
				k = p;
			}
			else {
				break;
			}
		}
	}
	
	private void remove() {
		int item = heap.get(0);
		heap.set(0, heap.get(heap.size()-1));
		siftdown();
	}

	private void siftdown() {
		int k = 0; // parent pos
		int l = 2*k + 1; // left child pos
		while (l < heap.size()) {
			int max = l; // pos of max child 
			int r = l + 1; // right child pos
			if (heap.get(r) > heap.get(l)) max++; 
			
			int maxChild = heap.get(max);
			int par = heap.get(k);
			if (maxChild > par) {
				heap.set(max, par);
				heap.set(k, maxChild);
				k = max;
				l = 2*k + 1; // calc a new left child pos
			}
			else {
				return;
			}
		}
		
	}

	public static void main(String[] args) {
		Heap heap = new Heap();
		heap.insert(1);
		heap.insert(2);
		heap.insert(3);
		heap.insert(4);
		heap.insert(5);
		heap.insert(6);
//		heap.insert(7);
//		heap.insert(0);
		heap.print();
	}
}
