import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean createEvents = true;
        boolean createTickets = true;
        String customerName;
        EventRepository myEventRepository = new EventRepository();
        TicketRepository myTicketRepository = new TicketRepository();

        PayService payService = new PayService();
        EventService eventService = new EventService(myEventRepository);
        TicketService ticketService = new TicketService(myTicketRepository, payService);

        while(createEvents)
        {
            System.out.println("Event name: ");
            String eventName = scanner.nextLine();

            System.out.println("Event Location: ");
            String eventLocation = scanner.nextLine();

            System.out.println("Event Description: ");
            String eventDescription = scanner.nextLine();

            System.out.println("Event MaxNumberOfTickets: ");
            int eventMaxNumberOfTickets = Integer.parseInt(scanner.nextLine());

            System.out.println("Event TicketPrice: ");
            int eventTicketPrice = Integer.parseInt(scanner.nextLine());

            eventService.createEvent(eventName, eventLocation, eventDescription, eventMaxNumberOfTickets, eventTicketPrice);

            System.out.println("if you want to create more events write yes/no");
            String answer = scanner.nextLine();
            if (answer.equals("yes"))
                createEvents = true;
            else if (answer.equals("no"))
                createEvents = false;

        }

        List<Event> availableEvents = eventService.getEvents();
        while(createTickets)
        {

            for (Event event : availableEvents)
            {
                System.out.println(event.getName());
            }

            System.out.println("Write name of event to purchase ticket");
            String eventToPurchaseTicket = scanner.nextLine();

            System.out.println("CustomerName: ");
            customerName = scanner.nextLine();

            for (Event event : availableEvents)
            {
                if (event.getName().equals(eventToPurchaseTicket))
                {
                    ticketService.createTicket(customerName, event);
                }
            }
            System.out.println("if you want to create more tickets write yes/no");
            String answer = scanner.nextLine();
            if (answer.equals("yes"))
                createTickets = true;
            else if (answer.equals("no"))
                createTickets = false;
        }

        for(Event event : availableEvents)
        {
            System.out.println("-" + event.getName());
            for(Ticket ticket : event.getTickets())
            {
                System.out.println("--" + ticket.getOwner());
            }
        }

        /*if (eventService.checkTicketValid(eventToPurchaseTicket, customerName))
        {
            System.out.println("You have a ticket! Yes");
        }
        eventService.useTicket(eventToPurchaseTicket, customerName);
        if (!eventService.checkTicketValid(eventToPurchaseTicket, customerName))
        {
            System.out.println("Your ticket is not valid :(");
        }*/
    }
}