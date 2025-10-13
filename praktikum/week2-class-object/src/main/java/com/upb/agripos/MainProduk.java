package com.upb.agripos;

import com.upb.agripos.model.Produk;
import com.upb.agripos.util.CreditBy;

public class MainProduk {
    public static void main(String[] args) {
        Produk p1 = new Produk("KPA-001", "Kipas Angin", 100000, 100);
        Produk p2 = new Produk("KMP-101", "Kompor Gas", 150000, 40);
        Produk p3 = new Produk("STR-501", "Setrika", 170000, 15);

        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());
  // Transaksi penambahan stok
        p1.tambahStok(20);  // stok datang dari gudang

        // Transaksi pengurangan stok
        p1.kurangiStok(15); // produk dibeli pelanggan
      
        // Tampilkan identitas mahasiswa
        CreditBy.print("240202833", "Egalian Lalintang");
    }
}