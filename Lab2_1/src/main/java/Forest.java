import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class Forest{
    private final int AREAS_AMOUNT = 20;
    Queue<Area> areas;

    Forest() {
        areas = new ArrayDeque<>(AREAS_AMOUNT);
        int winnie = new Random().nextInt(20) + 1;
        for (int i = 1; i <= AREAS_AMOUNT; i++)
            if (i == winnie)
                areas.add(new Area(i, true));
            else
                areas.add(new Area(i, false));
    }

    public synchronized Area getArea() {
        return areas.poll();
    }

    public boolean areAreasEmpty(){
        synchronized (areas) {
            return areas.isEmpty();
        }
    }

    public Queue<Area> getAreas(){
        return areas;
    }
}
