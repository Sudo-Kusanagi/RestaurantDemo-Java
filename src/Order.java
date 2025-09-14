import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Kelas Order - merepresentasikan pesanan di restoran
 * Menunjukkan konsep: Object relationships, Constructor, Instance methods
 */
public class Order {
    // Static variables
    private static int orderCounter = 0;
    private static double totalPendapatanHarian = 0.0;

    // Instance variables
    private int orderId;
    private Customer customer;
    private LocalDateTime waktuPesan;
    private String status; // "Pending", "Diproses", "Selesai", "Dibatalkan"
    private double totalHarga;
    private int estimasiMenit;

    // Constructor default
    public Order() {
        this.orderId = ++orderCounter;
        this.customer = new Customer();
        this.waktuPesan = LocalDateTime.now();
        this.status = "Pending";
        this.totalHarga = 0.0;
        this.estimasiMenit = 15; // Default 15 menit
    }

    // Constructor dengan parameter
    public Order(Customer customer) {
        this.orderId = ++orderCounter;
        this.customer = customer;
        this.waktuPesan = LocalDateTime.now();
        this.status = "Pending";
        this.totalHarga = customer.getTotalBayar();

        // IF dengan multiple operands - hitung estimasi berdasarkan jumlah item
        if (customer.getJumlahItemPesanan() <= 2) {
            this.estimasiMenit = 10;
        } else if (customer.getJumlahItemPesanan() <= 5) {
            this.estimasiMenit = 20;
        } else {
            this.estimasiMenit = 30;
        }
    }

    // Constructor lengkap
    public Order(Customer customer, String status, int estimasiMenit) {
        this.orderId = ++orderCounter;
        this.customer = customer;
        this.waktuPesan = LocalDateTime.now();
        this.status = status;
        this.totalHarga = customer.getTotalBayar();
        this.estimasiMenit = estimasiMenit;
    }

    // Getter methods
    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public String getStatus() { return status; }
    public double getTotalHarga() { return totalHarga; }
    public int getEstimasiMenit() { return estimasiMenit; }

    // Static methods
    public static int getTotalOrders() {
        return orderCounter;
    }

    public static double getTotalPendapatanHarian() {
        return totalPendapatanHarian;
    }

    // Method untuk mengubah status order
    public void updateStatus(String statusBaru) {
        String[] statusValid = {"Pending", "Diproses", "Selesai", "Dibatalkan"};
        boolean validStatus = false;

        // Loop untuk validasi status
        for (int i = 0; i < statusValid.length; i++) {
            if (statusValid[i].equals(statusBaru)) {
                validStatus = true;
                break;
            }
        }

        if (validStatus) {
            this.status = statusBaru;

            // Jika status selesai, tambahkan ke pendapatan harian
            if (statusBaru.equals("Selesai")) {
                totalPendapatanHarian += this.totalHarga;
            }
        }
    }

    // Method untuk simulasi proses order dengan loop dan sleep
    public void prosesOrder() {
        String[] tahapanProses = {"Menerima Pesanan", "Menyiapkan Bahan",
                "Memasak", "Penyelesaian", "Siap Disajikan"};

        System.out.println("\n=== MEMPROSES ORDER #" + orderId + " ===");

        // Loop while untuk simulasi proses
        int tahapan = 0;
        while (tahapan < tahapanProses.length) {
            System.out.println("Tahap " + (tahapan + 1) + ": " + tahapanProses[tahapan]);

            // Simulasi waktu proses (dalam detik, bukan menit untuk demo)
            try {
                Thread.sleep(1000); // 1 detik per tahapan
            } catch (InterruptedException e) {
                System.out.println("Proses terganggu!");
                break; // Break jika ada gangguan
            }

            tahapan++;
        }

        this.updateStatus("Selesai");
        System.out.println("Order #" + orderId + " telah selesai!");
    }

    // Method untuk menampilkan detail order
    public void tampilkanDetailOrder() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("\n=== DETAIL ORDER #" + orderId + " ===");
        System.out.println("Waktu Pesan: " + waktuPesan.format(formatter));
        System.out.println("Status: " + status);
        System.out.println("Estimasi: " + estimasiMenit + " menit");
        System.out.println("Customer: " + customer.getNama() + " (" + customer.getNoTelepon() + ")");

        // Tampilkan pesanan customer
        customer.tampilkanPesanan();

        System.out.println("Total Order: Rp" + String.format("%.2f", totalHarga));
    }

    @Override
    public String toString() {
        return String.format("Order[#%d, %s, %s, Rp%.2f]",
                orderId, customer.getNama(), status, totalHarga);
    }
}