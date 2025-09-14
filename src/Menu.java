/**
 * Kelas Menu - merepresentasikan item makanan/minuman di restoran
 * Menunjukkan konsep: Class, Object, Constructor, Static Variables
 */
public class Menu {
    // Static variable - menghitung total item menu yang dibuat
    private static int totalMenuItems = 0;

    // Instance variables
    private String nama;
    private double harga;
    private String kategori;
    private boolean tersedia;
    private int stok;

    // Constructor default
    public Menu() {
        this.nama = "Item Tidak Dikenal";
        this.harga = 0.0;
        this.kategori = "Umum";
        this.tersedia = true;
        this.stok = 0;
        totalMenuItems++; // Increment static counter
    }

    // Constructor dengan parameter
    public Menu(String nama, double harga, String kategori, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.stok = stok;
        // IF dengan multiple operands - cek ketersediaan berdasarkan stok dan harga
        this.tersedia = (stok > 0 && harga > 0);
        totalMenuItems++; // Increment static counter
    }

    // Constructor dengan parameter lengkap
    public Menu(String nama, double harga, String kategori, int stok, boolean tersedia) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.stok = stok;
        this.tersedia = tersedia;
        totalMenuItems++;
    }

    // Getter methods
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public String getKategori() { return kategori; }
    public boolean isTersedia() { return tersedia; }
    public int getStok() { return stok; }

    // Static method untuk mendapatkan total menu items
    public static int getTotalMenuItems() {
        return totalMenuItems;
    }

    // Method untuk mengurangi stok (Passing by Value example)
    public boolean kurangiStok(int jumlah) {
        // IF dengan multiple operands
        if (jumlah > 0 && jumlah <= this.stok && this.tersedia) {
            this.stok -= jumlah;
            if (this.stok == 0) {
                this.tersedia = false;
            }
            return true;
        }
        return false;
    }

    // Method untuk menambah stok
    public void tambahStok(int jumlah) {
        if (jumlah > 0) {
            this.stok += jumlah;
            this.tersedia = true;
        }
    }

    @Override
    public String toString() {
        return String.format("%s - Rp%.2f (%s) - Stok: %d - %s",
                nama, harga, kategori, stok,
                tersedia ? "Tersedia" : "Habis");
    }
}