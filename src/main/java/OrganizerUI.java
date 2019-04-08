import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
            System.out.println("Type 1 to create an event:");
            System.out.println("Type 2 to view available events and their participants:");
            System.out.println("Type 99 to go back to the main menu:");
            int input = Integer.parseInt(scanner.nextLine());

            switch (input)
            {
                case 1:
                    createEvent();
                    break;
                case 2:
                    ConsoleApp.viewEventsAndParticipants(eventService.getEvents());
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
        System.out.println("Enter a name for your event: ");
        String eventName = scanner.nextLine();

        System.out.println("Enter the location for your event: ");
        String eventLocation = scanner.nextLine();

        System.out.println("Enter a description of your event: ");
        String eventDescription = scanner.nextLine();

        System.out.println("Enter a bank account number of your event: ");
        String bank = scanner.nextLine();

        System.out.println("Enter a contact email for your event: ");
        String email = scanner.nextLine();

        System.out.println("What is your events max number of participants: ");
        int eventMaxNumberOfTickets = Integer.parseInt(scanner.nextLine());

        System.out.println("How many days is it until your event starts: ");
        int daysUntilStart= Integer.parseInt(scanner.nextLine());

        System.out.println("Input a price for your events tickets: ");
        int eventTicketPrice = Integer.parseInt(scanner.nextLine());

        if (eventService.createEvent(eventName, eventLocation, eventDescription, eventMaxNumberOfTickets, eventTicketPrice, daysUntilStart, bank, email))
        {
            System.out.println("Your event was created successfully.\nThank you for using our service.");
        }
        else
        {
            System.out.println("Something went wrong when creating your event :( \nTry again later");
        }
    }

}
