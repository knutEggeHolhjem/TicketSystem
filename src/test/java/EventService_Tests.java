import org.junit.Before;
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
        boolean created = eventService.createEvent("Name", "Location", "Description", 10, 100, 10);

        List<String> events = eventService.getEvents()
                .stream()
                .map(Event::getName)
                .collect(Collectors.toList());

        assertEquals("Name", String.join(", ", events));
        assert(created);
    }

    @Test
    public void creates_multiple_events()
    {
        eventService.createEvent("Name", "Location", "Description", 10, 100, 10);
        eventService.createEvent("AdalParty", "Adal", "SickParty", 10, 100, 10);

        assertEquals(2, eventService.getEvents().size());
    }

    @Test
    public void does_not_create_new_event_when_one_exist()
    {
        eventService.createEvent("Name", "Location", "Description", 10, 100, 10);
        boolean created = eventService.createEvent("Name", "Location", "Description", 10, 100, 10);

        assert(!created);
        assertEquals(1, eventService.getEvents().size());
    }

    @Test
    public void removes_event()
    {
        eventService.createEvent("Name", "Location", "Description", 10, 100, 10);
        eventService.removeEvent("Name");

        List<String> events = eventService.getEvents()
                .stream()
                .map(Event::getName)
                .collect(Collectors.toList());

        assert(!events.contains("Name"));
    }

    @Test
    public void check_valid_ticket_returns_true()
    {
        eventService.createEvent("Name", "Location", "Description", 10, 100, 10);
        Event event = eventService.getEvent("Name");

        ticketService.createTicket("TicketOwner", event);

        assertTrue(eventService.checkTicketValid("Name", "TicketOwner"));
    }

    @Test
    public void check_invalid_ticket_returns_false()
    {
        eventService.createEvent("Name", "Location", "Description", 10, 100, 10);
        Event event = eventService.getEvent("Name");

        ticketService.createTicket("TicketOwner", event);
        Ticket ticket = ticketService.getTicket("TicketOwner");
        ticket.setIsUsed(true);

        assertTrue(!eventService.checkTicketValid("Name", "TicketOwner"));
    }

    @Test
    public void using_ticket_changes_ticket_status_to_used()
    {
        eventService.createEvent("Name", "Location", "Description", 10, 100, 10);
        Event event = eventService.getEvent("Name");

        ticketService.createTicket("TicketOwner", event);

        eventService.useTicket("Name", "TicketOwner");

        assertTrue(!eventService.checkTicketValid("Name", "TicketOwner"));
    }

    @Test
    public void event_shows_when_ended(){
        eventService.createEvent("Name", "Location", "Description", 10, 100, 10);
        Event event = eventService.getEvent("Name");

        long dateIn11Days = System.currentTimeMillis() + 11*24*60*60*1000;

        assertTrue(event.hasEventStarted(dateIn11Days));
    }

    @Test
    public void cant_create_ticket_when_event_has_ended(){
        eventService.createEvent("Name", "Location", "Description", 10, 100, -1); //event ended yesterday
        Event event = eventService.getEvent("Name");

        assertTrue(!ticketService.createTicket("TicketOwner", event));
    }

    @Test
    public void can_create_ticket_when_event_hasnt_started(){
        eventService.createEvent("Name", "Location", "Description", 10, 100, 10); //event starts in 10 days
        Event event = eventService.getEvent("Name");

        assertTrue(ticketService.createTicket("TicketOwner", event));
    }

    @Test
    public void shows_all_available_events()
    {
        eventService.createEvent("Name", "Location", "Description", 10, 100, 10);
        eventService.createEvent("AdalParty", "Adal", "SickParty", 10, 100, 10);

        List<String> events = eventService
                .getEvents()
                .stream()
                .map(Event::getName)
                .collect(Collectors.toList());

        assertTrue(events.contains("Name"));
        assertTrue(events.contains("AdalParty"));
    }


}
