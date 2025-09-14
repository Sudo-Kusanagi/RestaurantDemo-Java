/**
 * Kelas Customer - merepresentasikan pelanggan restoran
 * Menunjukkan konsep: Array, Constructor, Instance Variables
 */
public class Customer {
    // Static variable untuk ID counter
    private static int customerCounter = 0;

    // Instance variables
    private int customerId;
    private String nama;
    private String noTelepon;
    private Menu[] pesanan; // Array untuk menyimpan pesanan
    private int[] jumlahPesanan; // Array untuk menyimpan jumlah setiap pesanan
    private int jumlahItemPesanan; // Counter untuk pesanan
    private double totalBayar;

    // Constructor default
    public Customer() {
        this.customerId = ++customerCounter;
        this.nama = "Customer" + customerId;
        this.noTelepon = "000-000-0000";
        this.pesanan = new Menu[10]; // Array dengan kapasitas maksimal 10 item
        this.jumlahPesanan = new int[10];
        this.jumlahItemPesanan = 0;
        this.totalBayar = 0.0;
    }

    // Constructor dengan parameter
    public Customer(String nama, String noTelepon) {
        this.customerId = ++customerCounter;
        this.nama = nama;
        this.noTelepon = noTelepon;
        this.pesanan = new Menu[10];
        this.jumlahPesanan = new int[10];
        this.jumlahItemPesanan = 0;
        this.totalBayar = 0.0;
    }

    // Getter methods
    public int getCustomerId() { return customerId; }
    public String getNama() { return nama; }
    public String getNoTelepon() { return noTelepon; }
    public double getTotalBayar() { return totalBayar; }
    public int getJumlahItemPesanan() { return jumlahItemPesanan; }

    // Static method
    public static int getTotalCustomers() {
        return customerCounter;
    }

    // Method untuk menambah pesanan (Passing by Reference - object Menu)
    public boolean tambahPesanan(Menu menu, int jumlah) {
        // IF dengan multiple operands
        if (menu != null && jumlah > 0 && jumlahItemPesanan < pesanan.length
                && menu.isTersedia() && menu.getStok() >= jumlah) {

            // Cek apakah menu sudah ada dalam pesanan
            boolean sudahAda = false;

            // Loop untuk mencari menu yang sama
            for (int i = 0; i < jumlahItemPesanan; i++) {
                if (pesanan[i].getNama().equals(menu.getNama())) {
                    jumlahPesanan[i] += jumlah;
                    sudahAda = true;
                    break; // Break statement
                }
            }

            // Jika belum ada, tambahkan sebagai item baru
            if (!sudahAda) {
                pesanan[jumlahItemPesanan] = menu;
                jumlahPesanan[jumlahItemPesanan] = jumlah;
                jumlahItemPesanan++;
            }

            // Kurangi stok menu dan update total bayar
            if (menu.kurangiStok(jumlah)) {
                totalBayar += menu.getHarga() * jumlah;
                return true;
            }
        }
        return false;
    }

    // Method untuk menampilkan pesanan dengan Loop dan array.length
    public void tampilkanPesanan() {
        System.out.println("\n=== PESANAN " + nama + " (ID: " + customerId + ") ===");

        if (jumlahItemPesanan == 0) {
            System.out.println("Belum ada pesanan.");
            return;
        }

        // Loop menggunakan for dengan array.length
        for (int i = 0; i < pesanan.length; i++) {
            if (i >= jumlahItemPesanan) {
                continue; // Continue statement - skip iterasi
            }

            Menu item = pesanan[i];
            int qty = jumlahPesanan[i];
            double subtotal = item.getHarga() * qty;

            System.out.printf("%d. %s x%d = Rp%.2f\n",
                    (i + 1), item.getNama(), qty, subtotal);
        }

        System.out.printf("Total: Rp%.2f\n", totalBayar);
    }

    // Method untuk mendapatkan array pesanan (Passing by Reference example)
    public Menu[] getPesananArray() {
        return pesanan; // Mengembalikan reference ke array
    }

    @Override
    public String toString() {
        return String.format("Customer[ID: %d, Nama: %s, Tel: %s, Total: Rp%.2f]",
                customerId, nama, noTelepon, totalBayar);
    }
}