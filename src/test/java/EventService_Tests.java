import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class EventService_Tests
{

    private EventService eventService;

    @Before
    public void setup()
    {
        EventRepository eventRepository = new EventRepository();
        eventService = new EventService(eventRepository);
    }

    @Test
    public void creates_event()
    {
        boolean created = eventService.createEvent("Name", "Location", "Description", 10, 100);

        List<String> events = eventService.getEvents()
                .stream()
                .map(x -> x.getName())
                .collect(Collectors.toList());

        assertEquals("Name", String.join(", ", events));
        assert(created);
    }

    @Test
    public void creates_multiple_events()
    {
        eventService.createEvent("Name", "Location", "Description", 10, 100);
        eventService.createEvent("AdalParty", "Adal", "SickParty", 10, 100);
        eventService.getEvents().size();

        assertEquals(2, eventService.getEvents().size());
    }

    @Test
    public void does_not_create_new_event_when_one_exist()
    {
        eventService.createEvent("Name", "Location", "Description", 10, 100);
        boolean created = eventService.createEvent("Name", "Location", "Description", 10, 100);

        assert(!created);
        assertEquals(1, eventService.getEvents().size());
    }

    @Test
    public void removes_event()
    {
        eventService.createEvent("Name", "Location", "Description", 10, 100);
        eventService.removeEvent("Name");

        List<String> events = eventService.getEvents()
                .stream()
                .map(x -> x.getName())
                .collect(Collectors.toList());

        assert(!events.contains("Name"));
    }

}
