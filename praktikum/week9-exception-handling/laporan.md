# Laporan Praktikum Minggu 9
Topik: exception handling

## Identitas
- Nama  : Egalian Lalintang
- NIM   : 240202833
- Kelas : 3IKRA

---

## Tujuan
Mahasiswa mampu menjelaskan perbedaan antara error dan exception.
Mahasiswa mampu mengimplementasikan blok try-catch-finally dengan tepat.
Mahasiswa mampu membuat custom exception untuk menangani logika bisnis yang spesifik.
Mahasiswa mampu mengintegrasikan penanganan error ke dalam sistem Agri-POS.

---

## Dasar Teori
1. Error vs Exception: Error adalah kerusakan fatal sistem yang tidak bisa diperbaiki (seperti memori habis), sedangkan Exception adalah masalah logika/program yang masih bisa diatasi agar aplikasi tidak mati.
2. Try-Catch-Finally: Metode pengamanan kode; Try untuk mencoba kode berisiko, Catch untuk menangani jika terjadi masalah, dan Finally untuk menjalankan kode yang wajib dieksekusi dalam kondisi apa pun.
3. Custom Exception: Eror buatan programmer sendiri untuk menandai masalah spesifik pada aturan bisnis, seperti pesan "Saldo Kurang" atau "User Tidak Ditemukan".

---

## Langkah Praktikum
1. Membuat Custom Exception: Menyusun tiga kelas pengecualian spesifik (InvalidQuantity, ProductNotFound, InsufficientStock) sebagai turunan dari kelas Exception.
2. Modifikasi Model Produk: Menambahkan atribut stock pada kelas Product untuk menyimpan data ketersediaan barang.
3. Implementasi Logika Keranjang: Memperbarui fungsi di ShoppingCart agar mampu melempar (throw) exception jika ditemukan input negatif atau stok tidak mencukupi.
4. Uji Coba Program Utama: Membuat kelas MainExceptionDemo yang menggunakan blok try-catch untuk menangani setiap kesalahan yang muncul saat simulasi belanja.
---

## Kode Program

package main.java.com.upb.agripos;

public class InsufficientStockException extends Exception {
    public InsufficientStockException(String msg) { super(msg); }
}



package main.java.com.upb.agripos;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String msg) { super(msg); }
}



package main.java.com.upb.agripos;

public class MainExceptionDemo {
    public static void main(String[] args) {
        // Ganti dengan Nama dan NIM Anda sesuai format di gambar
        System.out.println("Hello, I am [Egalian Lalintang]-[240202833] (Week9)");

        ShoppingCart cart = new ShoppingCart();
        Product p1 = new Product("P01", "Pupuk Organik", 25000, 3);

        // Skenario 1: Invalid Quantity (Quantity harus lebih dari 0)
        try {
            cart.addProduct(p1, -1);
        } catch (InvalidQuantityException e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        // Skenario 2: Product Not Found (Produk tidak ada dalam keranjang)
        try {
            cart.removeProduct(p1);
        } catch (ProductNotFoundException e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        // Skenario 3: Insufficient Stock (Stok tidak cukup)
        try {
            // Kita coba tambah 5, padahal stok p1 hanya 3
            cart.addProduct(p1, 5);
            cart.checkout();
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }
    }
}



package main.java.com.upb.agripos;

public class Product {
    private final String code;
    private final String name;
    private final double price;
    private int stock;

    public Product(String code, String name, double price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Menambahkan getter di bawah ini akan menghilangkan warning "is not used"
    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    
    public void reduceStock(int qty) { 
        this.stock -= qty; 
    }
}



package main.java.com.upb.agripos;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String msg) { super(msg); }
}


package main.java.com.upb.agripos;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product p, int qty) throws InvalidQuantityException {
        if (qty <= 0) {
            throw new InvalidQuantityException("Jumlah beli '" + p.getName() + "' harus lebih dari 0!");
        }
        items.put(p, items.getOrDefault(p, 0) + qty);
    }

    public void removeProduct(Product p) throws ProductNotFoundException {
        if (!items.containsKey(p)) {
            throw new ProductNotFoundException("Gagal menghapus: Produk tidak ditemukan di keranjang.");
        }
        items.remove(p);
    }

    public void checkout() throws InsufficientStockException {
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            if (entry.getKey().getStock() < entry.getValue()) {
                throw new InsufficientStockException("Stok '" + entry.getKey().getName() + "' tidak mencukupi.");
            }
        }
        // Jika semua stok aman, kurangi stok
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().reduceStock(entry.getValue());
        }
        System.out.println("Checkout Berhasil!");
    }
}

---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![week9-exception-handling](image.png)
)
---

## Analisis
(
1. Jalannya Kode
Program menjalankan simulasi transaksi pada sistem Agripos. Alur kerjanya adalah melakukan validasi data secara otomatis:
Deteksi Input Salah: Menolak jumlah pembelian 0.
Validasi Keranjang: Mendeteksi jika mencoba menghapus barang yang tidak ada.
Cek Ketersediaan: Memastikan stok gudang cukup sebelum transaksi diproses. Program tidak berhenti (crash), melainkan memberikan pesan edukatif kepada pengguna.
2. Perbedaan Pendekatan
Dulu: Menggunakan banyak if-else manual. Jika ada kesalahan besar, program berisiko langsung mati.
Sekarang (Exception Handling): Menggunakan blok try-catch. Kesalahan dianggap sebagai "Pengecualian" yang dikelola dengan rapi, sehingga alur program utama tetap berjalan stabil meskipun ada error input.
3. Kendala & Solusi
Kendala: Menangani berbagai jenis error yang berbeda (stok vs input) agar tidak tercampur.
Solusi: Menggunakan Custom Exception atau pesan error spesifik lewat e.getMessage(), sehingga terminal hanya menampilkan pesan kesalahan yang relevan bagi pengguna tanpa kode teknis yang membingungkan.
)
---

## Kesimpulan
Implementasi Exception Handling pada sistem Agripos (Week 9) berhasil meningkatkan aspek ketangguhan (robustness) dan pengalaman pengguna (user experience) pada aplikasi.
Sistem Lebih Stabil: Dengan menggunakan blok try-catch, program tidak lagi berhenti paksa (force close) saat menemui kesalahan input atau logika belanja, melainkan mampu menangani kesalahan tersebut secara mandiri.
Pemisahan Logika: Pendekatan minggu ini memisahkan antara alur bisnis normal dengan alur penanganan kesalahan, sehingga kode menjadi lebih bersih dan terstruktur dibandingkan penggunaan if-else konvensional.
Pesan Error Informatif: Program mampu memberikan umpan balik yang spesifik kepada pengguna (seperti pesan stok tidak cukup atau barang tidak ditemukan), yang memudahkan proses debugging dan penggunaan aplikasi secara nyata.

---

## Quiz
1. Jelaskan bagaimana kode berjalan. Jawaban: Kode menjalankan simulasi transaksi pada sistem Agripos dengan menerapkan validasi otomatis. Program mencoba melakukan beberapa aksi (seperti input jumlah beli dan pengecekan stok); jika aksi tersebut melanggar aturan bisnis (misalnya stok kurang atau input 0), sistem menangkap kesalahan tersebut dan menampilkan pesan edukatif di terminal tanpa membuat program terhenti (crash).

2. Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya. Jawaban: Pendekatan minggu ini menggunakan Exception Handling (try-catch), sedangkan minggu sebelumnya cenderung menggunakan validasi manual dengan if-else. Dengan try-catch, logika utama program tetap bersih dan terpisah dari logika penanganan kesalahan, sehingga kode lebih modular, profesional, dan lebih tangguh menghadapi error yang tidak terduga.

3. Kendala yang dihadapi dan cara mengatasinya. Jawaban: Kendala utama adalah membedakan jenis kesalahan agar pesan yang muncul tetap spesifik (misal: beda pesan antara "salah input" dan "stok habis"). Cara mengatasinya adalah dengan menggunakan Custom Exception atau menangkap pesan spesifik melalui e.getMessage(). Hal ini memastikan terminal hanya menampilkan informasi yang relevan bagi pengguna, bukan baris kode teknis yang membingungkan.
