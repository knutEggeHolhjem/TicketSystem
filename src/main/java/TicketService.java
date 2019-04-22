

public class TicketService {
    private TicketRepository ticketRepository;
    private PayService payService;

    public TicketService(TicketRepository ticketRepository, PayService payService) {
        this.ticketRepository = ticketRepository;
        this.payService = payService;
    }

    //Create ticket and add it to an event if the event has available tickets and payment worked.
    public boolean createTicket(String ownerName, Event event, String ownerEmail)
    {
        if (canCreateNewTicket(ownerName, event))
        {
            Ticket newTicket = new Ticket(event.getName(), ownerName);
            newTicket.setOwnerEmail(ownerEmail);
            newTicket.setPrice(event.getTicketPrice());
            if (payService.payment(ownerName, event.getTicketPrice()))
            {
                event.addTicket(newTicket);
                ticketRepository.add(newTicket);
                return true;
            }
        }
        return false;
    }

    private boolean canCreateNewTicket(String ownerName, Event event)
    {
        if (event.hasEventStarted(System.currentTimeMillis()))
        {
            System.out.println("Event has already started, can't make a ticket for this event");
            return false;
        }
        if (!event.hasAvailableTicket())
        {
            return false;
        }
        if (event.getTickets().stream().anyMatch(x -> x.getOwner().equals(ownerName)))
        {
            return false;
        }
        return true;
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
