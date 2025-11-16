# Laporan Praktikum Minggu 5 Abstraction (Abstract Class & Interface)
Topik:Abstraction (Abstract Class & Interface)

## Identitas
- Nama  : Egalian Lalintang
- NIM   : 240202833
- Kelas : 3IKRA

---

## Tujuan
Mahasiswa mampu menjelaskan perbedaan abstract class dan interface.
Mahasiswa mampu mendesain abstract class dengan method abstrak sesuai kebutuhan kasus.
Mahasiswa mampu membuat interface dan mengimplementasikannya pada class.
Mahasiswa mampu menerapkan multiple inheritance melalui interface pada rancangan kelas.
Mahasiswa mampu mendokumentasikan kode (komentar kelas/method, README singkat pada folder minggu).

---

## Dasar Teori
Abstraksi adalah proses menyederhanakan kompleksitas dengan menampilkan elemen penting dan menyembunyikan detail implementasi.

Abstract class: tidak dapat diinstansiasi, dapat memiliki method abstrak (tanpa badan) dan non-abstrak. Dapat menyimpan state (field).
Interface: kumpulan kontrak (method tanpa implementasi konkret). Sejak Java 8 mendukung default method. Mendukung multiple inheritance (class dapat mengimplementasikan banyak interface).
Gunakan abstract class bila ada shared state dan perilaku dasar; gunakan interface untuk mendefinisikan kemampuan/kontrak lintas hierarki.
Dalam konteks Agri-POS, Pembayaran dapat dimodelkan sebagai abstract class dengan method abstrak prosesPembayaran() dan biaya(). Implementasi konkritnya: Cash dan EWallet. Kemudian, interface seperti Validatable (mis. verifikasi OTP) dan Receiptable (mencetak bukti) dapat diimplementasikan oleh jenis pembayaran yang relevan.

---

## Langkah Praktikum
1. Abstract Class – Pembayaran
Buat Pembayaran (abstract) dengan field invoiceNo, total dan method:
double biaya() (abstrak) → biaya tambahan (fee).
boolean prosesPembayaran() (abstrak) → mengembalikan status berhasil/gagal.
double totalBayar() (konkrit) → return total + biaya();.
2. Subclass Konkret
Cash → biaya = 0, proses = selalu berhasil jika tunai >= totalBayar().
EWallet → biaya = 1.5% dari total; proses = membutuhkan validasi.
3. Interface
Validatable → boolean validasi(); (contoh: OTP).
Receiptable → String cetakStruk();
4. Multiple Inheritance via Interface
EWallet mengimplementasikan dua interface: Validatable, Receiptable.
Cash setidaknya mengimplementasikan Receiptable.
5. Main Class
Buat MainAbstraction.java untuk mendemonstrasikan pemakaian Pembayaran (polimorfik).
Tampilkan hasil proses dan struk. Di akhir, panggil CreditBy.print("[NIM]", "[Nama]").
6. Commit dan Push
Commit dengan pesan: week5-abstraction-interface.

---

## Kode Program
// 1. Receiptable.java
package main.java.com.upb.agripos.model.kontrak;

public interface Receiptable {
    String cetakStruk();
}



// 2. Validatable.java
package main.java.com.upb.agripos.model.kontrak;

public interface Validatable {
    boolean validasi(); // misal validasi OTP/ PIN
}



// 3. Cash.java
package main.java.com.upb.agripos.model.pembayaran;

import main.java.com.upb.agripos.model.kontrak.Receiptable;

public class Cash extends Pembayaran implements Receiptable {
    private double tunai;

    public Cash(String invoiceNo, double total, double tunai) {
        super(invoiceNo, total);
        this.tunai = tunai;
    }

    @Override
    public double biaya() {
        return 0.0;
    }

    @Override
    public boolean prosesPembayaran() {
        return tunai >= totalBayar(); // sederhana: cukup uang tunai
    }

    @Override
    public String cetakStruk() {
        return String.format("INVOICE %s | TOTAL: %.2f | BAYAR CASH: %.2f | KEMBALI: %.2f",
                invoiceNo, totalBayar(), tunai, Math.max(0, tunai - totalBayar()));
    }
}




// 4. EWallet.java
package main.java.com.upb.agripos.model.pembayaran;

import main.java.com.upb.agripos.model.kontrak.Validatable;
import main.java.com.upb.agripos.model.kontrak.Receiptable;

public class EWallet extends Pembayaran implements Validatable, Receiptable {
    private String akun;
    private String otp;

    public EWallet(String invoiceNo, double total, String akun, String otp) {
        super(invoiceNo, total);
        this.akun = akun;
        this.otp = otp;
    }

    @Override
    public double biaya() {
        return total * 0.015; // biaya 1.5%
    }

    @Override
    public boolean validasi() {
        return otp != null && otp.length() == 6; // OTP valid jika panjangnya 6
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi(); // proses pembayaran hanya jika validasi berhasil
    }

    @Override
    public String cetakStruk() {
        return String.format(
            "INVOICE %s | TOTAL+FEE: %.2f | E-WALLET: %s | STATUS: %s",
            invoiceNo, totalBayar(), akun,
            prosesPembayaran() ? "BERHASIL" : "GAGAL"
        );
    }
}



// 5. Pembayaran.java
package main.java.com.upb.agripos.model.pembayaran;

public abstract class Pembayaran {
    protected String invoiceNo;
    protected double total;

    public Pembayaran(String invoiceNo, double total) {
        this.invoiceNo = invoiceNo;
        this.total = total;
    }

    public abstract double biaya();              // biaya tambahan
    public abstract boolean prosesPembayaran();  // hasil: berhasil/gagal

    public double totalBayar() {
        return total + biaya();
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public double getTotal() {
        return total;
    }
}


// 6. CreditBy.java
package main.java.com.upb.agripos.util;

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\n--- Credit ---");
        System.out.println("Praktikum Week 5: Abstraction & Interface");
        System.out.println("NIM: " + nim);
        System.out.println("Nama: " + nama);
        System.out.println("--------------");
    }
}



// 7. MainAbstraction.java
package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.pembayaran.*;
import main.java.com.upb.agripos.model.kontrak.*;
import main.java.com.upb.agripos.util.CreditBy;

public class MainAbstraction {
    public static void main(String[] args) {
        // Kasus 1: Pembayaran Tunai (Cash) - Uang Cukup
        Pembayaran cashOK = new Cash("INV-001", 100000, 120000);
        
        // Kasus 2: Pembayaran E-Wallet - Validasi Gagal (OTP kurang dari 6 digit)
        Pembayaran ewGagal = new EWallet("INV-002", 150000, "user@ewallet", "123");
        
        // Kasus 3: Pembayaran E-Wallet - Validasi Berhasil
        Pembayaran ewSukses = new EWallet("INV-003", 20000, "tani@ewallet", "123456");
        
        // DEMO 1: Proses Pembayaran
        System.out.println("--- Proses Pembayaran ---");
        System.out.println("CASH (Status): " + cashOK.prosesPembayaran());
        System.out.println("E-Wallet (Gagal Status): " + ewGagal.prosesPembayaran());
        System.out.println("E-Wallet (Sukses Status): " + ewSukses.prosesPembayaran());
        System.out.println("-------------------------");
        
        // DEMO 2: Dynamic Binding melalui Interface (Receiptable)
        System.out.println("--- Cetak Struk ---");
        
        // Casting diperlukan karena cashOK dan ewSukses bertipe Pembayaran
        // dan kita ingin memanggil method dari interface Receiptable
        System.out.println(((Receiptable) cashOK).cetakStruk());
        System.out.println(((Receiptable) ewSukses).cetakStruk());
        System.out.println(((Receiptable) ewGagal).cetakStruk());

        // DEMO 3: Menggunakan Validatable (khusus EWallet)
        if (ewSukses instanceof Validatable) {
            Validatable v = (Validatable) ewSukses;
            System.out.println("\nValidasi OTP E-Wallet Sukses: " + v.validasi());
        }

        CreditBy.print("240202833", "Egalian Lalintang");
    }
}



---

## Hasil Eksekusi

![Screenshot hasil](screenshots/hasil.png)

---

## Analisis
(
- Jelaskan bagaimana kode berjalan.  
Jelaskan bagaimana kode berjalan. Kode mendemonstrasikan Abstraksi dan Polymorphism. Abstract Class Pembayaran mendefinisikan kontrak umum (biaya(), prosesPembayaran()) yang harus dipenuhi oleh class konkret (Cash, EWallet). Method konkrit totalBayar() menggunakan method abstrak biaya(). Pemanggilan cetakStruk() pada objek cash dan ew menunjukkan dynamic binding melalui interface Receiptable, di mana implementasi method berbeda sesuai class konkretnya.

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  
Minggu Lalu (Polymorphism/Inheritance) berfokus pada implementasi dan hierarki state. Minggu Ini (Abstraction & Interface) berfokus pada kontrak desain. Abstraksi memungkinkan kita mendesain Pembayaran tanpa tahu detailnya, dan interface memungkinkan EWallet memiliki kemampuan ganda (Validatable dan Receiptable) yang tidak dapat dilakukan melalui single inheritance class.

- Kendala yang dihadapi dan cara mengatasinya.  
Kendala: Awalnya terjadi compile error karena lupa mengimplementasikan semua method abstrak (biaya() dan prosesPembayaran()) di class konkret (Cash.java).
Cara Mengatasi: Memastikan setiap class yang mewarisi Abstract Class (Pembayaran) atau mengimplementasikan Interface (Receiptable) harus menyediakan badan method (implementasi) untuk semua method abstrak/interface yang ada.
)
---

## Kesimpulan
Dengan menggunakan class dan object, program menjadi lebih terstruktur dan mudah dikembangkan.
Praktikum Minggu 5 berhasil menerapkan Abstraksi dan Interface. Pembayaran sebagai Abstract Class berhasil menyimpan state dasar dan logic umum (totalBayar()), sementara Interface (Receiptable, Validatable) berhasil mendefinisikan kemampuan yang dapat diimplementasikan oleh banyak class berbeda (EWallet dan Cash), sekaligus mendemonstrasikan Multiple Inheritance via Interface.

---

## Quiz
(1. Jelaskan perbedaan konsep dan penggunaan abstract class dan interface.
   **Jawaban:** Abstract class dipakai ketika beberapa kelas memiliki kesamaan karakter dasar, baik dari sisi atribut maupun fungsi utama yang ingin diwariskan. Dalam abstract class, kita dapat menambahkan field sebagai data yang melekat pada objek, serta method yang boleh sudah diimplementasikan atau masih berbentuk abstrak. Sementara itu, interface hanya menyediakan daftar nama method sebagai aturan yang harus diikuti oleh kelas yang menggunakannya, tanpa menyertakan penyimpanan data atau state. Interface umumnya digunakan untuk mendefinisikan kemampuan tertentu yang dapat dimiliki oleh banyak kelas berbeda, meskipun kelas-kelas tersebut tidak memiliki hubungan pewarisan secara langsung.

2. Mengapa multiple inheritance lebih aman dilakukan dengan interface pada Java?
   **Jawaban:** Interface hanya berisi definisi method tanpa menyimpan data atau menyediakan implementasi secara langsung. Karena tidak membawa state maupun kode konkret, sebuah kelas dapat mengimplementasikan beberapa interface sekaligus tanpa menimbulkan konflik pewarisan. Dengan demikian, masalah seperti diamond problem yang muncul pada pewarisan ganda class dapat dihindari.

3.  Pada contoh Agri-POS, bagian mana yang paling tepat menjadi abstract class dan mana yang menjadi interface?
   **Jawaban:** Kelas Pembayaran sebaiknya dibuat sebagai abstract class karena memiliki atribut dasar dan logika umum, seperti invoiceNo, total, serta method totalBayar() yang bisa digunakan oleh semua tipe pembayaran. Sementara itu, Validatable dan Receiptable lebih cocok dijadikan interface karena hanya menetapkan kontrak perilaku tambahan, seperti validasi OTP atau pencetakan struk, yang tidak harus dimiliki oleh setiap jenis pembayaran.
