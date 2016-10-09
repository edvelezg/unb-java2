package concurrency;

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
				sleep ((int) Math.random() * 100); 
			} catch (Exception e) {}
			System.out.println("Leave critical section" + getName());
			mutex.up();
		}
	}
}
