import java.util.List;
import java.util.stream.Collectors;

public class EventService
{
    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository)
    {
        this.eventRepository = eventRepository;
    }

    public boolean createEvent(String eventName, String location, String description, int maxNumberOfTickets, int ticketPrice)
    {
        if (eventRepository.get(eventName) != null)
        {
            System.out.println("Event already exist");
            return false;
        }
        else
        {
            Event newEvent = new Event(eventName, location, description, maxNumberOfTickets,ticketPrice);
            eventRepository.add(newEvent);
            return true;
        }
    }

    public void removeEvent(String eventName)
    {
        eventRepository.remove(eventName);
    }

    public List<Event> getEvents()
    {
        return eventRepository.query().collect(Collectors.toList());
    }
}
