import java.util.ArrayDeque;
import java.util.Queue;

public class Hive implements Runnable {
    private final int GROUPS_AMOUNT = 5;
    private final Queue<BeeGroup> in;
    private final Forest forest;
    private Boolean found;
    private final Thread thread;

    Hive(Forest forest) {
        found = false;
        this.forest = forest;
        in = new ArrayDeque<>();
        thread = new Thread(this);
        thread.start();
    }

    private void startBees() {
        for (int i = 0; i < GROUPS_AMOUNT; i++) {
            new BeeGroup(i + 1, forest, this).start();
        }
    }

    public void setFound() {
        found = true;
    }

    @Override
    public void run() {
        startBees();
        while (!thread.isInterrupted()) {
            while (in.size() < GROUPS_AMOUNT)
                sleep();
            synchronized (forest) {
                if (found) {
                    for (BeeGroup beeG : in) {
                        beeG.setInterrupted();
                    }
                    thread.interrupt();
                } else {
                    for (int i=0;i<in.size();i++) {
                        in.poll();
                    }
                }
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void goBack(BeeGroup beeG) {
        synchronized (in) {
            in.add(beeG);
        }
    }
}
