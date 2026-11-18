# Katalog Buku UPI Store

Aplikasi desktop sederhana untuk menyimpan dan menampilkan katalog buku.
Dibuat dengan **Java, JavaFX, dan SQLite (JDBC)**.

## Fitur
- Menambah buku: judul, penulis, harga, dan sampul
- Validasi input (judul/penulis wajib diisi, harga harus angka bulat lebih dari 0)
- Data tersimpan di database SQLite dan tetap ada saat aplikasi dibuka kembali
- Katalog ditampilkan dalam bentuk kartu buku

## Kebutuhan
- JDK 21
- Apache Maven (sudah termasuk di Apache NetBeans)
- Koneksi internet saat pertama kali, untuk mengunduh dependency

## Cara Menjalankan

### 1. Clone repository
```
git clone https://github.com/USERNAME/KatalogBuku.git
cd KatalogBuku
```

### 2. Jalankan

**Lewat terminal:**
```
mvn clean javafx:run
```

**Lewat Apache NetBeans:**
1. Pilih **File > Open Project**, lalu buka folder `KatalogBuku`.
2. Pastikan **Properties > Run > Main Class** berisi `main.Launcher`.
3. Klik **Run Project** (F6).

### 3. Cara memakai
1. Isi **Judul buku**, **Penulis**, dan **Harga** (angka saja, contoh `45000`).
2. Pilih **Sampul contoh**.
3. Klik **Simpan**. Kartu buku akan muncul di katalog.
4. Tutup dan buka kembali aplikasi. Data yang tersimpan akan tampil lagi.

## Struktur Proyek
```
KatalogBuku/
  pom.xml
  data/                      # buku.db dibuat otomatis saat pertama dijalankan
  screenshots/               # gambar untuk README
  src/main/java/
    main/       MainApp.java, Launcher.java
    model/      Buku.java
    db/         Database.java
    data/       BukuRepository.java
    controller/ KatalogController.java
  src/main/resources/
    view/katalog.fxml
    view/styles/app.css
    gambar/     biru.png, hijau.png, merah.png

Screenshot 2026-11-18 143058 
![Tampilan halaman](Screenshot 2026-11-18 143058.png)
