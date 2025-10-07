
public class Order {
    private static int orderCounter = 0;
    private int orderId;
    private Customer customer;
    private String status;
    private double totalHarga;

    public Order(Customer customer) {
        this.orderId = ++orderCounter;
        this.customer = customer;
        this.status = "Pending";
        this.totalHarga = customer.getTotalBayar();
    }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public String getStatus() { return status; }
    public double getTotalHarga() { return totalHarga; }

    public static int getTotalOrders() {
        return orderCounter;
    }

    public void updateStatus(String statusBaru) {
        this.status = statusBaru;
    }

    @Override
    public String toString() {
        return "Order #" + orderId + " - " + customer.getNama() + " - " + status + " - Rp" + totalHarga;
    }
}