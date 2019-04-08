import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;

import static junit.framework.TestCase.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Mail_Test {

    private EventService eventService;
    private TicketService ticketService;
    private PayService payService;

    @Before
    public void setup()
    {
        EventRepository eventRepository = new EventRepository();
        TicketRepository ticketRepository = new TicketRepository();
        payService = new PayService();
        ticketService = new TicketService(ticketRepository, payService);
        eventService = new EventService(eventRepository);
    }

    @Test
    public void sends_ticket_to_mail(){ //if it doesnt work, it might be firewall, so try turning it off and try again, should work with code
        try{
            Mail.sendMail("TicketSystem88@gmail.com","Owner","Event");
        }catch(MessagingException e){
            fail();
        }
        assertTrue(true);
    }
}
