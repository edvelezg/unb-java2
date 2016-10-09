package concurrency;

public class Multithreading {

    public static void main(String[] args) {
        int numThreadsInCriticalSection = 3;
        Semaphore mutex = new Semaphore(numThreadsInCriticalSection);

        for (int i = 0; i < 10; i++) {
            @SuppressWarnings("unused")
            MutexThread thread = new MutexThread(mutex, "Thread " + i);
        }

    }

}
