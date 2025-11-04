package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.Produk;
import main.java.com.upb.agripos.model.Benih;
import main.java.com.upb.agripos.model.AlatPertanian;
import main.java.com.upb.agripos.model.ObatHama;
import main.java.com.upb.agripos.util.CreditBy;
import main.java.com.upb.agripos.model.Pupuk;



public class MainPolymorphism {
    public static void main(String[] args) {
        // Demonstrasi overloading
        Produk contoh = new Produk("P-001", "Contoh Produk", 10000, 10);
        contoh.tambahStok(5);
        contoh.tambahStok(2.5);

        System.out.println("\n=== Demonstrasi Dynamic Binding ===\n");

        // Dynamic binding
        Produk[] daftarProduk = {
            new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64"),
            new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea"),
            new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja"),
            new ObatHama("OBT-301", "Racun Serangga", 120000, 25, "Deltamethrin")
        };

        for (Produk p : daftarProduk) {
            System.out.println(p.getInfo());
        }


        // Credit info
        CreditBy.print("", "");
    }
}
