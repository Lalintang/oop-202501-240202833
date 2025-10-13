# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: [Tuliskan judul topik, misalnya "Class dan Object"]

## Identitas
- Nama  : Egalian Lalintang
- NIM   : 240202833
- Kelas : 3IKRA

---

## Tujuan
1. Mahasiswa mampu menjelaskan konsep class, object, atribut, dan method dalam paradigma Object Oriented Programming (OOP) dengan baik dan benar.
2. Mahasiswa mampu menerapkan penggunaan access modifier serta prinsip enkapsulasi dalam proses pembuatan class untuk menjaga keamanan dan keteraturan data.
3. Mahasiswa mampu mengimplementasikan sebuah class ProdukPertanian yang memuat atribut dan method sesuai karakteristik objek di dunia nyata.
4. Mahasiswa mampu mendemonstrasikan proses instansiasi object dari class yang telah dibuat serta menampilkan informasi produk pertanian melalui output di console.
## Dasar Teori
Class merupakan cetak biru (blueprint) yang digunakan sebagai dasar untuk membentuk suatu objek. Objek sendiri merupakan hasil instansiasi dari class yang memiliki atribut (data) dan method (perilaku) tertentu. Dalam konsep Object Oriented Programming (OOP), prinsip enkapsulasi diterapkan dengan cara menyembunyikan data menggunakan access modifier seperti public, private, dan protected, serta memberikan akses yang terkontrol melalui method getter dan setter.

Dalam konteks Agri-POS, produk pertanian seperti benih, pupuk, dan alat pertanian dapat direpresentasikan sebagai objek yang memiliki atribut seperti nama, harga, dan stok. Dengan memanfaatkan konsep class, setiap produk dapat dibuat, dikelola, serta dimanipulasi secara lebih terstruktur dan efisien, sehingga memudahkan pengelolaan data produk pertanian dalam sistem.

---

## Langkah Praktikum
Membuat Class Produk
1.Buat sebuah file bernama Produk.java di dalam package model.
2.Tambahkan beberapa atribut, yaitu kode, nama, harga, dan stok.
3.Terapkan prinsip enkapsulasi dengan menjadikan seluruh atribut bersifat private, kemudian buat getter dan setter untuk masing-masing atribut agar dapat diakses secara aman.
Membuat Class CreditBy
1.Buat file CreditBy.java pada package util.
2.Di dalam class ini, buat sebuah method statis yang berfungsi untuk menampilkan identitas mahasiswa di bagian akhir output dengan format:
Credit by: <NIM> - <Nama>.
Membuat Objek Produk dan Menampilkan Identitas
1.Buat file MainProduk.java.
2.Lakukan instansiasi minimal tiga objek produk, misalnya “Benih Padi”, “Pupuk Urea”, dan satu produk alat pertanian lainnya.
3.Tampilkan informasi setiap produk menggunakan method getter.
4.Panggil method CreditBy.print("<NIM>", "<Nama>") di akhir program untuk menampilkan identitas mahasiswa.
Melakukan Commit dan Push
"week2-class-object", kemudian push ke repository yang telah disiapkan.

## Kode Program
Produk.java

package com.upb.agripos.model;

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

     public void tambahStok(int jumlah) {
        this.stok += jumlah;
        System.out.println("\n===TRANSAKSI MASUK===");
        System.out.println("Produk : " + nama + ", " + kode);
        System.out.println("Jumlah : +" + jumlah);
        System.out.println("Stok Sekarang : " + stok);
        System.out.println("---------------------------");
    }

    public void kurangiStok(int jumlah) {
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
            System.out.println("\n===TRANSAKSI KELUAR===");
            System.out.println("Produk : " + nama + ", " + kode);
            System.out.println("Jumlah : -" + jumlah);
            System.out.println("Stok Sekarang : " + stok);
            System.out.println("---------------------------");
        } else {
            System.out.println("\nStok " + nama + ", " + kode + " tidak mencukupi!");
            System.out.println("Stok tersedia : " + stok + " | Diminta : " + jumlah);
            System.out.println("---------------------------");
        }
    }
}

CreditBy.java
package com.upb.agripos.util;
public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }
}
MainProduk.java
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

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](/praktikum/week2-class-object/screenshots/Screenshot%20(5).png)
)
---

## Analisis
1.Cara Kerja Kode
Program utama (MainProduk.java) berfungsi untuk membuat tiga objek (p1, p2, p3) yang berasal dari class Produk.
Masing-masing objek diinisialisasi dengan data yang berbeda melalui constructor yang telah didefinisikan. Setelah itu, program menggunakan method getter seperti p1.getNama() untuk mengambil nilai atribut dari setiap objek, lalu menampilkannya ke layar console.
Dengan cara ini, data setiap produk dapat diakses dan ditampilkan secara terstruktur sesuai prinsip OOP.
2.Perbedaan dengan Praktikum Minggu Sebelumnya
Pada pertemuan kali ini, mahasiswa mulai diperkenalkan dengan paradigma pemrograman berorientasi objek (Object-Oriented Programming / OOP).
Berbeda dengan minggu sebelumnya yang masih menggunakan pendekatan prosedural—di mana program dijalankan secara berurutan dalam satu alur—pendekatan OOP mengorganisir program ke dalam class dan object yang saling berhubungan.
Perubahan ini merupakan langkah penting dalam memahami struktur program yang lebih modular, efisien, dan mudah dikembangkan.
3.Kendala yang Ditemui
Pada saat awal pengerjaan, terjadi kesalahan dalam penempatan file Produk.java, yang seharusnya berada di dalam sub-folder model.
File tersebut sempat diletakkan sejajar dengan MainProduk.java, sehingga meskipun deklarasi package com.upb.agripos.model; sudah benar, struktur folder fisik yang tidak sesuai menyebabkan error pada saat kompilasi.
Setelah file dipindahkan ke lokasi yang tepat, program akhirnya dapat dijalankan dengan benar tanpa error.

## Kesimpulan
Praktikum ini memperkenalkan konsep dasar Object-Oriented Programming (OOP), khususnya penggunaan class, object, dan enkapsulasi.
Melalui pembuatan class Produk, mahasiswa belajar mengelola data secara terstruktur menggunakan getter dan setter.
Selain itu, kesalahan dalam penempatan file memberi pemahaman penting tentang struktur package dan folder pada proyek Java.

## Quiz
1. Mengapa atribut sebaiknya dideklarasikan sebagai private dalam class?
Jawaban:
Atribut sebaiknya dideklarasikan sebagai private untuk menerapkan prinsip enkapsulasi, yaitu pembungkusan data agar tidak dapat diakses atau diubah secara langsung dari luar class. Dengan cara ini, data menjadi lebih aman dan hanya dapat dimanipulasi melalui method khusus (getter dan setter) yang telah disediakan.

2. Apa fungsi getter dan setter dalam enkapsulasi?
Jawaban:

Getter (Akses Baca): Berfungsi untuk mengambil atau membaca nilai atribut yang bersifat private. Dengan getter, bagian lain dari program dapat mengetahui isi data tanpa harus mengubahnya.
Contoh: getNama().

Setter (Akses Tulis): Digunakan untuk mengubah atau menetapkan nilai baru pada atribut private. Melalui setter, kita juga dapat menambahkan logika validasi agar nilai yang diberikan sesuai ketentuan.
Contoh: setHarga(25000).

3. Bagaimana class Produk mendukung pengembangan aplikasi POS yang lebih kompleks?
Jawaban:
Class Produk berperan sebagai rancangan dasar (blueprint) untuk membuat berbagai objek produk pertanian dalam sistem POS. Dengan pendekatan ini, kode menjadi lebih terorganisir, mudah digunakan kembali (reusable), serta mudah dikembangkan (scalable) saat aplikasi diperluas, misalnya dengan penambahan fitur manajemen stok, transaksi, atau laporan penjualan.
