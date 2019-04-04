import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EventService_Tests
{

    private EventService eventService;
    private TicketService ticketService;
    private PayService payService;

    @Before
    public void setup()
    {
        EventRepository eventRepository = new EventRepository();
        TicketRepository ticketRepository = new TicketRepository();
        payService = new PayService();
        ticketService = new TicketService(ticketRepository, payService);
        eventService = new EventService(eventRepository);
    }

    @Test
    public void creates_event()
    {
        boolean created = eventService.createEvent("Name", "Location", "Description", 10, 100);

        List<String> events = eventService.getEvents()
                .stream()
                .map(x -> x.getName())
                .collect(Collectors.toList());

        assertEquals("Name", String.join(", ", events));
        assert(created);
    }

    @Test
    public void creates_multiple_events()
    {
        eventService.createEvent("Name", "Location", "Description", 10, 100);
        eventService.createEvent("AdalParty", "Adal", "SickParty", 10, 100);
        eventService.getEvents().size();

        assertEquals(2, eventService.getEvents().size());
    }

    @Test
    public void does_not_create_new_event_when_one_exist()
    {
        eventService.createEvent("Name", "Location", "Description", 10, 100);
        boolean created = eventService.createEvent("Name", "Location", "Description", 10, 100);

        assert(!created);
        assertEquals(1, eventService.getEvents().size());
    }

    @Test
    public void removes_event()
    {
        eventService.createEvent("Name", "Location", "Description", 10, 100);
        eventService.removeEvent("Name");

        List<String> events = eventService.getEvents()
                .stream()
                .map(x -> x.getName())
                .collect(Collectors.toList());

        assert(!events.contains("Name"));
    }

    @Test
    public void check_valid_ticket_returns_true()
    {
        eventService.createEvent("Name", "Location", "Description", 10, 100);
        Event event  = eventService.getEvents()
                .stream()
                .filter(x->x.getName().equals("Name"))
                .findAny()
                .orElse(null);

        ticketService.createTicket("TicketOwner", event);

        assertTrue(!eventService.checkTicket("Name", "TicketOwner"));
    }

    @Test
    public void check_invalid_ticket_returns_false()
    {
        eventService.createEvent("Name", "Location", "Description", 10, 100);
        Event event  = eventService.getEvents()
                .stream()
                .filter(x->x.getName().equals("Name"))
                .findAny()
                .orElse(null);

        ticketService.createTicket("TicketOwner", event);
        Ticket ticket = ticketService.getTicket("TicketOwner");
        ticket.setIsUsed(true);

        assertTrue(eventService.checkTicket("Name", "TicketOwner"));
    }

}
