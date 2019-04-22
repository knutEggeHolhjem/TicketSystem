import org.junit.Before;
import org.junit.Test;

import javax.mail.MessagingException;

import static junit.framework.TestCase.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Mail_Test {

    @Test
    public void sends_ticket_on_mail(){ //if it doesnt work, it might be firewall, so try turning it off and try again, should work with code
        try{
            Mail.sendMail("TicketSystem88@gmail.com","Owner","Event");
        }catch(MessagingException e){
            fail();
        }
        assertTrue(true);
    }
}
