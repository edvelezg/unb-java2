package concurrency;

public class Semaphore {
    // semaphore has a value
    private int value;

    public Semaphore(int init) {
        if (init < 0)
            init = 0;
        value = init;
    }

    public synchronized void down() // acquring the resource
    {
        while (value == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        value--;
    }

    public synchronized void up() { // releasing the resource
        notify();
        value++;
    }
}
