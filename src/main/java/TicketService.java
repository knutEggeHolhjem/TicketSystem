public class TicketService
{
    private TicketRepository ticketRepository;
    private PayService payService;

    public TicketService(TicketRepository ticketRepository, PayService payService)
    {
        this.ticketRepository = ticketRepository;
        this.payService = payService;
    }

    public boolean createTicket(String ownerName, Event event)
    {
        if(event.hasAvailableTicket())
        {
            Ticket newTicket = new Ticket(event.getName(), ownerName);
            newTicket.setPrice(event.getTicketPrice());
            if(payService.payment(ownerName, event.getTicketPrice()))
            {
                event.addTicket(newTicket);
                ticketRepository.add(newTicket);
                return true;
            }
        }
        System.out.println("Can not add ticket to event");
        return false;
    }

    public boolean refundTicket(String ownerName, Event event)
    {
        Ticket ticket = getTicket(ownerName);
        event.getTickets().remove(ticket);
        return true;
    }

    public Ticket getTicket(String name)
    {
        return ticketRepository.get(name);
    }
}
