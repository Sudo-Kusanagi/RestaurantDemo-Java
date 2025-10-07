public class Customer {
    private static int customerCounter = 0;
    private int customerId;
    private String nama;
    private String noTelepon;
    private Menu[] pesanan;
    private int[] jumlahPesanan;
    private int jumlahItemPesanan;
    private double totalBayar;

    public Customer(String nama, String noTelepon) {
        this.customerId = ++customerCounter;
        this.nama = nama;
        this.noTelepon = noTelepon;
        this.pesanan = new Menu[10];
        this.jumlahPesanan = new int[10];
        this.jumlahItemPesanan = 0;
        this.totalBayar = 0.0;
    }

    public String getNama() { return nama; }
    public double getTotalBayar() { return totalBayar; }
    public int getJumlahItemPesanan() { return jumlahItemPesanan; }

    public static int getTotalCustomers() {
        return customerCounter;
    }

    public boolean tambahPesanan(Menu menu, int jumlah) {
        if (menu != null && jumlah > 0 && jumlahItemPesanan < pesanan.length
                && menu.isTersedia() && menu.getStok() >= jumlah) {

            pesanan[jumlahItemPesanan] = menu;
            jumlahPesanan[jumlahItemPesanan] = jumlah;
            jumlahItemPesanan++;

            if (menu.kurangiStok(jumlah)) {
                totalBayar += menu.getHarga() * jumlah;
                return true;
            }
        }
        return false;
    }

    public void tampilkanPesanan() {
        System.out.println("=== PESANAN " + nama + " ===");
        if (jumlahItemPesanan == 0) {
            System.out.println("Belum ada pesanan.");
            return;
        }
        for (int i = 0; i < jumlahItemPesanan; i++) {
            Menu item = pesanan[i];
            int qty = jumlahPesanan[i];
            System.out.println((i + 1) + ". " + item.getNama() + " x" + qty + " = Rp" + (item.getHarga() * qty));
        }
        System.out.println("Total: Rp" + totalBayar);
    }

    @Override
    public String toString() {
        return "Customer: " + nama + " (" + noTelepon + ") - Total: Rp" + totalBayar;
    }
}