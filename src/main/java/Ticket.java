public class Ticket
{
    private String eventName;
    private String owner;
    private Boolean isUsed;
    private double price;

    public Ticket(String eventName, String owner) {
        this.eventName = eventName;
        this.owner = owner;
        isUsed = false;
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

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
