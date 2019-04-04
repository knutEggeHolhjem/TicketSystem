public class TicketService
{
    private TicketRepository ticketRepository;
    private PayService payService;

    public TicketService(TicketRepository ticketRepository, PayService payService)
    {
        this.ticketRepository = ticketRepository;
        this.payService = payService;
    }

    //Crate ticket and add it to an event if the event has available tickets and payment worked.
    public boolean createTicket(String ownerName, Event event)
    {
        if(event.hasAvailableTicket())
        {
            if (event.getTickets().stream().anyMatch(x -> x.getOwner().equals(ownerName)))
            {
                Ticket newTicket = new Ticket(event.getName(), ownerName);
                newTicket.setPrice(event.getTicketPrice());
                if (payService.payment(ownerName, event.getTicketPrice()))
                {
                    event.addTicket(newTicket);
                    ticketRepository.add(newTicket);
                    return true;
                }
            }
        }
        //System.out.println("Can not add ticket to event");
        return false;
    }

    //Remove ticket from event and repository then return money if ticket is not used.
    public boolean refundTicket(String ownerName, Event event)
    {
        Ticket ticket = getTicket(ownerName);
        if(!ticket.getIsUsed())
        {
            if(payService.returnMoney(ticket.getOwner(), ticket.getPrice()))
            {
                event.getTickets().remove(ticket);
                ticketRepository.remove(ticket);
                return true;
            }
        }
        return false;
    }

    public Ticket getTicket(String name)
    {
        return ticketRepository.get(name);
    }
}
