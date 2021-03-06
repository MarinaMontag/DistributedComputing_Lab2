public class BeeGroup extends Thread {
    private final Forest forest;
    private final Hive home;
    private final int number;
    private boolean interrupted;
    private static boolean found;
    BeeGroup(int number, Forest forest, Hive home) {
        this.forest = forest;
        this.home = home;
        this.number = number;
        interrupted=false;
        found=false;
    }

    public void setInterrupted(){
        interrupted=true;
    }

    @Override
    public void run() {
        while (!interrupted) {
           Area area = forest.getArea();
            if (area != null) {
                synchronized (forest) {
                    if (!found) {
                        System.out.println("Area #" + area.getNumber() + " checked by Group #" + number);
                        if (area.isWinnie()) {
                            System.out.println("Result: Winnie the Pooh was found and punished\n");
                            home.setFound();
                            found = true;
                        } else
                            System.out.println("Result: Winnie wasn't found\n");
                    }
                }
            }
            home.goBack(this);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
