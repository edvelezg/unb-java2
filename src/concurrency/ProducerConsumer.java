package concurrency;

import java.util.Vector;

public class ProducerConsumer {
    volatile static boolean finished = false;

    public static void main(String[] args) {
        int size = 5;
        Vector<Integer> sQ = new Vector<Integer>(size);
        Thread consumer = new Thread(new Consumer(sQ, size));
        Thread producer = new Thread(new Producer(sQ, size));
        consumer.start();
        producer.start();
    }

    static class Consumer implements Runnable {
        Vector<Integer> sQ = new Vector<Integer>();
        int             size;

        public Consumer(Vector<Integer> sQ, int size) {
            this.sQ = sQ;
            this.size = size;
        }

        @Override
        public void run() {
            while (!(finished && sQ.isEmpty())) {
                try {
                    System.out.println("Consuming element: " + consume());
                    ;
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        private int consume() throws InterruptedException {
            synchronized (sQ) {

                while (sQ.isEmpty()) {
                    System.out.println("The queue is empty and " + Thread.currentThread().getName() + " has to wait."
                            + "size is: " + sQ.size());
                    sQ.wait();
                }

                sQ.notifyAll();
                return sQ.remove(0);
            }

        }

    }

    static class Producer implements Runnable {
        Vector<Integer> sQ = new Vector<Integer>();
        int             size;

        public Producer(Vector<Integer> sQ, int size) {
            this.sQ = sQ;
            this.size = size;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20; ++i) {
                try {
                    produce(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            finished = true;
        }

        private void produce(int i) throws InterruptedException {

            synchronized (sQ) {
                while (sQ.size() == size) {
                    System.out.println("The queue is full and " + Thread.currentThread().getName() + " has to wait."
                            + "size is: " + sQ.size());
                    sQ.wait();
                }

                sQ.add(i);
                sQ.notify();
            }

        }

    }

}