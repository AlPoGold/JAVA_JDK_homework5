import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Fork{
    private final int id;
    private boolean isFree = true;

    public Fork(int id) {
        this.id = id;

    }

    public static ArrayList<Fork> createForks(){
        ArrayList<Fork> forks = new ArrayList<>();
        forks.add(new Fork(0));
        forks.add(new Fork(1));
        forks.add(new Fork(2));
        forks.add(new Fork(3));
        forks.add(new Fork(4));
        return forks;
    }

    public int getId() {
        return id;
    }


    public boolean isFree() {
        return isFree;
    }




    public void setFree(boolean isUsed) {
        isFree=isUsed;
    }
}
