import java.util.Random;

public class Philosopher implements Runnable{

    private Random rnd = new Random();

    private int id;
    private String name;
    private Fork leftFork;
    private Fork rightFork;

    private Thread thread;
    private boolean isFedUp;
    private  int countFeeding = 3;

    public Philosopher(int id, String name, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.thread = new Thread(this);
        this.isFedUp = false;


    }

    @Override
    public void run(){
        while(countFeeding>0){
            try{
                think();
                useForks();
                eat();
                endUsingForks();
            }catch (InterruptedException e){
                getThread().interrupt();
            }



            countFeeding-=1;
        }
        isFedUp = true;
        System.out.println(id + " is full!!!");
        getThread().interrupt();


    }

    private void endUsingForks() {
        System.out.println(String.format("%d stops eating", id));
        synchronized (leftFork){
            synchronized (rightFork){
                leftFork.setFree(true);
                rightFork.setFree(true);
            }
    }


    }

    public void eat() throws InterruptedException {
        System.out.println(id + " is eating");
        Thread.sleep(500*rnd.nextInt(1, 4));
    }

    public void useForks() throws InterruptedException {

        //цикл для того, чтобы подождать пока вилки освободятся
        while (true) {
            synchronized (leftFork) {
                synchronized (rightFork) {

                    //если 2 вилки освободились, что философ начинает есть и выходим из цикла
                    if (leftFork.isFree() && rightFork.isFree()) {
                        leftFork.setFree(false);
                        rightFork.setFree(false);
                        System.out.println(
                                String.format("%d using forks %d in left hand, %d in right hand",
                                        id, leftFork.getId(), rightFork.getId()));
                        return;
                    } else Thread.sleep(100);
                }
            }
        }

    }

    public void think() throws InterruptedException {

        System.out.println("Philosopher " + id + " is thinking");
        Thread.sleep(500*rnd.nextInt(1, 4));
    }

    public Thread getThread() {
        return thread;
    }
}
