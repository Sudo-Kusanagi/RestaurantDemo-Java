public class RestaurantDemo {

    public static void main(String[] args) {
        System.out.println("=== SISTEM MANAJEMEN RESTORAN ===\n");

        System.out.println("1. MEMBUAT RESTAURANT DAN MENU");
        Restaurant restaurant = new Restaurant("Warung Padang Sejati");

        restaurant.tambahMenu("Nasi Goreng", 25000, "Main Course", 15);
        restaurant.tambahMenu("Ayam Bakar", 30000, "Main Course", 10);
        restaurant.tambahMenu("Es Teh Manis", 8000, "Minuman", 20);
        restaurant.tambahMenu("Jus Alpukat", 15000, "Minuman", 8);

        restaurant.tampilkanMenu();

        System.out.println("\n2. MEMBUAT CUSTOMER");
        Customer customer1 = new Customer("Budi Santoso", "081234567890", restaurant);
        System.out.println(customer1);
        System.out.println("Total Customers: " + Customer.getTotalCustomers());

        System.out.println("\n3. PROSES PEMESANAN");
        customer1.tambahPesanan(restaurant.getMenu(0), 2);
        customer1.tambahPesanan(restaurant.getMenu(2), 2);
        customer1.tampilkanPesanan();

        System.out.println("\n4. PERHITUNGAN TAGIHAN");
        double subtotal = customer1.getTotalBayar();
        double pajak = Restaurant.hitungPajak(subtotal);
        double total = Restaurant.hitungTotalAkhir(subtotal, true);

        System.out.println("Subtotal: " + Restaurant.formatRupiah(subtotal));
        System.out.println("Pajak: " + Restaurant.formatRupiah(pajak));
        System.out.println("Total (Member): " + Restaurant.formatRupiah(total));

        System.out.println("\n5. MEMBUAT ORDER");
        Order order1 = new Order(customer1);
        System.out.println(order1);
        System.out.println("Total Orders: " + Order.getTotalOrders());

        System.out.println("\n6. DEMONSTRASI ARRAYLIST BENEFITS");
        System.out.println("Menambah menu baru ke restaurant (dinamis):");
        restaurant.tambahMenu("Sate Ayam", 20000, "Main Course", 12);
        restaurant.tambahMenu("Coca Cola", 5000, "Minuman", 25);
        restaurant.tampilkanMenu();
    }
}