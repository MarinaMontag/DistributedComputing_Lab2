import java.util.Random;

public class Member {
    private String temple;
    private int number;
    private final Integer energy;
    Member(int number){
        this.number=number;
        if(new Random().nextInt(2)==0)
            temple="ONE";
        else
            temple="TWO";
        energy=new Random().nextInt(2001)+1000;
    }

    public Integer getEnergy(){
        return energy;
    }

    public String getTemple() {
        return temple;
    }

    public int getNumber() {
        return number;
    }
}
