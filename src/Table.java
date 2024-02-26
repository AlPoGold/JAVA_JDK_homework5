import java.util.ArrayList;

public class Table {

    private ArrayList<Philosopher> philosophers;
    private ArrayList<Fork> forks;

    public Table() {
        philosophers = new ArrayList<>(5);
        forks = Fork.createForks();

        philosophers.add(new Philosopher(0, "Ivan", forks.get(0), forks.get(4)));
        philosophers.add(new Philosopher(1, "Semen",forks.get(1), forks.get(0)));
        philosophers.add(new Philosopher(2, "Petya", forks.get(2), forks.get(1)));
        philosophers.add(new Philosopher(3, "Bob", forks.get(3), forks.get(2)));
        philosophers.add(new Philosopher(4, "Tom", forks.get(4), forks.get(3)));


    }

    public void start(){
        philosophers.forEach(p -> p.getThread().start());


        for (Philosopher philosopher : philosophers) {
            try {
                philosopher.getThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
