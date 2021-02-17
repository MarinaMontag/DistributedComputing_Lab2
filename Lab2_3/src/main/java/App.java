import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main(String[] args) {
        final int AMOUNT_OF_COMPETITORS=100;
        Member[]competitors=new Member[AMOUNT_OF_COMPETITORS];
        for (int i=0;i<AMOUNT_OF_COMPETITORS;i++)
            competitors[i]=new Member(i);
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        Competition competition=new Competition(competitors,0,AMOUNT_OF_COMPETITORS);
        Member winner=forkJoinPool.invoke(competition);
        System.out.println("\n\nThe Winner of the competition is monk #"+winner.getNumber()+
                " from the Temple "+winner.getTemple()+" with ENERGY "+winner.getEnergy());
    }
}
