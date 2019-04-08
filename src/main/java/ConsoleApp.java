import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp
{
    private Scanner scanner;

    private OrganizerUI organizerUI;
    private CustomerUI customerUI;
    private EventService eventService;


    public ConsoleApp(TicketService ticketService, EventService eventService)
    {
        scanner = new Scanner(System.in);
        organizerUI = new OrganizerUI(ticketService, eventService);
        customerUI = new CustomerUI(ticketService, eventService);
        this.eventService = eventService;
    }

    public void start()
    {
        boolean exit = false;
        while (!exit)
        {
            System.out.println("Type 1 to enter organizer menu:");
            System.out.println("Type 2 to enter customer menu:");
            System.out.println("Type 99 to exit:");
            int input = Integer.parseInt(scanner.nextLine());

            switch (input)
            {
                case 1:
                    organizerUI.start();
                    break;
                case 2:
                    customerUI.start();
                    break;
                case 4: //Hidden commands to make testing easier by bypassing the creation of events that is needed to test tickets and multiple events
                    eventService.createEvent("Name", "Location", "Description", 10, 100, 10, "", "");
                    break;
                case 5:
                    eventService.createEvent("Nam", "Location", "Description", 10, 100, 10,"","");
                    break;
                case 99:
                    exit = true;
                    break;
            }
            System.out.println();
        }
    }

    public static void viewEventsAndParticipants(List<Event> availableEvents) {
        if (availableEvents.isEmpty()) {
            System.out.println("There are no available events: try again later, or make your own");
        } else {
            System.out.println("Number of available events: " + availableEvents.size());
            for (Event event : availableEvents) {
                if(!event.hasEventStarted(System.currentTimeMillis())) { //Event doesn't show up in list if it has already started
                    Calendar start = event.getStartDate();
                    DateFormat df = new SimpleDateFormat("dd:MM:yyyy");
                    System.out.println("-" + event.getName() + ", Date of event: " +df.format(start.getTime()) +", Tickets left: " + event.getNumberOfLeftOverTickets() );
                    for (Ticket ticket : event.getTickets()) {
                        System.out.println("--" + ticket.getOwner());
                    }
                } else {
                    System.out.println("-" + event.getName() + ", Date of event: ENDED");
                }
            }
        }
    }
}
