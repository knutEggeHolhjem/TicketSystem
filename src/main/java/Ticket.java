public class Ticket
{
    private int ticketId;
    private int eventId;
    private String owner;

    public Ticket(int ticketId, int eventId, String owner) {
        this.ticketId = ticketId;
        this.eventId = eventId;
        this.owner = owner;
    }

    public int getEventId() {
        return eventId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getTicketId() {
        return ticketId;
    }
}
