import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class EventRepository
{
    private ArrayList<Event> events = new ArrayList<>();

    public void add(Event event)
    {
        events.add(event);
    }

    public Event get(String name)
    {
        Optional<Event> possibleEvent = events.stream().filter(x -> x.getName().equals(name)).findFirst();
        return possibleEvent.orElse(null);
    }

    public void remove(String name)
    {
        events.remove(get(name));
    }

    public Stream<Event> query()
    {
        return events.stream();
    }
}
