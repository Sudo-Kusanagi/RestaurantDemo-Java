public class Menu {
    private static int totalMenuItems = 0;
    private String nama;
    private double harga;
    private String kategori;
    private int stok;
    private boolean tersedia;

    public Menu(String nama, double harga, String kategori, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.stok = stok;
        this.tersedia = (stok > 0);
        totalMenuItems++;
    }

    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public String getKategori() { return kategori; }
    public int getStok() { return stok; }
    public boolean isTersedia() { return tersedia; }

    public static int getTotalMenuItems() {
        return totalMenuItems;
    }

    public boolean kurangiStok(int jumlah) {
        if (jumlah > 0 && jumlah <= stok && tersedia) {
            stok -= jumlah;
            if (stok == 0) tersedia = false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return nama + " - Rp" + harga + " (" + kategori + ") - Stok: " + stok;
    }
}