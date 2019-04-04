import java.util.List;
import java.util.Scanner;

public class CustomerUI
{
    private TicketService ticketService;
    private EventService eventService;
    private Scanner scanner;

    public CustomerUI(TicketService ticketService, EventService eventService) {
        this.ticketService = ticketService;
        this.eventService = eventService;
        scanner = new Scanner(System.in);
    }

    public void start()
    {
        boolean exit = false;
        while (!exit)
        {
            System.out.println("Type 1 to create ticket:");
            System.out.println("Type 2 to view events and participants:");
            System.out.println("Type 3 to refund ticket:");
            System.out.println("Type 4 to check if ticket is valid:");
            System.out.println("Type 5 to use ticket:");
            System.out.println("Type 99 to exit to main menu:");
            int input = Integer.parseInt(scanner.nextLine());

            switch (input)
            {
                case 1:
                    createTicket();
                    break;
                case 2:
                    viewEventsAndParticipants();
                    break;
                case 3:
                    refundTicket();
                    break;
                case 4:
                    checkIfTicketIsValid();
                    break;
                case 5:
                    useTicket();
                    break;
                case 99:
                    exit = true;
                    break;

            }
            System.out.println();
        }
    }

    private void createTicket()
    {
        List<Event> availableEvents = eventService.getEvents();
        System.out.println("Available events:");
        for (Event event : availableEvents)
        {
            System.out.println("-" + event.getName());
        }

        System.out.println("Select event by writing the event name:");
        String eventToPurchaseTicket = scanner.nextLine();

        System.out.println("CustomerName: ");
        String customerName = scanner.nextLine();

        for (Event event : availableEvents)
        {
            if (event.getName().equals(eventToPurchaseTicket))
            {
                if (ticketService.createTicket(customerName, event))
                {
                    System.out.println("Ticket created successfully");
                }
                else
                {
                    System.out.println("Something went wrong when crating the ticket :( ");
                }
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

        System.out.println("For waht event do you want to refund your ticket?");
        String eventName = scanner.nextLine();
        System.out.println("What name did you use when buying the ticket?");
        String customerName = scanner.nextLine();

        for (Event event : availableEvents)
        {
            if (event.getName().equals(eventName))
            {
                if (ticketService.refundTicket(customerName, event))
                {
                    System.out.println("The ticket was refunded");
                }
                else
                {
                    System.out.println("The ticket was not valid for a refund");
                }
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

    private void checkIfTicketIsValid()
    {
        System.out.println("For what event do you want to check if your ticket is valid?");
        String eventName = scanner.nextLine();
        System.out.println("What name did you use when buying the ticket?");
        String customerName = scanner.nextLine();

        if (eventService.checkTicketValid(eventName, customerName))
        {
            System.out.println("You have a valid ticket! Yes");
        }
        else
        {
            System.out.println("Your ticket is not valid :(");
        }
    }

    private void useTicket()
    {
        System.out.println("What event do you want to use your ticket for?");
        String eventName = scanner.nextLine();
        System.out.println("What name did you use when buying the ticket?");
        String customerName = scanner.nextLine();

        if (eventService.useTicket(eventName, customerName))
        {
            System.out.println("The ticket was used successfully");
        }
        else
        {
            System.out.println("Something went wrong when using the ticket :(");
        }
    }
}