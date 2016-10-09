package concurrency;

public class ThreadsExample {

    public ThreadsExample() {
        (new SimpleThread("0 thread")).start();
        (new SimpleThread("1 thread")).start();
        (new SimpleThread("2 thread")).start();
        (new SimpleThread("3 thread")).start();
        (new SimpleThread("4 thread")).start();
        (new SimpleThread("5 thread")).start();
    }

    private class SimpleThread extends Thread {
        public SimpleThread(String str) {
            super(str);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(getName() + " skys " + i);
            }
            try {
                sleep((long) Math.random() * 1000);
            } catch (InterruptedException e) {
            }

            System.out.println(getName() + " is done");
        }
    }

    public static void main(String[] args) {
        new ThreadsExample();
    }
}
