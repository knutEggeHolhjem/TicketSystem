public class Event
{
    private int eventId;
    private String name;

    public Event(int eventId, String name) {
        this.eventId = eventId;
        this.name = name;
    }

    public int getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }
}
