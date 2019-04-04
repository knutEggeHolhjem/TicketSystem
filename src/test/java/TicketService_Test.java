import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class TicketService_Test
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
    public void creates_ticket_and_adds_to_event()
    {
        String ticketOwnerName = "TicketOwner";
        Ticket ticket = null;
        eventService.createEvent("Name", "Location", "Description", 10, 100);

        Event event  = eventService.getEvents()
                .stream()
                .filter(x->x.getName().equals("Name"))
                .findAny()
                .orElse(null);

        boolean result = ticketService.createTicket(ticketOwnerName, event);

        if(event != null) {
            ticket = event.getTickets()
                    .stream()
                    .filter(x -> x.getOwner().equals(ticketOwnerName))
                    .findAny()
                    .orElse(null);
        }

        assertTrue(event.getTickets().contains(ticket));
        assertTrue(result);
    }

    @Test
    public void creates_ticket()
    {
        String ticketOwnerName = "TicketOwner";
        eventService.createEvent("Name", "Location", "Description", 10, 100);

        Event event  = eventService.getEvents()
                .stream()
                .filter(x->x.getName().equals("Name"))
                .findAny()
                .orElse(null);

        boolean result = ticketService.createTicket(ticketOwnerName, event);

        assertNotNull(ticketService.getTicket(ticketOwnerName));
        assertTrue(result);
    }

    @Test
    public void does_not_crate_ticket_when_event_has_no_available_tickets()
    {
        String ticketOwnerName = "TicketOwner";
        eventService.createEvent("Name", "Location", "Description", 0, 100);

        Event event  = eventService.getEvents()
                .stream()
                .filter(x->x.getName().equals("Name"))
                .findAny()
                .orElse(null);

        boolean result = ticketService.createTicket(ticketOwnerName, event);

        assertNull(ticketService.getTicket(ticketOwnerName));
        assertTrue(!result);
    }

    @Test
    public void getTicket_shows_existing_ticket()
    {
        String ticketOwnerName = "TicketOwner";
        eventService.createEvent("Name", "Location", "Description", 10, 100);

        Event event  = eventService.getEvents()
                .stream()
                .filter(x->x.getName().equals("Name"))
                .findAny()
                .orElse(null);

        ticketService.createTicket(ticketOwnerName, event);

        assertEquals(ticketOwnerName, ticketService.getTicket(ticketOwnerName).getOwner());
    }
}
