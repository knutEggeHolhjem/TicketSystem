import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Event
{
    private String name;
    private String location;
    private String description;
    private int maxNumberOfTickets;
    private int ticketPrice;
    private Set<Ticket> tickets = new HashSet<>(0);


    public Event(String name, String location, String description, int maxNumberOfTickets, int ticketPrice) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.maxNumberOfTickets = maxNumberOfTickets;
        this.ticketPrice = ticketPrice;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public boolean hasAvailableTicket()
    {
        return tickets.size() < maxNumberOfTickets;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }


    public int getTicketPrice() {
        return ticketPrice;
    }

    public Set<Ticket> getTickets()
    {
        return tickets;
    }
}
