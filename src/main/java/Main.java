import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String eventName = scanner.nextLine();
        String customerName = scanner.nextLine();

        Event myEvent = new Event(1, eventName);
        Ticket myTicket = new Ticket(1, myEvent.getEventId(), customerName);

        System.out.println("Event: " + myEvent.getName() + " Customer:  " + myTicket.getOwner());
    }
}