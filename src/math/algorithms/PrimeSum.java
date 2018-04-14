package math.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class PrimeSum {

	public boolean isPrime(int p) {
		if (p == 2) return true;
		if (p % 2 == 0) return false;
		if (p < 2) return false;
		for (int i = 3; i <= Math.sqrt(p); i+=2) {
			if (p % i == 0) return false;
		}
		return true;
	}
	
  	public ArrayList<Integer> primesum(int a) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 2; i < a; i++) {
            if (isPrime(i) && isPrime(a - i)) {
                arr.add(i);
                arr.add(a - i);
                return arr;
            }
        }
        return arr;  		
  	}

	
	public List<Integer> findPrimes(int n) {

		List<Integer> candList = new LinkedList<>();
		for (int i = 3; i <= n; i+=2) {
			candList.add(i);
		}
//		System.out.println(candList);

		List<Integer> primeList = new LinkedList<>();
		primeList.add(2); // 2 is a known prime
		
		while (!candList.isEmpty()) {
			int num = candList.remove(0);
			primeList.add(num);
			
			if (num > Math.sqrt(n)) {
				primeList.addAll(candList);
				candList.clear();
				break;
			}
			
			for (Iterator<Integer> it = candList.iterator(); it.hasNext();) {
				Integer cand = (Integer) it.next();
				if (cand % num == 0) {
					it.remove();
				}
			}
		}

		System.out.println(primeList);
		return primeList;
	}

	public ArrayList<Integer> primesum_inefficient(int a) {
		ArrayList<Integer> res = new ArrayList<>();
		if (a % 2 == 1)
			return res;

		int n = a;
		List<Integer> candList = new LinkedList<>();
		for (int i = 3; i <= n; i += 2) {
			candList.add(i);
		}

		List<Integer> primeList = new LinkedList<>();
		primeList.add(2); // 2 is a known prime;

		while (!candList.isEmpty()) {
			int num = candList.get(0);
			primeList.add(num);
			candList.remove(0);

			if (Math.sqrt(a) < num) {
				primeList.addAll(candList);
				candList.clear();
			}

			for (Iterator<Integer> it = candList.iterator(); it.hasNext();) {
				Integer cand = (Integer) it.next();
				if (cand % num == 0) {
					it.remove();
				}
			}
		}

		Set<Integer> primeSet = new HashSet<Integer>(primeList);
		for (Iterator<Integer> it = primeList.iterator(); it.hasNext();) {
			Integer val = (Integer) it.next();
			int b = a - val;
			if (primeSet.contains(b)) {
				res.add(val);
				res.add(b);
				break;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		PrimeSum s = new PrimeSum();
		System.out.println(s.primesum(4));
		// System.out.println(s.primeSum(64));

	}
}
