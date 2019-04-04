import java.util.List;
import java.util.Scanner;

public class OrganizerUI
{
    private TicketService ticketService;
    private EventService eventService;
    private Scanner scanner;

    public OrganizerUI(TicketService ticketService, EventService eventService)
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
            System.out.println("Type 2 to view events and participants:");
            System.out.println("Type 99 to exit to main menu:");
            int input = Integer.parseInt(scanner.nextLine());

            switch (input)
            {
                case 1:
                    createEvent();
                    break;
                case 2:
                    viewEventsAndParticipants();
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

        if (eventService.createEvent(eventName, eventLocation, eventDescription, eventMaxNumberOfTickets, eventTicketPrice))
        {
            System.out.println("Event created successfully");
        }
        else
        {
            System.out.println("Something went wrong when crating the event :( ");
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

}
