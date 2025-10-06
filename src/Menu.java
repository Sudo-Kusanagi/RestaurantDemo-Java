public class Menu {
    private static int totalMenuItems = 0;
    private String nama;
    private double harga;
    private String kategori;
    private boolean tersedia;
    private int stok;
    private char rating;

    public Menu() {
        this.nama = "Item Tidak Dikenal";
        this.harga = 0.0;
        this.kategori = "Umum";
        this.tersedia = true;
        this.stok = 0;
        this.rating = 'C';
        totalMenuItems++;
    }

    public Menu(String nama, double harga, String kategori, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.stok = stok;
        this.tersedia = (stok > 0 && harga > 0);
        this.rating = hitungRatingMenu(harga, stok);
        totalMenuItems++;
    }

    public Menu(String nama, double harga, String kategori, int stok, boolean tersedia) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.stok = stok;
        this.tersedia = tersedia;
        this.rating = hitungRatingMenu(harga, stok);
        totalMenuItems++;
    }

    private char hitungRatingMenu() {
        int skorHarga = 0;
        int skorStok = 0;
        int hargaInt = (int) this.harga;
        double faktorHarga = hargaInt;
        if (faktorHarga >= 25000) skorHarga = 3;
        else if (faktorHarga >= 15000) skorHarga = 2;
        else skorHarga = 1;
        if (this.stok >= 15) skorStok = 3;
        else if (this.stok >= 10) skorStok = 2;
        else skorStok = 1;
        int totalSkor = skorHarga + skorStok;
        if (totalSkor >= 5) return 'A';
        else if (totalSkor >= 3) return 'B';
        else return 'C';
    }

    private char hitungRatingMenu(double harga, int stok) {
        int lokalSkor = 0;
        float hargaFloat = (float) harga;
        lokalSkor += (hargaFloat >= 20000) ? 2 : 1;
        lokalSkor += (stok >= 10) ? 2 : 1;
        return (lokalSkor >= 3) ? 'A' : 'B';
    }

    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public String getKategori() { return kategori; }
    public boolean isTersedia() { return tersedia; }
    public int getStok() { return stok; }
    public char getRating() { return rating; }

    public static int getTotalMenuItems() {
        return totalMenuItems;
    }

    public boolean kurangiStok(int jumlah) {
        if (jumlah > 0 && jumlah <= this.stok && this.tersedia) {
            this.stok -= jumlah;
            if (this.stok == 0) {
                this.tersedia = false;
            }
            return true;
        }
        return false;
    }

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