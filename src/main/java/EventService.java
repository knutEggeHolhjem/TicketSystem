import java.util.List;
import java.util.stream.Collectors;

public class EventService
{
    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository)
    {
        this.eventRepository = eventRepository;
    }

    //Creates event using all necessary information, can only create events with unique names.
    public boolean createEvent(String eventName, String location, String description, int maxNumberOfTickets, int ticketPrice, int daysUntilStart, String ownerBank, String ownerEmail)
    {
        if (eventRepository.get(eventName) != null)
        {
            System.out.println("Event already exist");
            return false;
        }
        else
        {
            Event newEvent = new Event(eventName, location, description, maxNumberOfTickets, ticketPrice, daysUntilStart, ownerBank, ownerEmail);
            eventRepository.add(newEvent);
            return true;
        }
    }

    //The ticket is valid if the event exist, and has a ticket under that name that is not used.
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

    //Finds an event under that name and a ticket, then sets the ticket to used.
    public boolean useTicket(String eventName, String ticketOwner)
    {
        Ticket ticket;
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

                if (ticket != null)
                {
                    ticket.setIsUsed(true);
                    return true;
                }
            }
        }
        return false;
    }

    public void removeEvent(String eventName)
    {
        eventRepository.remove(eventName);
    }

    public List<Event> getEvents()
    {
        return eventRepository.query().collect(Collectors.toList());
    }

    public Event getEvent(String name)
    {
        return getEvents()
                .stream()
                .filter(x->x.getName().equals(name))
                .findAny()
                .orElse(null);
    }
}
