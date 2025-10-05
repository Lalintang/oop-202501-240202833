# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: Eksplorasi Tiga Paradigma Pemrograman Java: Prosedural, OOP, dan Fungsional

## Identitas
- Nama  : Egalian Lalintang
- NIM   : 240202833
- Kelas : 3IKRA

---

## Tujuan
1. Paradigma Prosedural – dengan menulis program menggunakan fungsi main secara langsung tanpa objek.
2. Paradigma Berorientasi Objek (OOP) – dengan membuat class dan object (contohnya class Mahasiswa) untuk mengorganisasi data dan perilaku.
3. Paradigma Fungsional – dengan memanfaatkan functional interface seperti BiConsumer untuk mendefinisikan fungsi sebagai objek.
Sehingga, setelah praktikum ini mahasiswa mampu mengimplementasikan ketiga paradigma tersebut untuk menyelesaikan masalah sederhana seperti menampilkan data diri.
---

## Dasar Teori
1. Prosedural – Fokus pada langkah-langkah berurutan tanpa class atau object.
2. OOP – Menggunakan class dan object untuk membuat kode lebih terstruktur dan mudah dikembangkan.
3. Fungsional – Memanfaatkan fungsi dan ekspresi lambda agar kode lebih ringkas dan deklaratif.
---

## Langkah Praktikum
1. Langkah-langkah yang dilakukan
Melakukan setup project Java di VS Code.
Membuat dan menulis kode program sesuai tiga paradigma (Prosedural, OOP, dan Fungsional).
Menjalankan program untuk melihat hasil output.
2. File/Kode yang dibuat
HelloProsedural.java – implementasi paradigma prosedural.
HelloOOP.java – implementasi paradigma berorientasi objek.
HelloFunctional.java – implementasi paradigma fungsional.
3. Commit message yang digunakan
feat: add HelloProsedural.java
feat: add HelloOOP.java
feat: add HelloFunctional.java

---

## Kode Program
  class Mahasiswa{
    String nama;
    int nim;

    Mahasiswa(String n, int m){
        this.nama = n;
        this.nim = m;
    }
    void sapa(){
    System.out.println("Hello World I am " +nama + "_" + nim );
}
}
public class hellooop {
    public static void main(String[] args) {
         //membuat variable scanner input
       Mahasiswa a = new Mahasiswa("Khansa Amanda Icha Sentana", 240202838);
        // Meminta Input Dari User
        // Mengambil Object dari scaner input
      
        // Call method sapa
        a.sapa();
        

        System.out.println("Program OOP Selesai Dijalankan, Terimakasih");

    }
    
}
public class helloProcedural {

  
    public static void main(String[] args) {
         String nama = "Khansa Amanda Icha Sentana";
         String nim = "240202838";

         System.out.println("Hello World I'm "+ nama + " -"+ nim);

       

    }

    
}
import java.util.function.BiConsumer;

public class helofunctional {

     public static void main(String[] args) {
        // Membuat fungsi "sapa" menggunakan lambda
        BiConsumer<String, String> sapa = 
            (nama, nim) -> System.out.println("Hello World, I am " + nama + " -" + nim);

        // eksekusi fungsi
        sapa.accept("Khansa Amanda Icha Sentana", "240202838");
        System.out.println("Program Functional Selesai, Terimakasih");
}
}


## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil](/praktikum/week1-setup-hello-pos/src/screenshot/Screenshot%20(1).png)
)
![Screenshot hasil](/praktikum/week1-setup-hello-pos/src/screenshot/Screenshot%20(2).png)
![Screenshot hasil](/praktikum/week1-setup-hello-pos/src/screenshot/Screenshot%20(3).png)
)
)
---

## Analisis
1.Cara Kerja Kode
a. Program membaca data nama dan nim, lalu mencetak teks ke layar.
b. Pada prosedural, semua perintah langsung ditulis di dalam main().
c. Pada OOP, data dan perilaku dibungkus dalam class dan object (Mahasiswa).
d. Pada fungsional, fungsi ditulis sebagai ekspresi lambda dan dijalankan menggunakan BiConsumer.
2.Perbedaan dengan Minggu Sebelumnya
a. Minggu ini membandingkan tiga paradigma pemrograman dalam Java: prosedural, OOP, dan fungsional.
b. Minggu sebelumnya fokus pada penulisan kode sederhana tanpa konsep paradigma yang berbeda.
3.Kendala dan Cara Mengatasinya
a. Kendala: Kesalahan sintaks saat membuat class atau lambda.
b. Solusi: Mengecek kembali struktur kode, tanda kurung, dan tipe data sesuai aturan Java.


## Kesimpulan
Dari praktikum minggu ini dapat disimpulkan bahwa setiap paradigma pemrograman memiliki cara tersendiri dalam menyelesaikan masalah. Paradigma prosedural menjalankan perintah secara berurutan dan sederhana, OOP membuat program lebih terstruktur dan mudah dikembangkan dengan penggunaan class dan object, sedangkan fungsional membuat kode lebih ringkas dan efisien dengan memanfaatkan fungsi sebagai nilai. Dengan memahami ketiganya, kita dapat memilih paradigma yang paling tepat sesuai kebutuhan program.
---

## Quiz
1. Apakah OOP selalu lebih baik dari prosedural?
Tidak selalu. OOP lebih baik untuk proyek besar dan kompleks karena strukturnya terorganisir dan mudah dikembangkan. Namun, prosedural lebih sederhana dan cepat diterapkan pada program kecil atau tugas yang tidak memerlukan struktur kompleks.
2. Kapan functional programming lebih cocok digunakan dibanding OOP atau prosedural?
Functional programming lebih cocok saat kita membutuhkan operasi data yang kompleks, paralel, atau transformasi data berulang karena kode menjadi lebih ringkas, mudah diuji, dan minim efek samping.
3. Bagaimana paradigma (prosedural, OOP, fungsional) memengaruhi maintainability dan scalability aplikasi?
Prosedural: mudah dibuat tapi sulit dikelola saat aplikasi membesar.
OOP: sangat baik untuk maintainability dan scalability karena kode terstruktur dan modular.
Fungsional: meningkatkan keterbacaan dan mengurangi bug, cocok untuk sistem besar dengan banyak proses data.
4. Mengapa OOP lebih cocok untuk mengembangkan aplikasi POS dibanding prosedural?
Karena POS memiliki banyak entitas dan interaksi (seperti produk, transaksi, pelanggan) yang dapat direpresentasikan sebagai class dan object, sehingga kode lebih terorganisir, mudah dikembangkan, dan dirawat.
5. Bagaimana paradigma fungsional dapat membantu mengurangi kode berulang (boilerplate code)?
Dengan menggunakan fungsi sebagai nilai dan ekspresi lambda, kita dapat menulis ulang logika umum sekali saja lalu memanggilnya kapan pun diperlukan. Ini membuat kode lebih singkat, bersih, dan mudah dikelola.


2. [Tuliskan kembali pertanyaan 2 dari panduan]  
   **Jawaban:** …  

3. [Tuliskan kembali pertanyaan 3 dari panduan]  
   **Jawaban:** …  )
