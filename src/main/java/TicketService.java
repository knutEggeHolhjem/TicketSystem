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
            if(payService.payment(ownerName, event.getTicketPrice()))
            {
                event.addTicket(newTicket);
                ticketRepository.add(newTicket);
                return true;
            }
        }
        else
        {
            System.out.println("Can not add ticket to event");
        }
        return false;
    }
}
