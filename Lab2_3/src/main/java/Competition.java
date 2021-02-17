import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Competition extends RecursiveTask<Member>{
    private final Member[] members;
    private final int start;
    private final int end;
    private static final int THRESHOLD = 1;
    Competition(Member[]members, int start, int end){
        this.start=start;
        this.end=end-1;
       this.members=members;
    }
    @Override
    protected Member compute() {
        int len=end-start;
        if(len>THRESHOLD){
            List<Competition> subtasks = new ArrayList<>(createSubtasks());
            for(Competition subtask : subtasks){
                subtask.fork();
            }
            Member member1=subtasks.get(0).join();
            Member member2=subtasks.get(1).join();
            return getWinner(member1,member2);
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

    private List<Competition> createSubtasks() {
        List<Competition> subtasks = new ArrayList<>();
        int offset=end/2;
        Competition subtask1=new Competition(members,start,offset);
        Competition subtask2=new Competition(members,offset+1,end);
        subtasks.add(subtask1);
        subtasks.add(subtask2);
        return subtasks;
    }

    private void printResult(Member m1,Member m2){
        System.out.println("In competition between monk #"+m1.getNumber()+" ("+
                m1.getEnergy()+") from Temple "+m1.getTemple()+
                "\nend monk #"+m2.getNumber()+" (" +m2.getEnergy()+") from Temple "+m2.getTemple()+"\nthe Winner is monk #"+
                m1.getNumber()+"\n");
    }
}
