public class RestaurantUtils {
    private static final double PAJAK_RESTORAN = 0.10;
    private static final double DISKON_MEMBER = 0.05;
    private static final double MINIMUM_ORDER_GRATIS_ONGKIR = 50000.0;
    private static final char GRADE_A = 'A';
    private static final char GRADE_B = 'B';
    private static final char GRADE_C = 'C';

    public static double hitungPajak(double totalHarga) {
        return totalHarga * PAJAK_RESTORAN;
    }

    public static char hitungGradeRestoran(double totalPendapatan) {
        int batasGradeA = 500000;
        double batasGradeB = 200000.0;
        int pendapatanInt = (int) totalPendapatan;
        double batasA = batasGradeA;
        float persentaseTarget = (float) (totalPendapatan / batasGradeA * 100);
        if (totalPendapatan >= batasA) {
            return GRADE_A;
        } else if (totalPendapatan >= batasGradeB) {
            return GRADE_B;
        } else {
            return GRADE_C;
        }
    }

    public static double hitungDiskonMember(double totalHarga) {
        return totalHarga * DISKON_MEMBER;
    }

    public static double hitungTotalAkhir(double totalHarga, boolean isMember) {
        double pajak = hitungPajak(totalHarga);
        double diskon = isMember ? hitungDiskonMember(totalHarga) : 0.0;
        return totalHarga + pajak - diskon;
    }

    public static boolean cekGratisOngkir(double totalHarga) {
        return totalHarga >= MINIMUM_ORDER_GRATIS_ONGKIR;
    }

    public static int[] generateNomorMeja(int jumlahCustomer) {
        int[] mejaTersedia = new int[20];
        int indexMeja = 0;
        for (int i = 1; i <= 20; i++) {
            mejaTersedia[indexMeja] = i;
            indexMeja++;
        }
        for (int i = 0; i < mejaTersedia.length; i++) {
            int randomIndex = (int)(Math.random() * mejaTersedia.length);
            int temp = mejaTersedia[i];
            mejaTersedia[i] = mejaTersedia[randomIndex];
            mejaTersedia[randomIndex] = temp;
        }
        int[] hasil = new int[jumlahCustomer];
        for (int i = 0; i < jumlahCustomer && i < mejaTersedia.length; i++) {
            hasil[i] = mejaTersedia[i];
        }
        return hasil;
    }

    public static boolean validasiKategoriMenu(String kategori) {
        String[] kategoriValid = {"Makanan", "Minuman", "Dessert", "Appetizer", "Main Course"};
        for (int i = 0; i < kategoriValid.length; i++) {
            if (kategoriValid[i] == null) {
                continue;
            }
            if (kategoriValid[i].equalsIgnoreCase(kategori)) {
                return true;
            }
        }
        return false;
    }

    public static Menu cariMenuTermahal(Menu[] daftarMenu) {
        if (daftarMenu == null || daftarMenu.length == 0) {
            return null;
        }
        Menu termahal = daftarMenu[0];
        for (int i = 1; i < daftarMenu.length; i++) {
            if (daftarMenu[i] != null && daftarMenu[i].getHarga() > termahal.getHarga()) {
                termahal = daftarMenu[i];
            }
        }
        return termahal;
    }

    public static double hitungRataRataHarga(Menu[] daftarMenu) {
        if (daftarMenu == null || daftarMenu.length == 0) {
            return 0.0;
        }
        double totalHarga = 0.0;
        int jumlahMenu = 0;
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

    public static String formatRupiah(double amount) {
        return String.format("Rp%,.2f", amount);
    }

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
        for (int i = 0; i < daftarOrder.length; i++) {
            if (daftarOrder[i] == null) {
                continue;
            }
            totalOrderHariIni++;
            if (daftarOrder[i].getStatus().equals("Selesai")) {
                orderSelesai++;
                totalPendapatan += daftarOrder[i].getTotalHarga();
            } else if (daftarOrder[i].getStatus().equals("Dibatalkan")) {
                orderDibatalkan++;
            }
        }
        System.out.println("Total Order Hari Ini: " + totalOrderHariIni);
        System.out.println("Order Selesai: " + orderSelesai);
        System.out.println("Order Dibatalkan: " + orderDibatalkan);
        System.out.println("Total Pendapatan: " + formatRupiah(totalPendapatan));
        System.out.println("Rata-rata per Order: " +
                formatRupiah(orderSelesai > 0 ? totalPendapatan / orderSelesai : 0.0));
        System.out.println("=====================================");
    }
}