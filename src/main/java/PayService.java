public class PayService
{
    public boolean payment(String customer ,int amount)
    {
        return amount > 50;
    }

    public boolean returnMoney(String customer ,int amount)
    {
        return amount > 50;
    }
}
