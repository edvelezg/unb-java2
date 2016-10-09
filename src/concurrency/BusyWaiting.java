package concurrency;

public class BusyWaiting {

	public static void main(String[] args) {
		Thread task = new Task();
		task.start();

		while (task.isAlive()) {
		}
	}
	
	static class Task extends Thread
	{
		@Override
		public void run() {
			super.run();
			for (int i = 0; i < 50; i++) {
				System.out.println(i);
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}


