import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private static int orderCounter = 0;
    private static double totalPendapatanHarian = 0.0;
    private int orderId;
    private Customer customer;
    private LocalDateTime waktuPesan;
    private String status;
    private double totalHarga;
    private int estimasiMenit;

    public Order() {
        this.orderId = ++orderCounter;
        this.customer = new Customer();
        this.waktuPesan = LocalDateTime.now();
        this.status = "Pending";
        this.totalHarga = 0.0;
        this.estimasiMenit = 15;
    }

    public Order(Customer customer) {
        this.orderId = ++orderCounter;
        this.customer = customer;
        this.waktuPesan = LocalDateTime.now();
        this.status = "Pending";
        this.totalHarga = customer.getTotalBayar();
        if (customer.getJumlahItemPesanan() <= 2) {
            this.estimasiMenit = 10;
        } else if (customer.getJumlahItemPesanan() <= 5) {
            this.estimasiMenit = 20;
        } else {
            this.estimasiMenit = 30;
        }
    }

    public Order(Customer customer, String status, int estimasiMenit) {
        this.orderId = ++orderCounter;
        this.customer = customer;
        this.waktuPesan = LocalDateTime.now();
        this.status = status;
        this.totalHarga = customer.getTotalBayar();
        this.estimasiMenit = estimasiMenit;
    }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public String getStatus() { return status; }
    public double getTotalHarga() { return totalHarga; }
    public int getEstimasiMenit() { return estimasiMenit; }

    public static int getTotalOrders() {
        return orderCounter;
    }

    public static double getTotalPendapatanHarian() {
        return totalPendapatanHarian;
    }

    public void updateStatus(String statusBaru) {
        String[] statusValid = {"Pending", "Diproses", "Selesai", "Dibatalkan"};
        boolean validStatus = false;
        for (int i = 0; i < statusValid.length; i++) {
            if (statusValid[i].equals(statusBaru)) {
                validStatus = true;
                break;
            }
        }
        if (validStatus) {
            this.status = statusBaru;
            if (statusBaru.equals("Selesai")) {
                totalPendapatanHarian += this.totalHarga;
            }
        }
    }

    public void prosesOrder() {
        String[] tahapanProses = {"Menerima Pesanan", "Menyiapkan Bahan",
                "Memasak", "Penyelesaian", "Siap Disajikan"};

        System.out.println("\n=== MEMPROSES ORDER #" + orderId + " ===");

        int tahapan = 0;
        while (tahapan < tahapanProses.length) {
            System.out.println("Tahap " + (tahapan + 1) + ": " + tahapanProses[tahapan]);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Proses terganggu!");
                break;
            }

            tahapan++;
        }

        this.updateStatus("Selesai");
        System.out.println("Order #" + orderId + " telah selesai!");
    }

    public void tampilkanDetailOrder() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("\n=== DETAIL ORDER #" + orderId + " ===");
        System.out.println("Waktu Pesan: " + waktuPesan.format(formatter));
        System.out.println("Status: " + status);
        System.out.println("Estimasi: " + estimasiMenit + " menit");
        System.out.println("Customer: " + customer.getNama() + " (" + customer.getNoTelepon() + ")");

        customer.tampilkanPesanan();

        System.out.println("Total Order: Rp" + String.format("%.2f", totalHarga));
    }

    @Override
    public String toString() {
        return String.format("Order[#%d, %s, %s, Rp%.2f]",
                orderId, customer.getNama(), status, totalHarga);
    }
}