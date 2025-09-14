/**
 * Kelas Utility untuk operasi-operasi umum restoran
 * Menunjukkan konsep: Static Methods, Utility Class, Passing by Value/Reference
 */
public class RestaurantUtils {

    // Static variables untuk konfigurasi restoran
    private static final double PAJAK_RESTORAN = 0.10; // 10% pajak
    private static final double DISKON_MEMBER = 0.05; // 5% diskon member
    private static final double MINIMUM_ORDER_GRATIS_ONGKIR = 50000.0;

    // Static method untuk menghitung pajak (Passing by Value)
    public static double hitungPajak(double totalHarga) {
        // Parameter totalHarga adalah passing by value
        return totalHarga * PAJAK_RESTORAN;
    }

    // Static method untuk menghitung diskon member (Passing by Value)
    public static double hitungDiskonMember(double totalHarga) {
        return totalHarga * DISKON_MEMBER;
    }

    // Static method untuk menghitung total dengan pajak dan diskon
    public static double hitungTotalAkhir(double totalHarga, boolean isMember) {
        double pajak = hitungPajak(totalHarga);
        double diskon = isMember ? hitungDiskonMember(totalHarga) : 0.0;

        return totalHarga + pajak - diskon;
    }

    // Static method untuk mengecek gratis ongkir
    public static boolean cekGratisOngkir(double totalHarga) {
        return totalHarga >= MINIMUM_ORDER_GRATIS_ONGKIR;
    }

    // Static method untuk generate nomor meja berdasarkan jumlah customer
    public static int[] generateNomorMeja(int jumlahCustomer) {
        // Array untuk menyimpan nomor meja yang tersedia
        int[] mejaTersedia = new int[20]; // Asumsikan ada 20 meja
        int indexMeja = 0;

        // Loop untuk mengisi array meja yang tersedia
        for (int i = 1; i <= 20; i++) {
            mejaTersedia[indexMeja] = i;
            indexMeja++;
        }

        // Shuffle array untuk randomize (simple shuffle)
        for (int i = 0; i < mejaTersedia.length; i++) {
            int randomIndex = (int)(Math.random() * mejaTersedia.length);
            // Swap elements
            int temp = mejaTersedia[i];
            mejaTersedia[i] = mejaTersedia[randomIndex];
            mejaTersedia[randomIndex] = temp;
        }

        // Return array dengan jumlah yang diminta
        int[] hasil = new int[jumlahCustomer];
        for (int i = 0; i < jumlahCustomer && i < mejaTersedia.length; i++) {
            hasil[i] = mejaTersedia[i];
        }

        return hasil; // Passing by Reference - mengembalikan array
    }

    // Static method untuk validasi menu berdasarkan kategori
    public static boolean validasiKategoriMenu(String kategori) {
        String[] kategoriValid = {"Makanan", "Minuman", "Dessert", "Appetizer", "Main Course"};

        // Loop dengan continue dan break
        for (int i = 0; i < kategoriValid.length; i++) {
            if (kategoriValid[i] == null) {
                continue; // Skip null values
            }

            if (kategoriValid[i].equalsIgnoreCase(kategori)) {
                return true; // Break loop implicitly with return
            }
        }

        return false;
    }

    // Static method untuk mencari menu paling mahal dalam array (Passing by Reference)
    public static Menu cariMenuTermahal(Menu[] daftarMenu) {
        if (daftarMenu == null || daftarMenu.length == 0) {
            return null;
        }

        Menu termahal = daftarMenu[0];

        // Loop untuk mencari menu termahal
        for (int i = 1; i < daftarMenu.length; i++) {
            if (daftarMenu[i] != null && daftarMenu[i].getHarga() > termahal.getHarga()) {
                termahal = daftarMenu[i];
            }
        }

        return termahal; // Return reference ke object Menu
    }

    // Static method untuk menghitung rata-rata harga menu
    public static double hitungRataRataHarga(Menu[] daftarMenu) {
        if (daftarMenu == null || daftarMenu.length == 0) {
            return 0.0;
        }

        double totalHarga = 0.0;
        int jumlahMenu = 0;

        // While loop untuk menghitung total
        int i = 0;
        while (i < daftarMenu.length) {
            if (daftarMenu[i] != null) {
                totalHarga += daftarMenu[i].getHarga();
                jumlahMenu++;
            }
            i++;
        }

        return jumlahMenu > 0 ? totalHarga / jumlahMenu : 0.0;
    }

    // Static method untuk format currency Indonesia
    public static String formatRupiah(double amount) {
        return String.format("Rp%,.2f", amount);
    }

    // Static method untuk generate laporan harian (Passing by Reference)
    public static void generateLaporanHarian(Order[] daftarOrder) {
        if (daftarOrder == null) {
            System.out.println("Tidak ada data order untuk laporan.");
            return;
        }

        System.out.println("\n======= LAPORAN HARIAN RESTORAN =======");

        int totalOrderHariIni = 0;
        double totalPendapatan = 0.0;
        int orderSelesai = 0;
        int orderDibatalkan = 0;

        // Loop untuk menghitung statistik
        for (int i = 0; i < daftarOrder.length; i++) {
            if (daftarOrder[i] == null) {
                continue; // Skip null orders
            }

            totalOrderHariIni++;

            // IF dengan multiple operands
            if (daftarOrder[i].getStatus().equals("Selesai")) {
                orderSelesai++;
                totalPendapatan += daftarOrder[i].getTotalHarga();
            } else if (daftarOrder[i].getStatus().equals("Dibatalkan")) {
                orderDibatalkan++;
            }
        }

        // Tampilkan statistik
        System.out.println("Total Order Hari Ini: " + totalOrderHariIni);
        System.out.println("Order Selesai: " + orderSelesai);
        System.out.println("Order Dibatalkan: " + orderDibatalkan);
        System.out.println("Total Pendapatan: " + formatRupiah(totalPendapatan));
        System.out.println("Rata-rata per Order: " +
                formatRupiah(orderSelesai > 0 ? totalPendapatan / orderSelesai : 0.0));
        System.out.println("=====================================");
    }
}