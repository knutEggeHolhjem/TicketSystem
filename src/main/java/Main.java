import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String eventName = scanner.nextLine();
        String customerName = scanner.nextLine();

        EventRepository myEventRepository = new EventRepository();
        TicketRepository myTicketRepository = new TicketRepository();

        EventService eventService = new EventService(myEventRepository, myTicketRepository);

        eventService.createEvent(eventName, "Adal", "FancyParty", 10, 80);
    }
}