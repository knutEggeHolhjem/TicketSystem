import java.util.ArrayList;
import java.util.Optional;

public class TicketRepository
{

    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    public void add(Ticket ticket)
    {
        tickets.add(ticket);
    }

    public Ticket get(String name)
    {
        Optional<Ticket> possibleTicket = tickets.stream().filter(x -> x.getOwner().equals(name)).findFirst();
        return possibleTicket.orElse(null);
    }

    public void remove(Ticket ticket)
    {
        tickets.remove(ticket);
    }
}
