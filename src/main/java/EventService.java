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

    public boolean checkTicketValid(String eventName, String ticketOwner)
    {
        Ticket ticket = null;
        Event event = eventRepository.get(eventName);
        if(event != null)
        {
            ticket = event.getTickets()
                    .stream()
                    .filter(x -> x.getOwner().equals(ticketOwner))
                    .findAny()
                    .orElse(null);
        }

        if (ticket != null)
        {
            return !ticket.getIsUsed();
        }
        else return false;
    }

    public void useTicket(String eventName, String ticketOwner)
    {
        Ticket ticket = null;
        Event event = eventRepository.get(eventName);
        if (checkTicketValid(eventName, ticketOwner))
        {
            if (event!= null)
            {
                ticket = event.getTickets()
                        .stream()
                        .filter(x -> x.getOwner().equals(ticketOwner))
                        .findAny()
                        .orElse(null);
            }

            if (ticket != null)
            {
                ticket.setIsUsed(true);
            }
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
