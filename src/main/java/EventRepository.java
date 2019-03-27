import java.util.ArrayList;
import java.util.Optional;

public class EventRepository
{
    private ArrayList<Event> events = new ArrayList<Event>();

    public void add(Event event)
    {
        events.add(event);
    }

    public Event get(String name)
    {
        Optional<Event> possibleEvent = events.stream().filter(x -> x.getName().equals(name)).findFirst();
        return possibleEvent.orElse(null);
    }

}
