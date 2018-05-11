/**
* Uses a linked list to implement the sieve of
* Eratosthenes algorithm for finding prime numbers.
 */

package math.algorithms;
import java.util.*;

public class PrimeNumber {
	public static void main(String[] args) {
		System.out.println("This program will tell you all prime");
		System.out.println("numbers up to a given maximum.");
		System.out.println();

		Scanner console = new Scanner(System.in);
		System.out.print("Maximum number? ");
		int max = console.nextInt();

		List<Integer> primes = sieve(max);
		System.out.println("Prime numbers up to " + max + ":");
		System.out.println(primes);
	}

	// Returns a list of all prime numbers up to given max
	// using the sieve of Eratosthenes algorithm.
	public static List<Integer> sieve(int max) {
		List<Integer> primes = new LinkedList<Integer>();
		primes.add(2); // 2 is known to be prime
		
		// add all numbers from 2 to max to a list
		List<Integer> numbers = new LinkedList<Integer>();
		for (int i = 3; i <= max; i+=2) {
			numbers.add(i);
		}

		System.out.println(numbers);
		
		while (!numbers.isEmpty()) {
			// remove a prime number from the
			int front = numbers.remove(0);
			primes.add(front);
			
			if (front >= Math.sqrt(max)) {
				primes.addAll(numbers);
				numbers.clear();
			}
			
			// remove all multiples of this prime number
			Iterator<Integer> itr = numbers.iterator();
			while (itr.hasNext()) {
				int current = itr.next();
				if (current % front == 0) {
					itr.remove();
				}
			}
		}

		return primes;
	}
}
