# Laporan Praktikum Minggu 7
Topik: Collections dan Implementasi Keranjang Belanja

## Identitas
- Nama  : Egalian Lalintang
- NIM   : 240202833
- Kelas : 3IKRA

---

## Tujuan
1. Mahasiswa mampu menjelaskan konsep collection dalam Java (List, Map, Set).
2. Mahasiswa mampu menggunakan ArrayList untuk menyimpan dan mengelola objek secara dinamis.
3. Mahasiswa dapat melakukan operasi dasar pada collection seperti menambah, menghapus, dan menghitung total data.
4. Mahasiswa mampu menganalisis penggunaan struktur data yang tepat untuk kasus Keranjang Belanja Agri-POS.


---

## Dasar Teori
1. Java Collections Framework adalah arsitektur terpadu yang menyediakan serangkaian interface dan kelas untuk mengelola sekumpulan objek secara efisien.

2. ArrayList (List): Koleksi linier yang menjaga urutan masuknya data dan memperbolehkan nilai yang sama (duplikat) muncul lebih dari sekali.

3. HashMap (Map): Koleksi berbasis pasangan kunci-nilai (key-value). Menggunakan kunci unik untuk mengambil data secara instan.

4. HashSet (Set): Koleksi yang fokus pada keunikan, di mana tidak ada dua elemen yang identik di dalamnya, dan urutannya biasanya tidak beraturan.

---

## Langkah Praktikum
1. Konfigurasi Proyek: Inisialisasi struktur proyek dengan menyusun package com.upb.agripos pada direktori sumber utama.

2. Definisi Objek (Model): Membangun kerangka data melalui class Product yang mencakup identitas barang (kode, nama, harga) dilengkapi metode akses data (getter).

3. Pengembangan Logika Bisnis: Menyusun class ShoppingCart yang memanfaatkan ArrayList sebagai wadah dinamis untuk mengelola koleksi objek Product.

4. Uji Coba Sistem: Membuat program utama (MainCart) sebagai simulator interaksi pengguna, mulai dari input barang hingga proses penghapusan dan pencetakan invoice.

5. Finalisasi & Pelaporan: Menjalankan aplikasi dengan menyertakan identitas mahasiswa (Nama/NIM) pada output, kemudian mendokumentasikan hasilnya dalam bentuk tangkapan layar.

---

## Kode Program
package main.java.com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {
        System.out.println("Hello, I am [Egalian Lalintang]-[240202833] (Week7)");

        Product p1 = new Product("P01", "Beras", 50000);
        Product p2 = new Product("P02", "Pupuk", 30000);

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.printCart();

        cart.removeProduct(p1);
        cart.printCart();
    }
}


package main.java.com.upb.agripos;

public class Product {
    private final String code;
    private final String name;
    private final double price;

    public Product(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}


package main.java.com.upb.agripos; 

import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Product> items = new ArrayList<>();

    public void addProduct(Product p) {
        items.add(p);
    }

    public void removeProduct(Product p) {
        items.remove(p);
    }

    public double getTotal() {
        double sum = 0;
        for (Product p : items) {
            sum += p.getPrice();
        }
        return sum;
    }

    public void printCart() {
        System.out.println("Isi Keranjang:");
        for (Product p : items) {
            System.out.println("- " + p.getCode() + " " + p.getName() + " = Rp" + p.getPrice());
        }
        System.out.println("Total: Rp" + getTotal());
    }
}


package main.java.com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartMap {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product p) { items.put(p, items.getOrDefault(p, 0) + 1); }

    public void removeProduct(Product p) {
        if (!items.containsKey(p)) return;
        int qty = items.get(p);
        if (qty > 1) items.put(p, qty - 1);
        else items.remove(p);
    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void printCart() {
        System.out.println("Isi Keranjang (Map):");
        for (Map.Entry<Product, Integer> e : items.entrySet()) {
            System.out.println("- " + e.getKey().getCode() + " " + e.getKey().getName() + " x" + e.getValue());
        }
        System.out.println("Total: " + getTotal());
    }
}
```

---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![hasil ](image.png)
)
---

## Analisis
(
Mekanisme Program: Aplikasi menginisialisasi objek Product yang kemudian dikelola oleh kelas ShoppingCart. Melalui metode addProduct(), objek tersebut disimpan secara dinamis di dalam ArrayList. Penghitungan total belanja dilakukan dengan mengiterasi (looping) seluruh elemen dalam list untuk mengakumulasi nilai harga setiap produk.

Keunggulan Koleksi Dinamis: Berbeda dengan Array konvensional (Product[]) yang memiliki kapasitas kaku, ArrayList memberikan fleksibilitas karena ukurannya dapat menyesuaikan secara otomatis dengan jumlah data yang masuk. Hal ini secara signifikan mengurangi risiko kesalahan saat akses data, seperti IndexOutOfBoundsException.

Analisis Kendala: Tantangan teknis muncul pada logika penghapusan data menggunakan items.remove(p). Metode ini bekerja dengan membandingkan referensi memori objek. Jika objek yang ingin dihapus berbeda referensi (meskipun isinya identik), penghapusan akan gagal kecuali metode equals() didefinisikan ulang. Dalam proyek ini, kendala tersebut diatasi dengan menggunakan referensi variabel yang konsisten.
)
---

## Kesimpulan
Penggunaan Java Collections Framework, khususnya ArrayList, dalam proyek ini membuktikan bahwa pengelolaan data kelompok objek dapat dilakukan dengan lebih fleksibel dan efisien dibandingkan menggunakan Array statis. Dengan memanfaatkan ArrayList, program mampu menampung objek Product secara dinamis tanpa perlu menentukan kapasitas maksimal di awal, sehingga menghindari risiko error akibat keterbatasan indeks. Proses otomatisasi seperti penambahan barang melalui addProduct() dan penghitungan total harga melalui iterasi list menunjukkan bagaimana struktur data ini mempermudah logika bisnis pada aplikasi Point of Sale (POS). Meskipun terdapat tantangan teknis dalam hal referensi objek saat proses penghapusan data, pemahaman terhadap mekanisme memori Java memastikan sistem dapat berjalan dengan akurat. Secara keseluruhan, integrasi antara model data yang terstruktur dan koleksi yang dinamis menjadi kunci utama dalam membangun aplikasi yang adaptif dan minim kesalahan.
---

## Quiz
1. Perbedaan Mendasar List, Map, dan Set
List: Bertindak sebagai koleksi linear yang menjaga urutan elemen berdasarkan indeks. Fitur utamanya adalah fleksibilitas dalam menerima elemen duplikat.
Map: Merupakan struktur data berbasis pemetaan kunci-nilai (Key-Value). Kunci harus bersifat eksklusif (unik) agar sistem dapat melakukan pencarian nilai secara instan.
Set: Berfokus pada integritas data, di mana koleksi ini menolak elemen yang sama. Urutan elemen dalam Set biasanya tidak menjadi prioritas.

2. Relevansi ArrayList dalam Keranjang Belanja ArrayList sangat ideal untuk keranjang belanja karena sifatnya yang dinamis dan kronologis. Dalam transaksi, urutan barang yang dimasukkan pelanggan perlu dipertahankan untuk pencetakan struk. Selain itu, karena kita tidak tahu pasti berapa banyak barang yang akan dibeli pelanggan, kemampuan ArrayList untuk memperluas kapasitasnya secara otomatis jauh lebih efisien dibandingkan Array biasa.

3. Mekanisme Set dalam Mencegah Duplikasi Set menjamin keunikan data melalui proses validasi internal. Saat sebuah elemen ditambahkan, Set (khususnya HashSet) akan mengevaluasi hash code dan memanggil metode equals() pada objek tersebut. Jika ditemukan kecocokan dengan data yang sudah ada, objek baru tersebut tidak akan disimpan, sehingga memastikan setiap entitas di dalamnya tetap tunggal.

4. Kapan Menggunakan Map vs List (Beserta Contoh) Map lebih unggul saat kita memerlukan pencarian cepat atau hubungan asosiatif antar data. Gunakan Map jika Anda ingin mengelompokkan data berdasarkan label atau identitas unik tertentu.
Contoh: Dalam sistem inventaris yang kompleks, daripada membuat daftar panjang berisi barang yang sama secara berulang (List), kita bisa menggunakan Map<Product, Integer>. Dengan cara ini, "Minyak Goreng" cukup muncul sekali sebagai Key, sedangkan jumlah botolnya disimpan sebagai Value (misalnya: 5 pcs).
