import java.util.*;
import java.util.stream.Stream;

public class Event
{
    private String name;
    private String location;
    private String description;
    private Long dateOfCreation;
    private Long msUntilStart; //from date of creation
    private int maxNumberOfTickets;
    private int ticketPrice;
    private Set<Ticket> tickets = new HashSet<>(0);


    public Event(String name, String location, String description, int maxNumberOfTickets, int ticketPrice, int daysUntilStart) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.maxNumberOfTickets = maxNumberOfTickets;
        this.ticketPrice = ticketPrice;
        this.dateOfCreation = System.currentTimeMillis();
        this.msUntilStart = (long) daysUntilStart*24*60*60*1000;
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

    public Calendar getStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateOfCreation+msUntilStart);
        return calendar;
    }

    public boolean hasEventStarted(Long todaysDate){
        if(todaysDate>dateOfCreation+msUntilStart){
            return true;
        }
        return false;
    }

    public void setMsUntilStart(Long msUntilStart) {
        this.msUntilStart = msUntilStart;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public Set<Ticket> getTickets()
    {
        return tickets;
    }
}
