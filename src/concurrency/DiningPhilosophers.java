package concurrency;
class DiningPhilosophers {
    Object[] forks;
    Philosopher[] philosophers;

    public DiningPhilosophers(int num) {
        forks = new Object[num];
        philosophers = new Philosopher[num];

        for (int i = 0; i < num; ++i) {
            forks[i] = new Object();
            if (i % 2 == 0) {
                philosophers[i] = new Philosopher(i, i, (i + 1) % num);
            } else {
                philosophers[i] = new Philosopher(i, (i + 1) % num, i);
            }
        }
    }

    public void startEating() throws InterruptedException {
        for (int i = 0; i < philosophers.length; ++i) {
            philosophers[i].start();
        }
        philosophers[0].join();

    }

    class Philosopher extends Thread {
        int id;
        int fork1;
        int fork2;

        public Philosopher(int id, int fork1, int fork2) {
            this.id = id;
            this.fork1 = fork1;
            this.fork2 = fork2;
        }

        @Override
        public void run() {
            status("ready to eat using forks" + fork1 + " and " + fork2);
            while (true) {
                status("Picking up fork " + fork1);
                synchronized (forks[fork1]) {
                    status("Picking up fork " + fork2);
                    synchronized (forks[fork2]) {
                        status("start eating");
                    }
                }
            }
        }

        private void status(String msg) {
            System.out.println("Philosopher " + id + ": " + msg);
        }
    }

    public static void main(String args[]) {
        DiningPhilosophers d = new DiningPhilosophers(5);
        try {
            d.startEating();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int num = fib(41);    
//        System.out.println(num);
    }


    static int fib(int n) {
        if (n == 0 || n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }
}


