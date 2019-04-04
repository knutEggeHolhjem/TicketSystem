public class Main
{
    public static void main(String[] args)
    {
        EventRepository myEventRepository = new EventRepository();
        TicketRepository myTicketRepository = new TicketRepository();

        PayService payService = new PayService();
        EventService eventService = new EventService(myEventRepository);
        TicketService ticketService = new TicketService(myTicketRepository, payService);

        ConsoleApp app = new ConsoleApp(ticketService, eventService);
        app.start();
    }
}