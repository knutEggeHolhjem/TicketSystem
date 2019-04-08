public class Main
{
    public static void main(String[] args)
    {
        //EventRepository holds a collection of all events
        EventRepository myEventRepository = new EventRepository();

        //TicketRepository holds a collection of all tickets
        TicketRepository myTicketRepository = new TicketRepository();

        //PayService is a simple service to do some payment things with.
        PayService payService = new PayService();
        //Event service is used to do things with events like creating.
        EventService eventService = new EventService(myEventRepository);

        //TicketService is used to do things with tickets like creating.
        TicketService ticketService = new TicketService(myTicketRepository, payService);

        //Console app is the UI
        ConsoleApp app = new ConsoleApp(ticketService, eventService);
        app.start();
    }
}