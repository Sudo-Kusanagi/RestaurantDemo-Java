import java.util.ArrayList;
import java.util.List;

public class Customer {
    private static int customerCounter = 0;
    private int customerId;
    private String nama;
    private String noTelepon;
    private Restaurant restaurant;
    private List<Menu> pesanan;
    private List<Integer> jumlahPesanan;
    private double totalBayar;

    public Customer(String nama, String noTelepon, Restaurant restaurant) {
        this.customerId = ++customerCounter;
        this.nama = nama;
        this.noTelepon = noTelepon;
        this.restaurant = restaurant;
        this.pesanan = new ArrayList<>();
        this.jumlahPesanan = new ArrayList<>();
        this.totalBayar = 0.0;
    }

    public String getNama() { return nama; }
    public double getTotalBayar() { return totalBayar; }
    public int getJumlahItemPesanan() { return pesanan.size(); }
    public Restaurant getRestaurant() { return restaurant; }

    public static int getTotalCustomers() {
        return customerCounter;
    }

    public boolean tambahPesanan(Menu menu, int jumlah) {
        if (menu != null && jumlah > 0 && menu.isTersedia() && menu.getStok() >= jumlah) {

            // Check if menu already exists in order
            for (int i = 0; i < pesanan.size(); i++) {
                if (pesanan.get(i).getNama().equals(menu.getNama())) {
                    jumlahPesanan.set(i, jumlahPesanan.get(i) + jumlah);
                    if (menu.kurangiStok(jumlah)) {
                        totalBayar += menu.getHarga() * jumlah;
                        return true;
                    }
                    return false;
                }
            }

            // Add new menu item
            pesanan.add(menu);
            jumlahPesanan.add(jumlah);

            if (menu.kurangiStok(jumlah)) {
                totalBayar += menu.getHarga() * jumlah;
                return true;
            }
        }
        return false;
    }

    public void tampilkanPesanan() {
        System.out.println("=== PESANAN " + nama + " di " + restaurant.getNama() + " ===");
        if (pesanan.isEmpty()) {
            System.out.println("Belum ada pesanan.");
            return;
        }
        for (int i = 0; i < pesanan.size(); i++) {
            Menu item = pesanan.get(i);
            int qty = jumlahPesanan.get(i);
            System.out.println((i + 1) + ". " + item.getNama() + " x" + qty + " = Rp" + (item.getHarga() * qty));
        }
        System.out.println("Total: Rp" + totalBayar);
    }

    @Override
    public String toString() {
        return "Customer: " + nama + " (" + noTelepon + ") - Total: Rp" + totalBayar;
    }
}