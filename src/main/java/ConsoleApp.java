import java.util.List;
import java.util.Scanner;

public class ConsoleApp
{
    private TicketService ticketService;
    private EventService eventService;
    private Scanner scanner;

    public ConsoleApp(TicketService ticketService, EventService eventService)
    {
        this.ticketService = ticketService;
        this.eventService = eventService;
        scanner = new Scanner(System.in);
    }

    public void start()
    {
        boolean exit = false;
        while (!exit)
        {
            System.out.println("Type 1 to create event:");
            System.out.println("Type 2 to create ticket:");
            System.out.println("Type 3 to view events and participants:");
            System.out.println("Type 4 to refund ticket:");
            System.out.println("Type 5 to check if ticket is valid:");
            System.out.println("Type 6 to use ticket:");
            System.out.println("Type 99 to exit:");
            int input = Integer.parseInt(scanner.nextLine());

            switch (input)
            {
                case 1:
                    createEvent();
                    break;
                case 2:
                    createTicket();
                    break;
                case 3:
                    viewEventsAndParticipants();
                    break;
                case 4:
                    refundTicket();
                    break;
                case 5:
                    checkIfTicketIsValid();
                    break;
                case 6:
                    useTicket();
                    break;
                case 99:
                    exit = true;
                    break;

            }
            System.out.println();
        }
    }

    private void createEvent()
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
    }

    private void createTicket()
    {
        List<Event> availableEvents = eventService.getEvents();
        for (Event event : availableEvents)
        {
            System.out.println(event.getName());
        }

        System.out.println("Select event by writing the event name:");
        String eventToPurchaseTicket = scanner.nextLine();

        System.out.println("CustomerName: ");
        String customerName = scanner.nextLine();

        for (Event event : availableEvents)
        {
            if (event.getName().equals(eventToPurchaseTicket))
            {
                ticketService.createTicket(customerName, event);
            }
        }
    }

    private void viewEventsAndParticipants()
    {
        List<Event> availableEvents = eventService.getEvents();
        for(Event event : availableEvents)
        {
            System.out.println("-" + event.getName());
            for(Ticket ticket : event.getTickets())
            {
                System.out.println("--" + ticket.getOwner());
            }
        }
    }

    private void refundTicket()
    {
        List<Event> availableEvents = eventService.getEvents();
        for (Event event : availableEvents)
        {
            System.out.println(event.getName());
        }

        System.out.println("What event do you want to refund your ticket for?");
        String eventName = scanner.nextLine();
        System.out.println("What name did you use when buying the ticket?");
        String customerName = scanner.nextLine();

        for (Event event : availableEvents)
        {
            if (event.getName().equals(eventName))
            {
                ticketService.refundTicket(customerName, event);
            }
        }

        viewEventsAndParticipants();
    }

    private void checkIfTicketIsValid()
    {
        System.out.println("What event do you want to refund your ticket for?");
        String eventName = scanner.nextLine();
        System.out.println("What name did you use when buying the ticket?");
        String customerName = scanner.nextLine();

        if (eventService.checkTicketValid(eventName, customerName))
        {
            System.out.println("You have a ticket! Yes");
        }
    }

    private void useTicket()
    {
        System.out.println("What event do you want to refund your ticket for?");
        String eventName = scanner.nextLine();
        System.out.println("What name did you use when buying the ticket?");
        String customerName = scanner.nextLine();

        eventService.useTicket(eventName, customerName);
    }
}
