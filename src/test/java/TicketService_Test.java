import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class TicketService_Test
{
    private EventService eventService;
    private TicketService ticketService;

    @Before
    public void setup()
    {
        EventRepository eventRepository = new EventRepository();
        TicketRepository ticketRepository = new TicketRepository();
        PayService payService = new PayService();
        ticketService = new TicketService(ticketRepository, payService);
        eventService = new EventService(eventRepository);
    }

    @Test
    public void creates_ticket_and_adds_to_event()
    {
        String ticketOwnerName = "TicketOwner";
        eventService.createEvent("Name", "Location", "Description", 10, 100, 10,"","");

        Event event = eventService.getEvent("Name");

        boolean result = ticketService.createTicket(ticketOwnerName, event,"");

        assertTrue(event.getTickets().stream().anyMatch(x -> x.getOwner().equals(ticketOwnerName)));
        assertTrue(result);
    }

    @Test
    public void creates_ticket()
    {
        String ticketOwnerName = "TicketOwner";
        eventService.createEvent("Name", "Location", "Description", 10, 100, 10,"","");

        Event event = eventService.getEvent("Name");

        boolean result = ticketService.createTicket(ticketOwnerName, event,"");

        assertNotNull(ticketService.getTicket(ticketOwnerName));
        assertTrue(result);
    }

    @Test
    public void does_not_crate_ticket_when_event_has_no_available_tickets()
    {
        String ticketOwnerName = "TicketOwner";
        eventService.createEvent("Name", "Location", "Description", 0, 100, 10,"","");

        Event event = eventService.getEvent("Name");

        boolean result = ticketService.createTicket(ticketOwnerName, event,"");

        assertNull(ticketService.getTicket(ticketOwnerName));
        assertTrue(!result);
    }

    @Test
    public void getTicket_shows_existing_ticket()
    {
        String ticketOwnerName = "TicketOwner";
        eventService.createEvent("Name", "Location", "Description", 10, 100, 10,"","");

        Event event = eventService.getEvent("Name");

        ticketService.createTicket(ticketOwnerName, event,"");

        assertEquals(ticketOwnerName, ticketService.getTicket(ticketOwnerName).getOwner());
    }

    @Test
    public void refund_refundable_ticket()
    {
        String ticketOwnerName = "TicketOwner";

        eventService.createEvent("Name", "Location", "Description", 10, 100, 10,"","");
        Event event = eventService.getEvent("Name");

        ticketService.createTicket(ticketOwnerName, event,"");
        ticketService.refundTicket(ticketOwnerName, event);

        assertNull(ticketService.getTicket(ticketOwnerName));
        assertTrue(event.getTickets().stream().noneMatch(x -> x.getOwner().equals(ticketOwnerName)));
    }

    @Test
    public void nonrefundable_ticket_not_refunded()
    {
        String ticketOwnerName = "TicketOwner";
        Ticket ticket = null;

        eventService.createEvent("Name", "Location", "Description", 10, 100, 10,"","");
        Event event = eventService.getEvent("Name");

        ticketService.createTicket(ticketOwnerName, event,"");

        if(event != null) {
            ticket = event.getTickets()
                    .stream()
                    .filter(x -> x.getOwner().equals(ticketOwnerName))
                    .findAny()
                    .orElse(null);
        }

        if (ticket != null)
        {
            ticket.setIsUsed(true);
        }

        ticketService.refundTicket(ticketOwnerName, event);

        assertEquals(ticketOwnerName ,ticketService.getTicket(ticketOwnerName).getOwner());
        assertEquals(ticketOwnerName ,event.getTickets().stream().filter(x->x.getOwner().equals(ticketOwnerName)).findAny().orElse(null).getOwner());
    }


}
