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
    private char rating; // Tipe data char untuk rating menu

    // Constructor default
    public Menu() {
        this.nama = "Item Tidak Dikenal";
        this.harga = 0.0;
        this.kategori = "Umum";
        this.tersedia = true;
        this.stok = 0;
        this.rating = 'C'; // Default rating
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

        // Demonstrasi casting dan operator matematika
        this.rating = hitungRatingMenu(harga, stok); // Method tanpa parameter
        totalMenuItems++; // Increment static counter
    }

    // Constructor dengan parameter lengkap
    public Menu(String nama, double harga, String kategori, int stok, boolean tersedia) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.stok = stok;
        this.tersedia = tersedia;
        this.rating = hitungRatingMenu(harga, stok);
        totalMenuItems++;
    }

    // Method tanpa parameter untuk menghitung rating
    private char hitungRatingMenu() {
        // Variabel lokal (lingkup method)
        int skorHarga = 0;
        int skorStok = 0;

        // Operator matematika dan casting
        // Explicit casting: double ke int
        int hargaInt = (int) this.harga;

        // Implicit casting: int ke double
        double faktorHarga = hargaInt; // int ke double (implicit)

        // Perhitungan menggunakan operator matematika
        if (faktorHarga >= 25000) skorHarga = 3;
        else if (faktorHarga >= 15000) skorHarga = 2;
        else skorHarga = 1;

        if (this.stok >= 15) skorStok = 3; // instance variable (non-lokal)
        else if (this.stok >= 10) skorStok = 2;
        else skorStok = 1;

        // Operator matematika untuk total skor
        int totalSkor = skorHarga + skorStok;

        // IF dengan perbandingan boolean
        if (totalSkor >= 5) return 'A';
        else if (totalSkor >= 3) return 'B';
        else return 'C';
    }

    // Method dengan parameter dan nilai balik
    private char hitungRatingMenu(double harga, int stok) {
        // Variabel lokal (berbeda dengan instance variables)
        int lokalSkor = 0;

        // Explicit casting
        float hargaFloat = (float) harga;

        // Operator matematika
        lokalSkor += (hargaFloat >= 20000) ? 2 : 1;
        lokalSkor += (stok >= 10) ? 2 : 1;

        // Return berdasarkan perhitungan
        return (lokalSkor >= 3) ? 'A' : 'B';
    }

    // Getter methods
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public String getKategori() { return kategori; }
    public boolean isTersedia() { return tersedia; }
    public int getStok() { return stok; }
    public char getRating() { return rating; } // Getter untuk char

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