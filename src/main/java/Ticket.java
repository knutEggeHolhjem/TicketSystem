public class Ticket
{
    private String eventName;
    private String owner;

    public Ticket(String eventName, String owner) {
        this.eventName = eventName;
        this.owner = owner;
    }

    public String getEventName() {
        return eventName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


}
