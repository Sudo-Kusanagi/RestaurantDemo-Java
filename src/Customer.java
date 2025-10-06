public class Customer {
    private static int customerCounter = 0;
    private int customerId;
    private String nama;
    private String noTelepon;
    private Menu[] pesanan;
    private int[] jumlahPesanan;
    private int jumlahItemPesanan;
    private double totalBayar;

    public Customer() {
        this.customerId = ++customerCounter;
        this.nama = "Customer" + customerId;
        this.noTelepon = "000-000-0000";
        this.pesanan = new Menu[10];
        this.jumlahPesanan = new int[10];
        this.jumlahItemPesanan = 0;
        this.totalBayar = 0.0;
    }

    public Customer(String nama, String noTelepon) {
        this.customerId = ++customerCounter;
        this.nama = nama;
        this.noTelepon = noTelepon;
        this.pesanan = new Menu[10];
        this.jumlahPesanan = new int[10];
        this.jumlahItemPesanan = 0;
        this.totalBayar = 0.0;
    }

    public int getCustomerId() { return customerId; }
    public String getNama() { return nama; }
    public String getNoTelepon() { return noTelepon; }
    public double getTotalBayar() { return totalBayar; }
    public int getJumlahItemPesanan() { return jumlahItemPesanan; }

    public static int getTotalCustomers() {
        return customerCounter;
    }

    public boolean tambahPesanan(Menu menu, int jumlah) {
        if (menu != null && jumlah > 0 && jumlahItemPesanan < pesanan.length
                && menu.isTersedia() && menu.getStok() >= jumlah) {
            boolean sudahAda = false;
            for (int i = 0; i < jumlahItemPesanan; i++) {
                if (pesanan[i].getNama().equals(menu.getNama())) {
                    jumlahPesanan[i] += jumlah;
                    sudahAda = true;
                    break;
                }
            }
            if (!sudahAda) {
                pesanan[jumlahItemPesanan] = menu;
                jumlahPesanan[jumlahItemPesanan] = jumlah;
                jumlahItemPesanan++;
            }
            if (menu.kurangiStok(jumlah)) {
                totalBayar += menu.getHarga() * jumlah;
                return true;
            }
        }
        return false;
    }

    public void tampilkanPesanan() {
        System.out.println("\n=== PESANAN " + nama + " (ID: " + customerId + ") ===");
        if (jumlahItemPesanan == 0) {
            System.out.println("Belum ada pesanan.");
            return;
        }
        for (int i = 0; i < pesanan.length; i++) {
            if (i >= jumlahItemPesanan) {
                continue;
            }

            Menu item = pesanan[i];
            int qty = jumlahPesanan[i];
            double subtotal = item.getHarga() * qty;

            System.out.printf("%d. %s x%d = Rp%.2f\n",
                    (i + 1), item.getNama(), qty, subtotal);
        }

        System.out.printf("Total: Rp%.2f\n", totalBayar);
    }

    public Menu[] getPesananArray() {
        return pesanan;
    }

    @Override
    public String toString() {
        return String.format("Customer[ID: %d, Nama: %s, Tel: %s, Total: Rp%.2f]",
                customerId, nama, noTelepon, totalBayar);
    }
}