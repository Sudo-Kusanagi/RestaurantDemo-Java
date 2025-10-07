public class Restaurant {
    private static final double PAJAK_RESTORAN = 0.10;
    private static final double DISKON_MEMBER = 0.05;

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

    public static String formatRupiah(double amount) {
        return "Rp" + amount;
    }
}