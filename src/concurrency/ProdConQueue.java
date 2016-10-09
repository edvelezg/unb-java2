package concurrency;

import java.util.Vector;

public class ProdConQueue {
    public static void main(String[] args) {

        Vector<Integer> queue = new Vector<Integer>(8);

        Thread producer = new Producer(queue);
        producer.start();

        Thread consumer = new Consumer(queue);
        consumer.start();

    }

    static class Producer extends Thread {
        private Vector<Integer> buffer;

        public Producer(Vector<Integer> buffer) {
            this.buffer = buffer;
        }

        private void produce(int i) {
            synchronized (buffer) {
                while (buffer.size() == 8) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                buffer.add(i);
                System.out.println("Produced " + i);
                buffer.notify();
            }
        }

        @Override
        public void run() {
            for (int i = 0; i < 30; i++) {
                produce(i);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    static class Consumer extends Thread {
        private Vector<Integer> buffer;

        public Consumer(Vector<Integer> buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (true) {
                consume();
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        private void consume() {
            synchronized (buffer) {
                while (buffer.size() == 0) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Consumed " + buffer.remove(0));
                buffer.notify();
            }
        }
    }
}
