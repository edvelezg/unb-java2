package concurrency;

import java.util.Random;

public class MutexThread extends Thread {
	private Semaphore mutex;

	public MutexThread(Semaphore mutex, String name) {
		super(name);
		this.mutex = mutex;
		start();
	}
	
	@Override
	public void run() {
		while (true) {
			mutex.down();
			System.out.println("Enter critical section" + getName());
			try {
			    Random rand = new Random();
				sleep ((int) rand.nextInt() * 100); 
			} catch (Exception e) {
			} finally {
    			System.out.println("Leave critical section" + getName());
    			mutex.up();
			}
		}
	}
}
