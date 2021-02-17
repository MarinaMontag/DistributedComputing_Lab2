import java.util.concurrent.RecursiveTask;

public class Competition extends RecursiveTask<Member>{
    private Member[] members;
    private int start;
    private int end;
    private static int THRESHOLD = 1;
    Competition(Member[]members, int start, int end){
        this.start=start;
        this.end=end-1;
       this.members=members;
    }
    @Override
    protected Member compute() {
        int len=end-start;
        if(len>THRESHOLD){
            int offset=end/2;
            Competition subtask1=new Competition(members,start,offset);
            subtask1.fork();
            Competition subtask2=new Competition(members,offset+1,end);
            return getWinner(subtask2.compute(),subtask1.join());
        }
        else{
            return getWinner(members[start],members[end]);
        }
    }

    private Member getWinner(Member m1,Member m2){
        if(m1.getEnergy()>=m2.getEnergy()){
            printResult(m1,m2);
            return m1;
        }
        else {
            printResult(m2,m1);
            return m2;
        }
    }

    private void printResult(Member m1,Member m2){
        System.out.println("In competition between monk #"+m1.getNumber()+" ("+
                m1.getEnergy()+") from Temple "+m1.getTemple()+
                "\nend monk #"+m2.getNumber()+" (" +m2.getEnergy()+") from Temple "+m2.getTemple()+"\nthe Winner is monk #"+
                m1.getNumber()+"\n");
    }
}
