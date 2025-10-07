public class RestaurantDemo {

    public static void main(String[] args) {
        System.out.println("=== SISTEM MANAJEMEN RESTORAN ===\n");

        System.out.println("1. MEMBUAT DAFTAR MENU");
        Menu[] daftarMenu = new Menu[4];
        daftarMenu[0] = new Menu("Nasi Goreng", 25000, "Main Course", 15);
        daftarMenu[1] = new Menu("Ayam Bakar", 30000, "Main Course", 10);
        daftarMenu[2] = new Menu("Es Teh Manis", 8000, "Minuman", 20);
        daftarMenu[3] = new Menu("Jus Alpukat", 15000, "Minuman", 8);

        for (int i = 0; i < daftarMenu.length; i++) {
            System.out.println((i + 1) + ". " + daftarMenu[i]);
        }
        System.out.println("Total Menu Items: " + Menu.getTotalMenuItems());

        System.out.println("\n2. MEMBUAT CUSTOMER");
        Customer customer1 = new Customer("Budi Santoso", "081234567890");
        System.out.println(customer1);
        System.out.println("Total Customers: " + Customer.getTotalCustomers());

        System.out.println("\n3. PROSES PEMESANAN");
        customer1.tambahPesanan(daftarMenu[0], 2);
        customer1.tambahPesanan(daftarMenu[2], 2);
        customer1.tampilkanPesanan();

        System.out.println("\n4. PERHITUNGAN TAGIHAN");
        double subtotal = customer1.getTotalBayar();
        double pajak = RestaurantUtils.hitungPajak(subtotal);
        double total = RestaurantUtils.hitungTotalAkhir(subtotal, true);

        System.out.println("Subtotal: " + RestaurantUtils.formatRupiah(subtotal));
        System.out.println("Pajak: " + RestaurantUtils.formatRupiah(pajak));
        System.out.println("Total (Member): " + RestaurantUtils.formatRupiah(total));

        System.out.println("\n5. MEMBUAT ORDER");
        Order order1 = new Order(customer1);
        System.out.println(order1);
        System.out.println("Total Orders: " + Order.getTotalOrders());
    }
}