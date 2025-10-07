import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private static final double PAJAK_RESTORAN = 0.10;
    private static final double DISKON_MEMBER = 0.05;

    private String nama;
    private List<Menu> daftarMenu;

    public Restaurant(String nama) {
        this.nama = nama;
        this.daftarMenu = new ArrayList<>();
    }

    public void tambahMenu(String namaMenu, double harga, String kategori, int stok) {
        daftarMenu.add(new Menu(namaMenu, harga, kategori, stok));
    }

    public void tampilkanMenu() {
        System.out.println("=== MENU " + nama + " ===");
        for (int i = 0; i < daftarMenu.size(); i++) {
            System.out.println((i + 1) + ". " + daftarMenu.get(i));
        }
        System.out.println("Total Menu Items: " + daftarMenu.size());
    }

    public Menu getMenu(int index) {
        if (index >= 0 && index < daftarMenu.size()) {
            return daftarMenu.get(index);
        }
        return null;
    }

    public List<Menu> getDaftarMenu() {
        return daftarMenu;
    }

    public int getJumlahMenu() {
        return daftarMenu.size();
    }

    public static double hitungPajak(double totalHarga) {
        return totalHarga * PAJAK_RESTORAN;
    }

    public static double hitungDiskonMember(double totalHarga) {
        return totalHarga * DISKON_MEMBER;
    }

    public static double hitungTotalAkhir(double totalHarga, boolean isMember) {
        double pajak = hitungPajak(totalHarga);
        double diskon = isMember ? hitungDiskonMember(totalHarga) : 0.0;
        return totalHarga + pajak - diskon;
    }

    public static String formatRupiah(double amount) {
        return "Rp" + amount;
    }

    public String getNama() {
        return nama;
    }
}
