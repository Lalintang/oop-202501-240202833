# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: Bab 3 – Inheritance

## Identitas
- Nama  : Egalian Lalintang
- NIM   : 240202833
- Kelas : 3IKRA

---

## Tujuan
- Mahasiswa mampu menjelaskan konsep inheritance (pewarisan class) dalam OOP.
-Mahasiswa mampu membuat superclass dan subclass untuk produk pertanian.
-Mahasiswa mampu mendemonstrasikan hierarki class melalui contoh kode.
-Mahasiswa mampu menggunakan super untuk memanggil konstruktor dan method parent class.
-Mahasiswa mampu membuat laporan praktikum yang menjelaskan perbedaan penggunaan inheritance dibanding class tunggal.

## Dasar Teori
Inheritance (pewarisan) adalah konsep dalam pemrograman berorientasi objek (OOP) yang memungkinkan sebuah class mewarisi sifat dan perilaku dari class lain. -Superclass adalah class induk yang berisi atribut dan fungsi umum. -Subclass adalah class turunan yang mengambil atribut dan fungsi dari superclass, lalu bisa menambahkan hal-hal baru sesuai kebutuhannya. -Kata kunci super digunakan untuk memanggil konstruktor atau method milik superclass. Dalam contoh sistem Agri-POS, kita bisa membuat class Produk sebagai class induk, lalu membuat class Benih, Pupuk, dan AlatPertanian sebagai class turunannya.

## Langkah Praktikum
1. Membuat Superclass Produk
-Gunakan class Produk dari Bab 2 sebagai superclass.
2. Membuat Subclass
-Benih.java → atribut tambahan: varietas.
-Pupuk.java → atribut tambahan: jenis pupuk (Urea, NPK, dll).
-AlatPertanian.java → atribut tambahan: material (baja, kayu, plastik).
3. Membuat Main Class
-Instansiasi minimal satu objek dari tiap subclass.
-Tampilkan data produk dengan memanfaatkan inheritance.
4. Menambahkan CreditBy
-Panggil class CreditBy untuk menampilkan identitas mahasiswa.
5. Commit dan Push
-Commit dengan pesan: week3-inheritance.

---

## Kode Program 


### 1. Produk.java
```java
package com.upb.agripos.model;
// Produk.java

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
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public int getStok() { return stok; }

    public void setKode(String kode) { this.kode = kode; }
    public void setNama(String nama) { this.nama = nama; }
    public void setHarga(double harga) { this.harga = harga; }
    public void setStok(int stok) { this.stok = stok; }

    public void tampilInfo() {
        System.out.println("Kode: " + kode);
        System.out.println("Nama: " + nama);
        System.out.println("Harga: Rp" + harga);
        System.out.println("Stok: " + stok);
    }

}

```

### 2.Benih.java
```java
package com.upb.agripos.model;

public class Benih extends Produk {
    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    public String getVarietas() { return varietas; }
    public void setVarietas(String varietas) { this.varietas = varietas; }

    public void deskripsi() {
        System.out.println("=== Benih ===");
        tampilInfo();
        System.out.println("Varietas: " + varietas);
        System.out.println("Keterangan: Benih ini cocok untuk berbagai jenis lahan dan tahan terhadap hama.");
    }
}
```
### 3. Pupuk.java
```java
package com.upb.agripos.model;

public class Pupuk extends Produk {
    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }

    public void deskripsi() {
        System.out.println("=== Pupuk ===");
        tampilInfo();
        System.out.println("Jenis: " + jenis);
        System.out.println("Keterangan: Pupuk ini membantu meningkatkan kesuburan tanah dan hasil panen.");
    }
}
```
### 4. Alat Pertanian.java
```java
package com.upb.agripos.model;


public class AlatPertanian extends Produk {
    private String material;

    public AlatPertanian(String kode, String nama, double harga, int stok, String material) {
        super(kode, nama, harga, stok);
        this.material = material;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

        public void deskripsi() {
        System.out.println("=== Alat Pertanian ===");
        tampilInfo();
        System.out.println("Material: " + material);
        System.out.println("Keterangan: Alat pertanian ini terbuat dari material kuat dan tahan lama.");
    }

}
```
### 5.CreditBy.java
```java
package com.upb.agripos.util;

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\n=== Credit By ===");
        System.out.println("NIM  : " + nim);
        System.out.println("Nama : " + nama);
    }
}
```
### 6.Mainlnheritance.java
```java
package com.upb.agripos;

import com.upb.agripos.model.*;
import com.upb.agripos.util.CreditBy;

public class MainInheritance {
    public static void main(String[] args) {
        Benih b = new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64");
        Pupuk p = new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea");
        AlatPertanian a = new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja");

        b.deskripsi();
        System.out.println();
        p.deskripsi();
        System.out.println();
        a.deskripsi();
        System.out.println();

        CreditBy.print("240202833", "Egalian Lalintang");
    }
}
```


## Hasil Eksekusi
<img width="1920" height="1080" alt="Screenshot (10)" src="https://github.com/user-attachments/assets/e335ee2e-e92b-46cc-ba3e-0c006c39cd21" />
---

## Analisis
Pada praktikum minggu ini, saya menerapkan konsep inheritance di Java dengan menjadikan Produk sebagai superclass, serta Benih, Pupuk, dan AlatPertanian sebagai subclass yang mewarisi atribut dan method dari Produk.
Awalnya saya mengalami kendala karena struktur package tidak sesuai dengan deklarasi package com.upb.agripos.model;, sehingga file tidak dikenali saat dijalankan.
Solusi: menyesuaikan struktur folder menjadi src/main/java/com/upb/agripos/... sesuai dengan penulisan package pada setiap file. Setelah itu, program berhasil dikompilasi dan dijalankan tanpa error.
Class Produk berisi atribut umum (kode, nama, harga, stok) dan method tampilInfo() untuk menampilkan data produk.
Tiga subclass dibuat untuk menambahkan atribut khusus:
Benih → atribut tambahan varietas
Pupuk → atribut tambahan jenis
AlatPertanian → atribut tambahan material
Masing-masing subclass memiliki method deskripsi() yang menampilkan detail produk, serta memanggil method tampilInfo() dari superclass menggunakan pewarisan.
Pada MainInheritance, saya membuat objek dari setiap subclass dan menambahkan System.out.println() agar hasil antar produk tidak menempel saat ditampilkan di konsol.
Class CreditBy ditempatkan di package util untuk menampilkan identitas mahasiswa.
Dibandingkan minggu sebelumnya, pendekatan ini lebih efisien dan terstruktur karena dengan inheritance, atribut dan method umum cukup ditulis sekali di superclass. Hal ini membuat kode lebih rapi, mudah dibaca, dan mudah dikembangkan. 

---

## Kesimpulan
Dengan menerapkan konsep inheritance, class Produk dapat digunakan kembali oleh berbagai subclass tanpa harus menulis ulang kode yang sama, sehingga lebih efisien. Subclass seperti Benih, Pupuk, dan AlatPertanian dapat menambahkan atribut khusus sesuai kebutuhannya masing-masing — misalnya varietas untuk benih, jenis untuk pupuk, serta material dan keterangan untuk alat pertanian. Pendekatan ini menjadikan program lebih terorganisir, mudah dikembangkan, dan fleksibel untuk penambahan fitur atau kategori produk baru di masa depan.

---

## Quiz
1. Apa keuntungan menggunakan inheritance dibanding membuat class terpisah tanpa hubungan? Jawaban: inheritance membuat kode lebih efisien, mudah dirawat, terstruktur, dan fleksibel. 

2. Bagaimana cara subclass memanggil konstruktor superclass?
  Jawaban: Subclass memanggil konstruktor superclass dengan super. 

3.  Berikan contoh kasus di POS pertanian selain Benih, Pupuk, dan Alat Pertanian yang bisa dijadikan subclass.
 Jawaban: Pestisida, Bibit Hewan, Obat Hewan, dan Hasil Panen.

