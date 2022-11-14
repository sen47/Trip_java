import java.util.ArrayList;

/////////////////////////////////////////////////////////////////////////// Main
public class Program {
    public static void main(String args[]){
        Trip trip = new Trip();

        for(Participant el : trip.getParticipants()){
            System.out.println(el);
        }
        System.out.println();

        for(Spend el : trip.getSpends()){
            System.out.println(el);
        }
    }
}

/////////////////////////////////////////////////////////////////////////// Trip
class Trip{
    private ArrayList<Participant> m_participants;
    private ArrayList<Spend> m_spends;

    ////////////////////////////////////////////// Ctor
    Trip(){
        m_participants = new ArrayList<Participant>();
        m_spends = new ArrayList<Spend>();

        addParticipant(new Participant("Aleks"));
        addParticipant(new Participant("James"));
        addParticipant(new Participant("Kate"));
        addParticipant(new Participant("Amy"));

        addSpend(new Spend("Food", 1000));
        addSpend(new Spend("Drinks", 1000));
        addSpend(new Spend("Camp", 1000));
        addSpend(new Spend("Fuel", 1000));
    }

    ////////////////////////////////////////////// Get & set
    public Spend getSpend(final int index){
        if(index < m_spends.size())
            return m_spends.get(index);
        return null;
    }

    public Participant getParticipant(final int index){
        if(index < m_participants.size())
            return m_participants.get(index);
        return null;
    }

    public final ArrayList<Participant> getParticipants(){
        return m_participants;
    }

    public final ArrayList<Spend> getSpends(){
        return m_spends;
    }

    ////////////////////////////////////////////// Func
    public void addParticipant(final Participant participant){
        m_participants.add(participant);
    }

    public void deleteParticipant(final Participant participant){
        if(m_participants.contains(participant))
            m_participants.remove(participant);
    }

    public void addSpend(final Spend spend){
        m_spends.add(spend);
    }

    public  void deleteSpend(final Spend spend){
        m_spends.remove(spend);
    }

    void addParticipantToSpend(Spend spend, Participant participant){
        if(!participant.isInCollection(spend)){
            participant.addSpend(spend);
            spend.addParticipant();
        }
    }

    void deleteParticipant(Spend spend, Participant participant){
        if(participant.isInCollection(spend)){
            participant.deleteSpend(spend);
            spend.deleteParticipant();
        }
    }
}

/////////////////////////////////////////////////////////////////////////// Base
class Base_Element{
    private String m_name;
    private static String m_className;

    ////////////////////////////////////////////// Ctor
    Base_Element(final String name, final String className){
        m_name = name;
        m_className = className;
    }

    @Override
    public String toString(){
        return m_name;
    }

    ////////////////////////////////////////////// Get & set
    public String getName(){
        return m_name;
    }

    public void setName(final String name){
        m_name = name;
    }

    public static String getClassName(){
        return m_className;
    }
}

/////////////////////////////////////////////////////////////////////////// Participant
class Participant extends Base_Element{
    private ArrayList<Spend> m_personSpends;

    Participant(final String name){
        super(name,"Participant");
        m_personSpends = new ArrayList<Spend>();
    }

    ////////////////////////////////////////////// Collection func
    public void addSpend(final Spend spend){
        if(!m_personSpends.contains(spend))
            m_personSpends.add(spend);
    }

    public void deleteSpend(final Spend spend){
        if(m_personSpends.contains(spend))
            m_personSpends.remove(spend);
    }

    public boolean isInCollection(final Spend spend){
        return m_personSpends.contains(spend);
    }

    ////////////////////////////////////////////// Get & set
    public double getPersonSum(){
        double sum = 0;
        for(Spend el : m_personSpends){
            sum += el.getPersonPath();
        }
        return sum;
    }
}

/////////////////////////////////////////////////////////////////////////// Spend
class Spend extends Base_Element{
    private double m_sum;
    private int m_participants;

    ////////////////////////////////////////////// Ctor
    Spend(final String name, final double sum){
        super(name, "Spend");
        m_sum = sum;
        m_participants = 0;
    }

    @Override
    public String toString(){
        return getName() + ' ' + String.valueOf(m_sum);
    }

    ////////////////////////////////////////////// Get & set
    double getSum(){
        return m_sum;
    }

    public void setSum(final double sum){
        m_sum = sum;
    }

    public void addParticipant(){
        ++m_participants;
    }

    public void deleteParticipant(){
        if(m_participants > 0)
            --m_participants;
    }

    public double getPersonPath(){
        return m_sum / m_participants;
    }
}