package concurrency;
public class NoBusyWait {
	public static void main(String[] args) {

		Thread task = new Task();
		task.start();
		synchronized (task) {
			try {
				task.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	static class Task extends Thread {
		public Task() {
		}

		@Override
		public void run() {
			
			synchronized (this) {
				for (int i = 0; i < 30; i++) {
					System.out.println(i);
					try {
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				notify();
			}

			super.run();
		}
	}

	// Object theLock = new Object();
	// synchronized( theLock ){
	// Thread task = new TheTask( theLock );
	// task.start();
	// try {
	// theLock.wait();
	// }
	// catch( InterruptedException e ){
	// .... // do something if interrupted
	// }
	// }
	// .....
	// class TheTask extends Thread {
	// private Object theLock;
	// public TheTask( Object theLock ){
	// this.theLock = theLock;
	// }
	// public void run(){
	// synchronized( theLock ){
	// .... // do the task
	// theLock.notify();
	// }
	// }
	// }

}
