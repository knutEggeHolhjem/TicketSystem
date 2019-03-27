import java.util.ArrayList;
import java.util.stream.Stream;

public class Event
{
    private String name;
    private String location;
    private String description;
    private int maxNumberOfTickets;
    private int ticketPrice;

    public Event(String name, String location, String description, int maxNumberOfTickets, int ticketPrice) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.maxNumberOfTickets = maxNumberOfTickets;
        this.ticketPrice = ticketPrice;
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

    public int getMaxNumberOfTickets() {
        return maxNumberOfTickets;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }


}
