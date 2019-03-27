import java.util.List;
import java.util.stream.Collectors;

public class EventService
{
    private EventRepository eventRepository;
    private TicketRepository ticketRepository;

    public EventService(EventRepository eventRepository, TicketRepository ticketRepository)
    {
        this.eventRepository = eventRepository;
        this.ticketRepository = ticketRepository;
    }

    public void createEvent(String eventName, String location, String description, int maxNumberOfTickets, int ticketPrice)
    {
        if (eventRepository.get(eventName) == null)
        {
            System.out.println("Event already exist");
        }
        else
        {
            Event newEvent = new Event(eventName, location, description, maxNumberOfTickets,ticketPrice);
            eventRepository.add(newEvent);
        }
    }

    public List<Event> getEvents()
    {
        return eventRepository.query().collect(Collectors.toList());
    }
}
