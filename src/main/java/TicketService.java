public class TicketService
{
    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository)
    {
        this.ticketRepository = ticketRepository;
    }

    public void createTicket(String ownerName, Event event)
    {
        if(event.hasAvailableTicket())
        {
            Ticket newTicket = new Ticket(event.getName(), ownerName);
            event.addTicket(newTicket);
            ticketRepository.add(newTicket);
        }
        else
        {
            System.out.println("Can not add ticket to event");
        }
    }
}
