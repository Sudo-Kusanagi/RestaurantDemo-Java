public class RestaurantDemo {

    public static void main(String[] args) {
        System.out.println("=== SISTEM MANAJEMEN RESTORAN JAVA ===\n");

        System.out.println("1. MEMBUAT DAFTAR MENU");

        Menu[] daftarMenu = new Menu[6];

        daftarMenu[0] = new Menu("Nasi Goreng", 25000, "Main Course", 15);
        daftarMenu[1] = new Menu("Ayam Bakar", 30000, "Main Course", 10);
        daftarMenu[2] = new Menu("Es Teh Manis", 8000, "Minuman", 20);
        daftarMenu[3] = new Menu("Jus Alpukat", 15000, "Minuman", 8);
        daftarMenu[4] = new Menu();
        daftarMenu[5] = new Menu("Es Krim", 12000, "Dessert", 0, false);

        for (int i = 0; i < daftarMenu.length; i++) {
            System.out.println((i + 1) + ". " + daftarMenu[i]);
        }

        System.out.println("Total Menu Items: " + Menu.getTotalMenuItems());

        System.out.println("\n2. MEMBUAT CUSTOMER");

        Customer customer1 = new Customer("Budi Santoso", "081234567890");
        Customer customer2 = new Customer();

        System.out.println(customer1);
        System.out.println(customer2);
        System.out.println("Total Customers: " + Customer.getTotalCustomers());

        System.out.println("\n3. PROSES PEMESANAN");

        boolean pesan1 = customer1.tambahPesanan(daftarMenu[0], 2);
        boolean pesan2 = customer1.tambahPesanan(daftarMenu[2], 2);
        boolean pesan3 = customer1.tambahPesanan(daftarMenu[5], 1);

        if (pesan1 && pesan2) {
            System.out.println("✓ Pesanan utama berhasil!");
        }

        if (!pesan3 || daftarMenu[5].getStok() <= 0) {
            System.out.println("✗ Es Krim tidak tersedia!");
        }

        customer1.tampilkanPesanan();

        System.out.println("\n4. PENCARIAN MENU");

        String[] cariMenu = {"Nasi", "Pizza", "Teh", "Soto"};

        for (int i = 0; i < cariMenu.length; i++) {
            String kata = cariMenu[i];
            boolean found = false;
            int j = 0;

            System.out.print("Cari '" + kata + "': ");

            while (j < daftarMenu.length) {
                if (daftarMenu[j] == null) {
                    j++;
                    continue;
                }

                if (daftarMenu[j].getNama().toLowerCase().contains(kata.toLowerCase())) {
                    System.out.println("FOUND - " + daftarMenu[j].getNama());
                    found = true;
                    break;
                }
                j++;
            }

            if (!found) {
                System.out.println("NOT FOUND");
            }
        }

        System.out.println("\n5. PERHITUNGAN TAGIHAN");

        double subtotal = customer1.getTotalBayar();
        double pajak = RestaurantUtils.hitungPajak(subtotal);
        double total = RestaurantUtils.hitungTotalAkhir(subtotal, true);

        System.out.println("Subtotal: " + RestaurantUtils.formatRupiah(subtotal));
        System.out.println("Pajak: " + RestaurantUtils.formatRupiah(pajak));
        System.out.println("Total (Member): " + RestaurantUtils.formatRupiah(total));

        Menu termahal = RestaurantUtils.cariMenuTermahal(daftarMenu);
        System.out.println("Menu Termahal: " + termahal.getNama());

        System.out.println("\n6. MEMBUAT ORDER");

        Order order1 = new Order(customer1);
        Order order2 = new Order();

        System.out.println(order1);
        System.out.println("Total Orders: " + Order.getTotalOrders());

        System.out.println("\n7. PASSING BY VALUE vs REFERENCE + CASTING");

        double harga = 50000;
        System.out.println("Harga sebelum: " + harga);
        RestaurantUtils.hitungPajak(harga);
        System.out.println("Harga setelah: " + harga);

        System.out.println("\nDEMO CASTING:");

        int hargaInt = 25000;
        double hargaDouble = 25000.75;
        boolean tersedia = true;
        char grade = 'A';

        System.out.println("Original - int: " + hargaInt + ", double: " + hargaDouble +
                ", boolean: " + tersedia + ", char: " + grade);

        int doubleToInt = (int) hargaDouble;
        float intToFloat = (float) hargaInt;

        System.out.println("Explicit cast - double ke int: " + doubleToInt);
        System.out.println("Explicit cast - int ke float: " + intToFloat);

        double intToDouble = hargaInt;
        long intToLong = hargaInt;

        System.out.println("Implicit cast - int ke double: " + intToDouble);
        System.out.println("Implicit cast - int ke long: " + intToLong);

        System.out.println("\nLINGKUP VARIABEL:");
        demonstrasiLingkupVariabel();

        System.out.println("\nPASSING BY REFERENCE:");
        System.out.println("Stok Nasi Goreng sebelum: " + daftarMenu[0].getStok());
        daftarMenu[0].kurangiStok(3);
        System.out.println("Stok Nasi Goreng setelah: " + daftarMenu[0].getStok());
        System.out.println("Rating menu: " + daftarMenu[0].getRating());

        System.out.println("\n8. LAPORAN & GRADE SISTEM");

        order1.updateStatus("Selesai");
        Order[] orders = {order1, order2};
        RestaurantUtils.generateLaporanHarian(orders);

        double totalPendapatan = Order.getTotalPendapatanHarian();
        char gradeRestoran = RestaurantUtils.hitungGradeRestoran(totalPendapatan);
        System.out.println("Grade Restoran Hari Ini: " + gradeRestoran);

        System.out.println("\n9. REKOMENDASI MENU");
        for (int i = 0; i < daftarMenu.length; i++) {
            Menu m = daftarMenu[i];
            if (m != null && m.isTersedia() &&
                    (m.getKategori().equals("Main Course") || m.getKategori().equals("Minuman")) &&
                    m.getStok() > 5 && m.getHarga() <= 25000) {

                System.out.println("⭐ Rekomendasi: " + m.getNama() +
                        " - " + RestaurantUtils.formatRupiah(m.getHarga()));
            }
        }

        System.out.println("\n" + "=".repeat(40));
        System.out.println("DEMO SELESAI - SEMUA KONSEP DITUNJUKKAN:");
        System.out.println("✓ Tipe data primitif (int, double, boolean, char)");
        System.out.println("✓ Tipe data objek (String, kelas buatan)");
        System.out.println("✓ Variabel (deklarasi, nilai, penggunaan)");
        System.out.println("✓ Operator matematika (+, -, *, /, %)");
        System.out.println("✓ Casting (implicit & explicit)");
        System.out.println("✓ Metode dengan/tanpa parameter & return");
        System.out.println("✓ Lingkup variabel (lokal vs non-lokal)");
        System.out.println("✓ IF & perbandingan boolean");
        System.out.println("✓ Static vs non-static");
        System.out.println("=".repeat(40));
    }

    private static void demonstrasiLingkupVariabel() {
        int variabelLokal = 100;
        String namaLokal = "Variabel Lokal";

        System.out.println("Variabel lokal di method: " + namaLokal + " = " + variabelLokal);

        int totalMenu = Menu.getTotalMenuItems();
        System.out.println("Static variable (non-lokal): Total Menu = " + totalMenu);

        System.out.println("=".repeat(40));
    }
}