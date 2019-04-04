import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String eventName = scanner.nextLine();
        String customerName = scanner.nextLine();
        String customerName2 = scanner.nextLine();

        EventRepository myEventRepository = new EventRepository();
        TicketRepository myTicketRepository = new TicketRepository();


        PayService payService = new PayService();
        EventService eventService = new EventService(myEventRepository);
        TicketService ticketService = new TicketService(myTicketRepository, payService);

        eventService.createEvent(eventName, "Adal", "FancyParty", 10, 80);

        List<Event> availableEvents = eventService.getEvents();
        for(Event event : availableEvents)
        {
            if(event.getName().equals("AdalParty"))
            {
                ticketService.createTicket(customerName,event);
                ticketService.createTicket(customerName2,event);
            }
        }

        for(Event event : availableEvents)
        {
            System.out.println(eventName);
            for(Ticket ticket : event.getTickets())
            {
                System.out.println("--" + ticket.getOwner());
            }
        }

        if (eventService.checkTicketValid(eventName, customerName))
        {
            System.out.println("You have a ticket! Yes");
        }
        eventService.useTicket(eventName, customerName);
        if (!eventService.checkTicketValid(eventName, customerName))
        {
            System.out.println("Your ticket is not valid :(");
        }
    }
}