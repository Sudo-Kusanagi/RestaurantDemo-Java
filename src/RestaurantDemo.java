/**
 * Kelas Demo untuk menjalankan simulasi Sistem Manajemen Restoran
 * Menunjukkan semua konsep Java yang diminta dalam tugas
 *
 * Konsep yang ditunjukkan:
 * - Good Programming Styles
 * - IF dengan multiple operands
 * - Loop (for, while, continue, break)
 * - Array dengan array.length
 * - Class dan Object
 * - Static Variables dan Methods
 * - Constructor (default dan dengan parameter)
 * - Passing by Value & Passing by Reference
 * - Main Method
 */
public class RestaurantDemo {

    public static void main(String[] args) {
        System.out.println("=== SISTEM MANAJEMEN RESTORAN JAVA ===\n");

        // 1. MEMBUAT MENU - Constructor, Static Variables, Array
        System.out.println("1. MEMBUAT DAFTAR MENU");

        Menu[] daftarMenu = new Menu[6]; // Array declaration

        // Constructor dengan parameter
        daftarMenu[0] = new Menu("Nasi Goreng", 25000, "Main Course", 15);
        daftarMenu[1] = new Menu("Ayam Bakar", 30000, "Main Course", 10);
        daftarMenu[2] = new Menu("Es Teh Manis", 8000, "Minuman", 20);
        daftarMenu[3] = new Menu("Jus Alpukat", 15000, "Minuman", 8);
        daftarMenu[4] = new Menu(); // Constructor default
        daftarMenu[5] = new Menu("Es Krim", 12000, "Dessert", 0, false); // Habis

        // Loop FOR dengan array.length
        for (int i = 0; i < daftarMenu.length; i++) {
            System.out.println((i + 1) + ". " + daftarMenu[i]);
        }

        // Static method call
        System.out.println("Total Menu Items: " + Menu.getTotalMenuItems());

        // 2. MEMBUAT CUSTOMER - Constructor variations
        System.out.println("\n2. MEMBUAT CUSTOMER");

        Customer customer1 = new Customer("Budi Santoso", "081234567890");
        Customer customer2 = new Customer(); // Constructor default

        System.out.println(customer1);
        System.out.println(customer2);
        System.out.println("Total Customers: " + Customer.getTotalCustomers());

        // 3. PROSES PEMESANAN - Passing by Reference, IF multiple operands
        System.out.println("\n3. PROSES PEMESANAN");

        // Passing by Reference (Menu objects)
        boolean pesan1 = customer1.tambahPesanan(daftarMenu[0], 2); // Nasi Goreng x2
        boolean pesan2 = customer1.tambahPesanan(daftarMenu[2], 2); // Es Teh x2
        boolean pesan3 = customer1.tambahPesanan(daftarMenu[5], 1); // Es Krim (habis)

        // IF dengan multiple operands
        if (pesan1 && pesan2) {
            System.out.println("✓ Pesanan utama berhasil!");
        }

        if (!pesan3 || daftarMenu[5].getStok() <= 0) {
            System.out.println("✗ Es Krim tidak tersedia!");
        }

        customer1.tampilkanPesanan();

        // 4. LOOP WHILE, CONTINUE, BREAK - Pencarian menu
        System.out.println("\n4. PENCARIAN MENU");

        String[] cariMenu = {"Nasi", "Pizza", "Teh", "Soto"};

        for (int i = 0; i < cariMenu.length; i++) {
            String kata = cariMenu[i];
            boolean found = false;
            int j = 0;

            System.out.print("Cari '" + kata + "': ");

            // WHILE LOOP dengan CONTINUE dan BREAK
            while (j < daftarMenu.length) {
                if (daftarMenu[j] == null) {
                    j++;
                    continue; // CONTINUE statement
                }

                if (daftarMenu[j].getNama().toLowerCase().contains(kata.toLowerCase())) {
                    System.out.println("FOUND - " + daftarMenu[j].getNama());
                    found = true;
                    break; // BREAK statement
                }
                j++;
            }

            if (!found) {
                System.out.println("NOT FOUND");
            }
        }

        // 5. STATIC UTILITY METHODS - Passing by Value
        System.out.println("\n5. PERHITUNGAN TAGIHAN");

        // Passing by Value (primitive types)
        double subtotal = customer1.getTotalBayar();
        double pajak = RestaurantUtils.hitungPajak(subtotal);
        double total = RestaurantUtils.hitungTotalAkhir(subtotal, true);

        System.out.println("Subtotal: " + RestaurantUtils.formatRupiah(subtotal));
        System.out.println("Pajak: " + RestaurantUtils.formatRupiah(pajak));
        System.out.println("Total (Member): " + RestaurantUtils.formatRupiah(total));

        // Passing by Reference (array)
        Menu termahal = RestaurantUtils.cariMenuTermahal(daftarMenu);
        System.out.println("Menu Termahal: " + termahal.getNama());

        // 6. MEMBUAT ORDER - Object relationships
        System.out.println("\n6. MEMBUAT ORDER");

        Order order1 = new Order(customer1); // Constructor dengan parameter
        Order order2 = new Order(); // Constructor default

        System.out.println(order1);
        System.out.println("Total Orders: " + Order.getTotalOrders());

        // 7. DEMONSTRASI PASSING BY VALUE vs REFERENCE
        System.out.println("\n7. PASSING BY VALUE vs REFERENCE + CASTING");

        // Passing by Value - nilai tidak berubah
        double harga = 50000;
        System.out.println("Harga sebelum: " + harga);
        RestaurantUtils.hitungPajak(harga); // tidak mengubah harga
        System.out.println("Harga setelah: " + harga); // masih sama

        // DEMONSTRASI CASTING dan TIPE DATA
        System.out.println("\nDEMO CASTING:");

        // Tipe data primitif: int, double, boolean, char
        int hargaInt = 25000;
        double hargaDouble = 25000.75;
        boolean tersedia = true;
        char grade = 'A';

        System.out.println("Original - int: " + hargaInt + ", double: " + hargaDouble +
                ", boolean: " + tersedia + ", char: " + grade);

        // EXPLICIT CASTING
        int doubleToInt = (int) hargaDouble; // double ke int
        float intToFloat = (float) hargaInt; // int ke float

        System.out.println("Explicit cast - double ke int: " + doubleToInt);
        System.out.println("Explicit cast - int ke float: " + intToFloat);

        // IMPLICIT CASTING
        double intToDouble = hargaInt; // int ke double (otomatis)
        long intToLong = hargaInt; // int ke long (otomatis)

        System.out.println("Implicit cast - int ke double: " + intToDouble);
        System.out.println("Implicit cast - int ke long: " + intToLong);

        // LINGKUP VARIABEL
        System.out.println("\nLINGKUP VARIABEL:");
        demonstrasiLingkupVariabel();

        // Passing by Reference - object berubah
        System.out.println("\nPASSING BY REFERENCE:");
        System.out.println("Stok Nasi Goreng sebelum: " + daftarMenu[0].getStok());
        daftarMenu[0].kurangiStok(3); // mengubah object
        System.out.println("Stok Nasi Goreng setelah: " + daftarMenu[0].getStok());
        System.out.println("Rating menu: " + daftarMenu[0].getRating()); // char type

        // 8. LAPORAN AKHIR dengan GRADE SISTEM
        System.out.println("\n8. LAPORAN & GRADE SISTEM");

        order1.updateStatus("Selesai");
        Order[] orders = {order1, order2};
        RestaurantUtils.generateLaporanHarian(orders);

        // Demo casting dan grade
        double totalPendapatan = Order.getTotalPendapatanHarian();
        char gradeRestoran = RestaurantUtils.hitungGradeRestoran(totalPendapatan);
        System.out.println("Grade Restoran Hari Ini: " + gradeRestoran);

        // Final complex condition dengan multiple operands
        System.out.println("\n9. REKOMENDASI MENU");
        for (int i = 0; i < daftarMenu.length; i++) {
            Menu m = daftarMenu[i];
            // Complex IF dengan &&, ||, !
            if (m != null && m.isTersedia() &&
                    (m.getKategori().equals("Main Course") || m.getKategori().equals("Minuman")) &&
                    m.getStok() > 5 && m.getHarga() <= 25000) {

                System.out.println("⭐ Rekomendasi: " + m.getNama() +
                        " - " + RestaurantUtils.formatRupiah(m.getHarga()));
            }
        }

        // SUMMARY
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

    // Method untuk demonstrasi lingkup variabel
    private static void demonstrasiLingkupVariabel() {
        // Variabel lokal (hanya ada di method ini)
        int variabelLokal = 100;
        String namaLokal = "Variabel Lokal";

        System.out.println("Variabel lokal di method: " + namaLokal + " = " + variabelLokal);

        // Akses static variable (non-lokal) dari kelas lain
        int totalMenu = Menu.getTotalMenuItems(); // static variable
        System.out.println("Static variable (non-lokal): Total Menu = " + totalMenu);

        // Variabel lokal akan hilang setelah method selesai
        // Static variables tetap ada selama program berjalan
        System.out.println("=".repeat(40));
    }
}