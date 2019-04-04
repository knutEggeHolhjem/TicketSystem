import java.util.List;
import java.util.Scanner;

public class ConsoleApp
{
    private Scanner scanner;

    private OrganizerUI organizerUI;
    private CustomerUI customerUI;


    public ConsoleApp(TicketService ticketService, EventService eventService)
    {
        scanner = new Scanner(System.in);
        organizerUI = new OrganizerUI(ticketService, eventService);
        customerUI = new CustomerUI(ticketService, eventService);
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
                case 99:
                    exit = true;
                    break;

            }
            System.out.println();
        }
    }
}
