package concurrency;

public class ProdCon {
    public static void main(String[] args) {

        IntBuffer buffer = new IntBuffer();

        Thread producer = new Producer(buffer);
        producer.start();

        Thread consumer = new Consumer(buffer);
        consumer.start();

    }

    static class IntBuffer {
        int   index;
        int[] buffer = new int[8];

        private synchronized void add(int num) {
            while (index == buffer.length - 1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            notify();
            buffer[++index] = num;
        }

        private synchronized int remove() {
            while (index <= -1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return buffer[index--];
        }
    }

    static class Producer extends Thread {
        private IntBuffer buffer;
        int               number = 0;

        public Producer(IntBuffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            for (int i = 0;; i++) {
                buffer.add(i);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Produced " + i);
            }

        }

    }

    static class Consumer extends Thread {
        private IntBuffer buffer;

        public Consumer(IntBuffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while (true) {
                int num = buffer.remove();
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (num != -1) {
                    System.out.println("Consumed " + num);
                }
            }
        }
    }
}
